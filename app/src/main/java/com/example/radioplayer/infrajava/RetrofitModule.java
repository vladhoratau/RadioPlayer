package com.example.radioplayer.infrajava;

import android.content.Context;
import android.util.Log;

import net.adamcin.httpsig.api.Constants;
import net.adamcin.httpsig.api.Algorithm;
import net.adamcin.httpsig.api.Challenge;
import net.adamcin.httpsig.api.RequestContent;
import net.adamcin.httpsig.api.Signer;
import net.adamcin.httpsig.bouncycastle.PEMHelper;

import net.adamcin.httpsig.api.Key;
import net.adamcin.httpsig.ssh.jce.UserFingerprintKeyId;

import java.io.File;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.TimeZone;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class RetrofitModule {

    private static final String TAG = RetrofitModule.class.getSimpleName();
    private static final String KEY_ID = "a1788d60-0403-11ef-ac22-edf145f33827";

    /** Method for providing the OkHttpClient repository
     * @return an instance of OkHttpClient */
    @Provides
    @Singleton
    public static OkHttpClient provideOkHttpClient(@ApplicationContext Context context) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        File pemFile = null;
        String fileName = KEY_ID + ".pem";
        byte[] pemByteArray = FileUtils.readPemFile(context, fileName);

        if (pemByteArray != null) {
            pemFile = FileUtils.saveByteArrayToFile(context, pemByteArray, fileName);
        }

        if (pemFile == null) {
            Log.e(TAG, "PEM file is null. Cannot configure OkHttpClient properly.");
        } else {
            Log.e(TAG, "PEM file is " + pemFile.getName());
        }

        Key key = null;
        try {
            if (pemFile != null) {
                key = PEMHelper.readKey(pemFile, new char[0]);
            }
        } catch (IOException e) {
            Log.e(TAG, "Error reading PEM key", e);
        }
        Signer signer = new Signer(key, new UserFingerprintKeyId(KEY_ID));
        Challenge challenge = new Challenge("", Constants.DEFAULT_HEADERS, Collections.singletonList(Algorithm.RSA_SHA256));
        signer.rotateKeys(challenge);

        SimpleDateFormat dateFormat = new SimpleDateFormat(RequestContent.DATE_FORMAT_RFC1123);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

        Interceptor authInterceptor = chain -> {
            Request originalRequest = chain.request();
            RequestContent requestContent = new RequestContent.Builder()
                    .addHeader("date", dateFormat.format(new Date()))
                    .build();

            // Assuming signer.sign(requestContent) returns a String representing the signature
            String signature = String.valueOf(signer.sign(requestContent));

            String authorizationHeader = String.format("Signature keyId=\"%s\",algorithm=\"rsa-sha256\",signature=\"%s\"",
                    KEY_ID, signature);

            Request newRequest = originalRequest.newBuilder()
                    .header("date", dateFormat.format(requestContent.getDateGMT()))
                    .header("Authorization", authorizationHeader)
                    .build();

            return chain.proceed(newRequest);
        };

        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(authInterceptor)
                .build();
    }

    /** Method for providing the Retrofit instance
     * @return an instance of Retrofit */
    @Provides
    @Singleton
    public static Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(com.example.radioplayer.infrajava.Constants.RADIO_PLAYER_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    public static RadioStationService provideRadioStationService(Retrofit retrofit) {
        return retrofit.create(RadioStationService.class);
    }
}

