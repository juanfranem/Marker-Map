package com.example.data.shared.retrofit.response

import com.example.domain.coordinate.Coordinate
import com.example.domain.coordinate.valueObject.Latitude
import com.example.domain.coordinate.valueObject.Longitude
import com.example.domain.marker.Marker
import com.example.domain.marker.valueObject.Company
import com.example.domain.marker.valueObject.Id
import com.example.domain.marker.valueObject.Name

data class AssetMarkerResponse(
    val id: String,
    val name: String?,
    val x: Double,
    val y: Double,
    val companyZoneId: Int
) {

    fun toMarker(): Marker {
        return Marker(Id(id), Company(companyZoneId), Name(name), Coordinate(Latitude(y), Longitude(x)))
    }

}