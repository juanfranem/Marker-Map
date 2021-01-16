package com.example.mapmarker.ui.main

import android.Manifest
import android.location.Geocoder
import android.view.LayoutInflater
import androidx.core.view.isVisible
import com.example.domain.coordinate.Coordinate
import com.example.mapmarker.R
import com.example.mapmarker.baseComponents.BaseActivity
import com.example.mapmarker.baseComponents.PermissionRequest
import com.example.mapmarker.baseComponents.ViewState
import com.example.mapmarker.databinding.ActivityMainBinding
import com.example.mapmarker.exceptions.PermissionNotGrantedException
import com.example.mapmarker.extensions.error
import com.example.mapmarker.extensions.toCoordinate
import com.example.mapmarker.view.MarkerMapView
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class MainActivity: BaseActivity<ActivityMainBinding, MainViewModel>(), MarkerMapView.MarkerMapListener, PermissionRequest.OnPermissionRequestResult {
    override val viewModel: MainViewModel by viewModel()

    private lateinit var locationPermissionRequest: PermissionRequest

    override fun subscribe() {
        viewModel.state.observe(this) {
            when (it) {
                is ViewState.IDLE -> {
                    binding.loading.isVisible = false
                }
                is ViewState.ERROR -> {
                    binding.loading.isVisible = false
                    showError(it.exception)
                }
                is ViewState.LOADING -> {
                    binding.loading.isVisible = true
                }
            }
        }
        viewModel.data.observe(this) {
            binding.mapView.addMarker(it)
        }
    }

    private fun showError(exception: Exception) {
        Snackbar.make(binding.root, exception.error(), Snackbar.LENGTH_SHORT).show()
    }

    override fun setup() {
        locationPermissionRequest = PermissionRequest(this,
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION), this)
        binding.mapView.init(supportFragmentManager)
        binding.mapView.setMarkerMapListener(this)
    }

    override fun attachBinding(layoutInflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    private fun getCityFromCoordinates(coordinate: Coordinate): String {
        val addresses = Geocoder(this, Locale.getDefault())
            .getFromLocation(coordinate.latitude.value, coordinate.longitude.value, 1)
        return addresses.firstOrNull()?.locality ?: resources.getString(R.string.default_location)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        locationPermissionRequest.onRequestPermissionsResult(requestCode, permissions)
    }

    override fun permissionGranted() {
        binding.mapView.setLocationEnabled(true)
    }

    override fun permissionDenied() {
        showError(PermissionNotGrantedException())
    }

    override fun onMapReady() {
        locationPermissionRequest.checkPermissions()
    }

    override fun onCameraIdle(latLngBounds: LatLngBounds) {
        val city = getCityFromCoordinates(latLngBounds.center.toCoordinate())
        viewModel.loadMarker(city, latLngBounds.northeast.toCoordinate(), latLngBounds.southwest.toCoordinate())
    }


}