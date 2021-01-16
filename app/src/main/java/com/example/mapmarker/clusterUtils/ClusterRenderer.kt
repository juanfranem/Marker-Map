package com.example.mapmarker.clusterUtils

import android.content.Context
import com.example.mapmarker.extensions.getMarkerIcon
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer

class ClusterRenderer(context: Context,
                      map: GoogleMap,
                      clusterManager: ClusterManager<MarkerCluster>
) : DefaultClusterRenderer<MarkerCluster>(context, map, clusterManager) {

    override fun onBeforeClusterItemRendered(item: MarkerCluster, markerOptions: MarkerOptions) {
        markerOptions.icon(getMarkerIcon(item.color))
        super.onBeforeClusterItemRendered(item, markerOptions)
    }

}