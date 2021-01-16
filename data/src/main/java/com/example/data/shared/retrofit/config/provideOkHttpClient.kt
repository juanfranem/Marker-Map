package com.example.data.shared.retrofit.config

import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

fun provideOkHttpClient(): OkHttpClient =
    OkHttpClient().newBuilder()
        .addNetworkInterceptor(StethoInterceptor())
        .connectTimeout(90, TimeUnit.SECONDS)
        .readTimeout(90, TimeUnit.SECONDS)
        .build()