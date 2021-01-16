package com.example.domain.coordinate.valueObject

import com.example.domain.coordinate.exception.LatitudeIsNotValid
import com.example.domain.shared.ValueObject

data class Latitude(val value: Double): ValueObject<Double>(value) {

    init {
        checkIfLatitudeAreInRange()
    }

    private fun checkIfLatitudeAreInRange() {
        if (value < -90.0 || value > 90.0 ) {
            throw LatitudeIsNotValid()
        }
    }

    override fun toString(): String {
        return value.toString()
    }

}