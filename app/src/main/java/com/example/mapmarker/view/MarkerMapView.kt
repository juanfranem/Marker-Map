package com.example.mapmarker.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.fragment.app.FragmentManager
import com.example.domain.coordinate.Coordinate
import com.example.domain.coordinate.valueObject.Latitude
import com.example.domain.coordinate.valueObject.Longitude
import com.example.domain.marker.Marker
import com.example.mapmarker.R
import com.example.mapmarker.databinding.MarkerMapViewBinding
import com.example.mapmarker.extensions.toLatLng
import com.example.mapmarker.clusterUtils.ClusterRenderer
import com.example.mapmarker.clusterUtils.MarkerCluster
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLngBounds
import com.google.maps.android.clustering.ClusterManager
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MarkerMapView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), OnMapReadyCallback, CoroutineScope {

    private lateinit var clusterManager: ClusterManager<MarkerCluster>
    private lateinit var map: GoogleMap
    private var markerMapInterface: MarkerMapListener? = null
    private var lastLngBounds: LatLngBounds? = null

    private val binding =
        MarkerMapViewBinding.inflate(LayoutInflater.from(context), this, true)

    fun init(fragmentManager: FragmentManager) {
        val mapFragment = fragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        markerMapInterface?.onMapReady()
        clusterManager = ClusterManager(context, map)
        clusterManager.renderer = ClusterRenderer(context, map, clusterManager)
        map.setOnCameraIdleListener(clusterManager)
        map.setOnMarkerClickListener(clusterManager)
        val lisboaCoordinate = Coordinate(Latitude(38.736946), Longitude(-9.142685))
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(lisboaCoordinate.toLatLng(), 15f))
        map.uiSettings.isZoomControlsEnabled = true
        map.uiSettings.isMapToolbarEnabled = true
        map.setOnCameraIdleListener {
            map.projection.visibleRegion.latLngBounds.let {
                if (lastLngBounds != it) {
                    markerMapInterface?.onCameraIdle(it)
                    lastLngBounds = it
                }
            }

        }
    }

    fun setMarkerMapListener(listener: MarkerMapListener) {
        this.markerMapInterface = listener
    }

    fun addMarker(markers: List<Marker>) {
        val newIds = markers.map { it.id.value }
        val clusterIncludes = clusterManager.algorithm.items.filter { !newIds.contains(it.id) }
        clusterManager.removeItems(clusterIncludes)
        val includesIds = clusterManager.algorithm.items.map { it.id }
        clusterManager.addItems(markers.filter { !includesIds.contains(it.id.value) }.map { MarkerCluster.fromMarker(it) })
        clusterManager.cluster()
    }


    @SuppressLint("MissingPermission")
    fun setLocationEnabled(value: Boolean) {
        map.isMyLocationEnabled = value
    }

    interface MarkerMapListener {
        fun onMapReady()
        fun onCameraIdle(latLngBounds: LatLngBounds)
    }

    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.Main

}