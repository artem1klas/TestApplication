package com.example.testapplication.ui.camera

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.testapplication.R
import com.example.testapplication.databinding.FragmentCameraBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File
import java.io.FileOutputStream

class CameraFragment : Fragment() {

    private var _binding: FragmentCameraBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel by viewModel<CameraViewModel>()

    var filePhoto: Bitmap? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCameraBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.observeState().observe(viewLifecycleOwner) {
            render(it)
        }

        binding.buttonMakePhoto.setOnClickListener {
            takePhoto()
        }

        binding.buttonSavePhoto.setOnClickListener {
            viewModel.changeState(CameraState.SavePicture)
        }

        binding.createAlbumButton.setOnClickListener {
            val nameAlbum = binding.editText.text.toString()
            val id =  viewModel.addPicture(nameAlbum, true)
            if (nameAlbum.isNotBlank()){
               if (id == null){
                   Toast.makeText(requireContext(), "Альбом уже существует", Toast.LENGTH_LONG)
               } else {
                   saveImage(id)
               }
            }
        }

        binding.openAlbumButton.setOnClickListener {
            val nameAlbum = binding.editText.text.toString()
            val id =  viewModel.addPicture(nameAlbum, false)
            if (nameAlbum.isNotBlank()){
                if (id == null){
                    Toast.makeText(requireContext(), "Такого альбома нет", Toast.LENGTH_LONG)
                } else {
                    saveImage(id)
                }
            }
        }

        binding.buttonClose.setOnClickListener {
            viewModel.changeState(CameraState.Empty)
        }
    }

    private fun takePhoto() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            val thumbnailBitmap = data?.extras?.get("data") as Bitmap
            filePhoto = thumbnailBitmap
            viewModel.changeState(CameraState.NoEmpty(thumbnailBitmap))
        }
    }

    private fun saveImage(id: Int) {

        val file =
            File(requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES), ".jpg")
        val outputStream = FileOutputStream(file)
        if (filePhoto != null) {
            filePhoto!!.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        }
    }



    @SuppressLint("ResourceType")
    private fun render(state: CameraState) {
        when (state) {
            is CameraState.Empty -> {
                binding.buttonMakePhoto.text = "СДЕЛАТЬ ФОТО"
                binding.buttonMakePhoto.isVisible = true
                binding.imageCamera.setImageResource(R.drawable.camera)
                binding.imageCamera.isVisible = true
                binding.buttonClose.isVisible = false
                binding.buttonSavePhoto.isVisible = false
                binding.savePhoto.isVisible = false
            }

            is CameraState.NoEmpty -> {
                binding.buttonMakePhoto.text = "Переделать фото"
                binding.buttonMakePhoto.isVisible = true
                binding.imageCamera.setImageBitmap(state.bitMap)
                binding.imageCamera.isVisible = true
                binding.buttonClose.isVisible = true
                binding.buttonSavePhoto.isVisible = true
                binding.savePhoto.isVisible = false
            }

            is CameraState.SavePicture -> {
                binding.buttonMakePhoto.isVisible = false
                binding.imageCamera.isVisible = false
                binding.buttonClose.isVisible = false
                binding.buttonSavePhoto.isVisible = false
                binding.savePhoto.isVisible = false
                binding.savePhoto.isVisible = true
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private val REQUEST_TAKE_PHOTO = 1
    }


}