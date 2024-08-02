package com.example.radioplayer.infrajava;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/** Utils class for specific file operations */
public class FileUtils {

    private static final String TAG = FileUtils.class.getSimpleName();

    public static byte[] readPemFile(Context context, String fileName) {
        try {
            // Open the file from assets
            InputStream inputStream = context.getAssets().open(fileName);
            // Read all bytes into a ByteArray
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            return buffer;
        } catch (IOException e) {
            Log.e(TAG, "Error reading PEM file: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public static File saveByteArrayToFile(Context context, byte[] byteArray, String fileName) {
        try {
            File file = new File(context.getFilesDir(), fileName);
            try (FileOutputStream fos = new FileOutputStream(file)) {
                fos.write(byteArray);
            }
            return file;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

