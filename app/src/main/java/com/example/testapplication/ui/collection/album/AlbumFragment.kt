package com.example.testapplication.ui.collection.album

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.testapplication.databinding.FragmentAlbumBinding
import com.example.testapplication.domain.models.Album
import org.koin.androidx.viewmodel.ext.android.viewModel

class AlbumFragment: Fragment() {

    private var _binding : FragmentAlbumBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel by viewModel<AlbumViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAlbumBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.createAlbumButton.setOnClickListener {
            if(binding.editText.text.isNotBlank()){
                val albumIsAdded = viewModel.addAlbum(binding.editText.text.toString())
                if (albumIsAdded){
                    Toast.makeText(requireContext(), "Альбом добавлен", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "Такой альбом уже существует увы", Toast.LENGTH_SHORT).show()
                }
            }

        }

        binding.openAlbumButton.setOnClickListener {
            val album = viewModel.getAlbum(binding.editText.text.toString())
            if (album is Album){
                Toast.makeText(requireContext(), "Альбом ${album.name} получен", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Ошибка", Toast.LENGTH_SHORT).show()
            }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}