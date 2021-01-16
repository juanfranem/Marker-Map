package com.example.mapmarker.baseComponents

import java.lang.Exception

sealed class ViewState {
    object LOADING : ViewState()
    object IDLE : ViewState()
    class ERROR(val exception: Exception): ViewState()
}