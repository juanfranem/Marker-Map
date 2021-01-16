package com.example.data.shared.useCase

import com.example.data.marker.useCase.FindMarkersByCityAndCoordinateRange
import org.koin.dsl.module

val useCaseModule = module {
    factory { FindMarkersByCityAndCoordinateRange(get()) }
}