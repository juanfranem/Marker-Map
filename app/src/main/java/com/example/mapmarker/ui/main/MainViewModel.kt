package com.example.mapmarker.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.data.marker.useCase.FindMarkersByCityAndCoordinateRange
import com.example.domain.coordinate.Coordinate
import com.example.domain.marker.Marker
import com.example.domain.shared.DomainException
import com.example.mapmarker.baseComponents.BaseViewModel
import com.example.mapmarker.baseComponents.ViewState
import com.example.mapmarker.extensions.toCoordinate
import kotlinx.coroutines.launch

class MainViewModel(
    private val findMarkersByCityAndCoordinateRange: FindMarkersByCityAndCoordinateRange
): BaseViewModel() {

    private val mutableData: MutableLiveData<List<Marker>> = MutableLiveData()
    val data: LiveData<List<Marker>> = mutableData

    fun loadMarker(city: String, neCoordinate: Coordinate, swCoordinate: Coordinate) {
        viewModelScope.launch {
            refreshState(ViewState.LOADING)
            try {
                findMarkersByCityAndCoordinateRange(
                        FindMarkersByCityAndCoordinateRange.Params(
                                city, neCoordinate, swCoordinate
                        ), onError = {
                    refreshState(ViewState.ERROR(it))
                }, onResult = {response ->
                    mutableData.postValue(response)
                    refreshState(ViewState.IDLE)
                })
            } catch (domainException: DomainException) {
                refreshState(ViewState.ERROR(domainException))
            } catch (exception: Exception) {
                refreshState(ViewState.ERROR(exception))
            }

        }
    }

}