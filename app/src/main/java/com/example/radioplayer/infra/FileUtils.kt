package com.example.radioplayer.infra

import android.content.Context
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

object FileUtils {
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
            Log.e("Vlad", "Error reading PEM file: ${e.message}")
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