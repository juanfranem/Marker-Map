package com.example.domain.marker

import com.example.domain.coordinate.Coordinate
import com.example.domain.marker.valueObject.Company
import com.example.domain.marker.valueObject.Id
import com.example.domain.marker.valueObject.Name

data class Marker(
    val id: Id,
    val company: Company,
    val name: Name,
    val coordinate: Coordinate
)