package com.example.domain.marker.valueObject

import com.example.domain.marker.exception.IdCantBeEmpty
import com.example.domain.marker.exception.NameCantBeEmpty
import com.example.domain.shared.ValueObject

data class Name(val value: String?): ValueObject<String?>(value) {

    init {
        checkNameCantBeEmpty()
    }

    private fun checkNameCantBeEmpty() {
        if (value.isNullOrEmpty()) {
            throw NameCantBeEmpty()
        }
    }

}