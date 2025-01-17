package com.example.testapplication.ui.collection.other

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.testapplication.databinding.FragmentCollectionBinding
import com.google.android.material.tabs.TabLayoutMediator

class CollectionFragment: Fragment() {

    private var _binding : FragmentCollectionBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var tabMediator : TabLayoutMediator


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCollectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = PagerAdapter(childFragmentManager, lifecycle)
        binding.pager.adapter = adapter

        tabMediator = TabLayoutMediator(binding.tabLayout, binding.pager){tab, position ->
            when(position){
                0 -> tab.text = "СОЗДАТЬ НОВЫЙ"
                1 -> tab.text = "АЛЬБОМЫ"
            }
        }

        tabMediator.attach()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        tabMediator.detach()
        _binding = null

    }

}