package com.example.data.shared.retrofit.modules

import com.example.data.marker.RetrofitMarkerRepositoryImpl
import com.example.domain.marker.MarkerRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<MarkerRepository> { RetrofitMarkerRepositoryImpl(get()) }
}