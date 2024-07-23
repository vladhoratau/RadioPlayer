package com.example.radioplayer.infra

import android.content.Context
import android.util.Log
import com.example.radioplayer.infra.Constants.RADIO_PLAYER_API_URL
import com.example.radioplayer.infra.FileUtils.readPemFile
import com.example.radioplayer.infra.FileUtils.saveByteArrayToFile
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.adamcin.httpsig.api.Algorithm
import net.adamcin.httpsig.api.Challenge
import net.adamcin.httpsig.api.Constants
import net.adamcin.httpsig.api.Key
import net.adamcin.httpsig.api.RequestContent
import net.adamcin.httpsig.api.Signer
import net.adamcin.httpsig.bouncycastle.PEMHelper
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    /** Method for providing the okhttp repository
     * @return an instance of OkHttpClient */
    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val keyId = "a1788d60-0403-11ef-ac22-edf145f33827"
        val fileName = "$keyId.pem"

        val pemByteArray = readPemFile(context, fileName)

        val pemFile = pemByteArray?.let { saveByteArrayToFile(context, it, fileName) }
        if (pemFile == null) {
            Log.e("Vlad", "PEM file is null. Cannot configure OkHttpClient properly.")
        } else {
            Log.e("Vlad", "PEM file is ${pemFile.name}")
        }
        val key: Key = PEMHelper.readKey(pemFile, charArrayOf())
        val signer =
            Signer(key, net.adamcin.httpsig.ssh.jce.UserFingerprintKeyId(keyId))
        val challenge =
            Challenge("", Constants.DEFAULT_HEADERS, listOf(Algorithm.RSA_SHA256))
        signer.rotateKeys(challenge)

        val dateFormat = SimpleDateFormat(RequestContent.DATE_FORMAT_RFC1123)
        dateFormat.timeZone = TimeZone.getTimeZone("GMT")


        val authInterceptor = Interceptor { chain ->
            val original = chain.request()
            val requestContentBuilder = RequestContent.Builder()
            requestContentBuilder.addHeader("date", dateFormat.format(Date()))
            val requestContent = requestContentBuilder.build()
            val auth = signer.sign(requestContent)
            val sig = StringBuilder().append("Signature keyId=")
                .append("\"").append(keyId).append("\"")
                .append(",algorithm=\"rsa-sha256\",signature=\"")
                .append(auth.signature)
                .append("\"").toString()

            val requestBuilder = original.newBuilder()
                .header("date", dateFormat.format(requestContent.dateGMT))
                .header("Authorization", sig)
            val request = requestBuilder.build()
            chain.proceed(request)
        }
        return OkHttpClient.Builder().addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor).build()
    }

    /** Method for providing the retrofit repository
     * @return an instance of Retrofit*/
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(RADIO_PLAYER_API_URL)
            .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build()
    }

    @Provides
    @Singleton
    fun provideRadioStationService(retrofit: Retrofit): RadioStationService {
        return retrofit.create(RadioStationService::class.java)
    }
}