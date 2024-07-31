package com.example.testapplication.ui.camera

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.provider.MediaStore
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testapplication.domain.api_impl.AlbumInteractorImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking

class CameraViewModel(private val albumInteractorImpl: AlbumInteractorImpl) : ViewModel() {


    private val liveData = MutableLiveData<CameraState>(CameraState.Empty)
    fun observeState(): LiveData<CameraState> = liveData


    fun changeState(cameraState: CameraState){
        liveData.postValue(cameraState)
    }

    fun addPicture(nameAlbum: String, addToNewAlbum: Boolean): Int? {
        return albumInteractorImpl.addPicture(nameAlbum, addToNewAlbum)
    }


}