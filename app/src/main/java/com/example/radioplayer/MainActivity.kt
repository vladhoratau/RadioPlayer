package com.example.radioplayer

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.HttpGet
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client.HttpClientBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.adamcin.httpsig.api.Algorithm
import net.adamcin.httpsig.api.Challenge
import net.adamcin.httpsig.api.Constants
import net.adamcin.httpsig.api.Key
import net.adamcin.httpsig.api.RequestContent
import net.adamcin.httpsig.api.Signer
import net.adamcin.httpsig.bouncycastle.PEMHelper
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone


class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CoroutineScope(Dispatchers.IO).launch {
            val keyId = "a1788d60-0403-11ef-ac22-edf145f33827"
            val fileName = "$keyId.pem"

            val pemByteArray = readPemFile(applicationContext, fileName)
            if (pemByteArray != null) {
                val pemFile = saveByteArrayToFile(applicationContext, pemByteArray, fileName)
                if (pemFile != null) {

                    Log.d("Vlad", "PEM ${pemFile.readText()}")
                    val key: Key = PEMHelper.readKey(pemFile, charArrayOf())
                    Log.d("Vlad", "key $key")

                    val signer =
                        Signer(key, net.adamcin.httpsig.ssh.jce.UserFingerprintKeyId(keyId))
                    val challenge =
                        Challenge("", Constants.DEFAULT_HEADERS, listOf(Algorithm.RSA_SHA256))
                    signer.rotateKeys(challenge)

                    val dateFormat = SimpleDateFormat(RequestContent.DATE_FORMAT_RFC1123)
                    dateFormat.timeZone = TimeZone.getTimeZone("GMT")

                    val requestCOntentBuilder = RequestContent.Builder()
                    requestCOntentBuilder.addHeader("date", dateFormat.format(Date()))
                    val requestContent = requestCOntentBuilder.build()

                    val auth = signer.sign(requestContent)
                    val sig = java.lang.StringBuilder().append("Signature keyId=")
                        .append("\"").append(keyId).append("\"")
                        .append(",algorithm=\"rsa-sha256\",signature=\"")
                        .append(auth.signature)
                        .append("\"").toString()

                    val request =
                        HttpGet("https://api.radioplayer.org/v2/stations/8261004?include=streamsource")
                    request.addHeader("date", dateFormat.format(requestContent.dateGMT))
                    Log.d("Vlad", "Authorization header: $sig")
                    request.addHeader("Authorization", sig)

                    val client = HttpClientBuilder.create().build()
                    val response = client.execute(request)

                    val statusCode = response.statusLine.statusCode
                    val responseBody = if (response.entity != null) {
                        val inputStream = response.entity.content
                        val content = inputStream.bufferedReader().use { it.readText() }
                        content
                    } else {
                        ""
                    }
                    Log.d("Vlad", "request status ${statusCode} response content $responseBody")
                } else {
                    Log.e("Vlad", "Failed to save PEM file to internal storage")
                }
            } else {
                Log.d("Vlad", "Failed to read PEM file from assets")
            }
        }
    }

    fun readPemFile(context: Context, fileName: String): ByteArray? {
        return try {
            // Open the file from assets
            val inputStream = context.assets.open(fileName)
            // Read all bytes into a ByteArray
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            buffer
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    fun saveByteArrayToFile(context: Context, byteArray: ByteArray, fileName: String): File? {
        return try {
            val file = File(context.filesDir, fileName)
            FileOutputStream(file).use { fos ->
                fos.write(byteArray)
            }
            file
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

}