package com.example.testapplication.ui.album_player

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.testapplication.R
import com.example.testapplication.databinding.FragmentAlbumPlayerBinding
import com.example.testapplication.domain.models.Album
import com.example.testapplication.domain.models.Picture
import com.example.testapplication.ui.picture.PictureFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class AlbumPlayerFragment : Fragment() {

//    private lateinit var album: Album

    private val viewModel by viewModel<AlbumPlayerViewModel>()

    private lateinit var onPictureClick: (Int) -> Unit

    private var _binding: FragmentAlbumPlayerBinding? = null
    private val binding get() = _binding!!

    private val pictures = ArrayList<Int>()

    private val adapter = PicturesAdapter(pictures){id ->
            onPictureClick(id)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAlbumPlayerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.albumList.layoutManager = GridLayoutManager(requireContext(), 2)

        binding.albumList.adapter = adapter


        val nameAlbum = requireArguments().getString(ALBUM_NAME)
        viewModel.fillData(nameAlbum!!)
        viewModel.observeState().observe(viewLifecycleOwner){
            render(it)
        }


        onPictureClick = {
            id ->
            findNavController().navigate(
                R.id.action_albumPlayerFragment_to_pictureFragment,
                PictureFragment.createArgs(id)
            )
        }

    }

    fun render(state: AlbumPlayerState){
        when(state) {
            is AlbumPlayerState.Loading -> {
                binding.nameAlbum.isVisible = false
                binding.albumList.isVisible = false
                binding.progressBar.isVisible = true
            }
            is AlbumPlayerState.Content -> {
                pictures.clear()
                pictures.addAll(state.album.pictures)
                binding.nameAlbum.text = state.album.name
                binding.nameAlbum.isVisible = true
                binding.albumList.isVisible = true
                binding.progressBar.isVisible = false
            }
        }
        adapter.notifyDataSetChanged()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val ALBUM_NAME = "album_name"

        fun createArgs(albumName: String) : Bundle = bundleOf(ALBUM_NAME to albumName)

    }




}