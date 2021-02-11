package com.example.mapmarker.baseComponents

import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import kotlin.random.Random

class PermissionRequest(
    private val activity: Activity,
    private val permissions: Array<String>,
    private val listener: OnPermissionRequestResult) {

    private val REQUEST_CODE = Random.nextInt(400, 999)

    fun checkPermissions() {
        if (permissions.map { ActivityCompat.checkSelfPermission(activity, it) == PackageManager.PERMISSION_GRANTED }.all{ it }) {
            listener.permissionGranted()
        } else {
            ActivityCompat.requestPermissions(activity, permissions, REQUEST_CODE)
        }
    }

    fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>) {
        if (requestCode == REQUEST_CODE) {
            if (permissions.map { ActivityCompat.checkSelfPermission(activity, it) == PackageManager.PERMISSION_GRANTED }.all{ it }) {
                listener.permissionGranted()
            } else {
                listener.permissionDenied()
            }
        }
    }

    interface OnPermissionRequestResult {
        fun permissionGranted()
        fun permissionDenied()
    }

}