package com.example.testapplication.ui.collection.other

import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.testapplication.databinding.FragmentNoticeBinding
import java.io.File

class NoticeFragment : Fragment() {

    private var _binding: FragmentNoticeBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNoticeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val file = File(requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES), "test.jpg")
        if (file.exists()) {
            val inputStream = requireActivity().contentResolver.openInputStream(Uri.fromFile(file))
            val bitMap = BitmapFactory.decodeStream(inputStream)
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
