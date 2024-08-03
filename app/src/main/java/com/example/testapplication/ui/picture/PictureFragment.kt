package com.example.testapplication.ui.picture

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.testapplication.R
import com.example.testapplication.databinding.FragmentAlbumBinding
import com.example.testapplication.databinding.FragmentPictureBinding
import java.io.File

class PictureFragment : Fragment() {

    private var _binding: FragmentPictureBinding? = null

    private var pictureId : Int? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPictureBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pictureId = requireArguments().getInt(PICTURE_ID)

        val file =
            File(requireContext().filesDir, "$pictureId.jpg")

        val bitmap: Bitmap = BitmapFactory.decodeFile(file.absolutePath)



        Glide.with(requireContext())
            .load(bitmap)
            .placeholder(R.drawable.close)
            .transform(CenterCrop())
            .into(binding.image)

        binding.close.setOnClickListener{
            findNavController().navigateUp()
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



    companion object {
        const val PICTURE_ID = "picture_id"

        fun createArgs(id: Int) = bundleOf(PICTURE_ID to id)
    }
}