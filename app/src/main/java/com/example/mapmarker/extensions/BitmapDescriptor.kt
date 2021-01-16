package com.example.mapmarker.extensions

import android.graphics.Color
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory


fun getMarkerIcon(color: Int): BitmapDescriptor? {
    val r = color / 100
    val g = (color - (100 * r)) / 10
    val b = (color - (100 * r) - (g * 10))
    val hsv = FloatArray(3)
    Color.colorToHSV( Color.argb(1, r, g, b), hsv)
    return BitmapDescriptorFactory.defaultMarker(hsv[0])
}