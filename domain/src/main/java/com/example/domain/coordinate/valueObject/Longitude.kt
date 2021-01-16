package com.example.domain.coordinate.valueObject

import com.example.domain.coordinate.exception.LongitudeIsNotValid
import com.example.domain.shared.ValueObject

data class Longitude(val value: Double): ValueObject<Double>(value) {

    init {
        checkIfLongitudeAreInRange()
    }

    private fun checkIfLongitudeAreInRange() {
        if (value < -180.0 || value > 180.0 ) {
            throw LongitudeIsNotValid()
        }
    }

    override fun toString(): String {
        return value.toString()
    }
}