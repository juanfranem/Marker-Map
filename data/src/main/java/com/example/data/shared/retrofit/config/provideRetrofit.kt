package com.example.data.shared.retrofit.config

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.data.BuildConfig.URL_BASE

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder().baseUrl(URL_BASE).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()