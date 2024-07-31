package com.example.testapplication.ui.camera

import android.graphics.Bitmap

sealed interface CameraState {

    data object Empty: CameraState

    data class NoEmpty(val bitMap: Bitmap): CameraState

    data object SavePicture: CameraState
}