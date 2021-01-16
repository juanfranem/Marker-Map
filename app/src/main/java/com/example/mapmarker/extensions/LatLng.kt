package com.example.mapmarker.extensions

import com.example.domain.coordinate.Coordinate
import com.example.domain.coordinate.valueObject.Latitude
import com.example.domain.coordinate.valueObject.Longitude
import com.google.android.gms.maps.model.LatLng

fun LatLng.toCoordinate(): Coordinate {
    return Coordinate(Latitude(this.latitude), Longitude(this.longitude))
}

fun Coordinate.toLatLng(): LatLng {
    return LatLng(this.latitude.value, this.longitude.value)
}