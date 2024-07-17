package com.example.radioplayer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.io.File


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val keyId = "a1788d60-0403-11ef-ac22-edf145f33827"
        val resource = javaClass.getResource("$keyId.pem")
        val pemFile =
            File(resource?.file ?: throw IllegalArgumentException("Resource not found: $keyId.pem"))
        //val key = PEMHelper
    }
}