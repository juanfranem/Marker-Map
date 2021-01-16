package com.example.data.shared

import com.example.data.shared.retrofit.modules.repositoryModule
import com.example.data.shared.retrofit.modules.retrofitModule
import com.example.data.shared.useCase.useCaseModule
import org.koin.dsl.module

val dataModule = listOf(
    repositoryModule,
    useCaseModule,
    retrofitModule
)