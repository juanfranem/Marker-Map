package com.example.data.shared.retrofit.modules

import com.example.data.shared.retrofit.config.provideApi
import com.example.data.shared.retrofit.config.provideOkHttpClient
import com.example.data.shared.retrofit.config.provideRetrofit
import org.koin.dsl.module

val retrofitModule = module {
    factory { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    factory { provideApi(get()) }
    repositoryModule
}