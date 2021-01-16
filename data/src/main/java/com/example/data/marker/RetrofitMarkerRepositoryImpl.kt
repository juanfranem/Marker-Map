package com.example.data.marker

import com.example.data.shared.retrofit.config.Api
import com.example.domain.coordinate.Coordinate
import com.example.domain.marker.Marker
import com.example.domain.marker.MarkerRepository

class RetrofitMarkerRepositoryImpl(
    private val api: Api
): MarkerRepository {

    override suspend fun findMarkersInRange(
        lowerLeftCoordinate: Coordinate,
        upperRightCoordinate: Coordinate,
        city: String): List<Marker> {
        return api.markers(city.toLowerCase(), lowerLeftCoordinate.toString(), upperRightCoordinate.toString())
            .filter { !it.name.isNullOrEmpty() }
            .map { it.toMarker() }
    }

}