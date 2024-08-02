package com.example.testapplication.ui.collection.album

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.testapplication.R
import com.example.testapplication.databinding.FragmentAlbumBinding
import com.example.testapplication.domain.models.Album
import com.example.testapplication.ui.album_player.AlbumPlayerFragment
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
            val name = binding.editText.text.toString()
            if (viewModel.albumIsExist(name)){
                Toast.makeText(requireContext(), "Альбом ${name} получен", Toast.LENGTH_SHORT).show()
                findNavController().navigate(
                    R.id.action_albumFragment_to_albumPlayerFragment,
//                    AlbumPlayerFragment.createArgs(name)
                )

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