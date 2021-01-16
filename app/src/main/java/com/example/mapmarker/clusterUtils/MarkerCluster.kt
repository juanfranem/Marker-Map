package com.example.mapmarker.clusterUtils

import com.example.domain.marker.Marker
import com.example.mapmarker.extensions.toLatLng
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

class MarkerCluster(
    val id: String,
    val color: Int,
    private val latLng: LatLng,
    private val title: String
): ClusterItem {

    override fun getPosition(): LatLng = latLng

    override fun getTitle(): String? = title

    override fun getSnippet(): String? = title

    companion object {
        fun fromMarker(marker: Marker): MarkerCluster {
            return MarkerCluster(marker.id.value, marker.company.value, marker.coordinate.toLatLng(), marker.name.value.orEmpty())
        }
    }
}