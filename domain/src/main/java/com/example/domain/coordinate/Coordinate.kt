package com.example.domain.coordinate

import com.example.domain.coordinate.valueObject.Latitude
import com.example.domain.coordinate.valueObject.Longitude

data class Coordinate(
    val latitude: Latitude,
    val longitude: Longitude
) {
    override fun toString(): String {
        return "$latitude,$longitude"
    }
}