package com.procamera.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {
    // Replace with your local machine's IP address if testing on a physical device.
    // 10.0.2.2 is the default alias for your host loopback interface in the Android Emulator.
    private const val BASE_URL = "http://10.0.2.2:5000"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: ProCameraApi by lazy {
        retrofit.create(ProCameraApi::class.java)
    }
}
