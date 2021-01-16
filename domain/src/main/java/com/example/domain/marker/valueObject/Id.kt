package com.example.domain.marker.valueObject

import com.example.domain.marker.exception.IdCantBeEmpty
import com.example.domain.shared.ValueObject

data class Id(val value: String): ValueObject<String>(value) {

    init {
        checkIdCantBeEmpty()
    }

    private fun checkIdCantBeEmpty() {
        if (value.isEmpty()) {
            throw IdCantBeEmpty()
        }
    }

}