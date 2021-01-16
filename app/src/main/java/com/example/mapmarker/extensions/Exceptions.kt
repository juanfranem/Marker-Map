package com.example.mapmarker.extensions

import com.example.domain.coordinate.exception.LatitudeIsNotValid
import com.example.domain.coordinate.exception.LongitudeIsNotValid
import com.example.domain.marker.exception.CompanyMustBeMoreThanZero
import com.example.domain.marker.exception.IdCantBeEmpty
import com.example.domain.marker.exception.NameCantBeEmpty
import com.example.mapmarker.R
import com.example.mapmarker.exceptions.PermissionNotGrantedException
import java.lang.Exception

fun Exception.error(): Int {
    return when (this) {
        is LatitudeIsNotValid -> R.string.latitude_exception
        is LongitudeIsNotValid -> R.string.longitude_exception
        is CompanyMustBeMoreThanZero -> R.string.company_exception
        is IdCantBeEmpty -> R.string.id_exception
        is NameCantBeEmpty -> R.string.name_exception
        is PermissionNotGrantedException -> R.string.permission_exception
        else -> R.string.app_exception
    }
}