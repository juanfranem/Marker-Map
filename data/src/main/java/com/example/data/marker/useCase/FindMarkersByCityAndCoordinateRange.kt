package com.example.data.marker.useCase

import com.example.data.shared.useCase.UseCase
import com.example.domain.coordinate.Coordinate
import com.example.domain.marker.Marker
import com.example.domain.marker.MarkerRepository

class FindMarkersByCityAndCoordinateRange(
    private val markerRepository: MarkerRepository
): UseCase<List<Marker>, FindMarkersByCityAndCoordinateRange.Params>() {

    data class Params(
        val city: String,
        val lowerLeftCoordinate: Coordinate,
        val upperRightCoordinate: Coordinate,
    )

    override suspend fun execute(params: Params): List<Marker> {
        return markerRepository.findMarkersInRange(params.lowerLeftCoordinate,
            params.upperRightCoordinate, params.city)
    }

}