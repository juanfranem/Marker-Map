package com.example.domain.marker.valueObject

import com.example.domain.marker.exception.CompanyMustBeMoreThanZero
import com.example.domain.shared.ValueObject

data class Company(val value: Int): ValueObject<Int>(value) {

    init {
        checkCompanyMustBeMoreThanZero()
    }

    private fun checkCompanyMustBeMoreThanZero() {
        if (value < 1) {
            throw CompanyMustBeMoreThanZero()
        }
    }

}