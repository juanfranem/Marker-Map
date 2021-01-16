package com.example.domain.marker

import com.example.domain.coordinate.Coordinate

interface MarkerRepository {
    suspend fun findMarkersInRange(
        lowerLeftCoordinate: Coordinate,
        upperRightCoordinate: Coordinate,
        city: String): List<Marker>
}