package com.example.radioplayer.infrajava;

import android.content.Context;
import android.util.Log;

import net.adamcin.httpsig.api.Algorithm;
import net.adamcin.httpsig.api.Challenge;
import net.adamcin.httpsig.api.Constants;
import net.adamcin.httpsig.api.Key;
import net.adamcin.httpsig.api.RequestContent;
import net.adamcin.httpsig.api.Signer;
import net.adamcin.httpsig.bouncycastle.PEMHelper;
import net.adamcin.httpsig.ssh.jce.UserFingerprintKeyId;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
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
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class RetrofitModule {

    private static final String TAG = RetrofitModule.class.getSimpleName();
    private static final String KEY_ID = "a1788d60-0403-11ef-ac22-edf145f33827";

    /**
     * Method for providing the OkHttpClient repository
     *
     * @return an instance of OkHttpClient
     */
    @Provides
    @Singleton
    public static OkHttpClient provideOkHttpClient(@ApplicationContext Context context) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        InputStream pemFileStream = null;
        try {
            pemFileStream = context.getAssets().open(KEY_ID + ".pem");
        } catch (IOException e) {
            Log.e(TAG, "Error opening PEM file from assets", e);
        }

        Key key = null;
        if (pemFileStream != null) {
            try {
                key = PEMHelper.readKey(pemFileStream, new char[0]);
            } catch (Exception e) {
                Log.e(TAG, "Error reading PEM key", e);
            }
        } else {
            Log.e(TAG, "PEM file is null. Cannot configure OkHttpClient properly.");
        }
        Signer signer = new Signer(key, new UserFingerprintKeyId(KEY_ID));
        Challenge challenge = new Challenge("", Constants.DEFAULT_HEADERS, Arrays.asList(new Algorithm[]{Algorithm.RSA_SHA256}));
        signer.rotateKeys(challenge);

        DateFormat dateFormat = new SimpleDateFormat(RequestContent.DATE_FORMAT_RFC1123);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

        Interceptor authInterceptor = chain -> {
            Request originalRequest = chain.request();
            RequestContent requestContent = new RequestContent.Builder()
                    .addHeader("date", dateFormat.format(new Date()))
                    .build();

            String authorizationHeader = new StringBuilder().append("Signature keyId=")
                    .append("\"")
                    .append(KEY_ID)
                    .append("\"")
                    .append(",algorithm=\"rsa-sha256\",signature=\"")
                    .append(signer.sign(requestContent).getSignature()).append("\"").toString();

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

    /**
     * Method for providing the Retrofit instance
     *
     * @return an instance of Retrofit
     */
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

