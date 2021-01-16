package com.example.mapmarker.baseComponents

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel: ViewModel() {

    private val mutableState = MutableLiveData<ViewState>()
    val state: LiveData<ViewState> = mutableState

    fun refreshState(state: ViewState) {
        mutableState.postValue(state)
    }

}