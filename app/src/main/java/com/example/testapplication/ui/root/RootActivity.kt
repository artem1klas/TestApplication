package com.example.testapplication.ui.root

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.testapplication.R
import com.example.testapplication.databinding.ActivityRootBinding

class RootActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRootBinding


    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRootBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        val navController = navHostFragment.navController


        binding.bottomNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id) {
                R.id.albumPlayerFragment -> {
                    binding.bottomNavigationView.visibility = View.GONE
                }
                else -> {
                    binding.bottomNavigationView.visibility = View.VISIBLE
                }
            }

        }

//        if (savedInstanceState == null) { ....... this was a reason for my misunderspanding
//            supportFragmentManager.commit {
//                add(R.id.fragment_container, AlbumFragment())
//            }
////            supportFragmentManager.beginTransaction()
////                .add(R.id.fragment_container, AlbumFragment())
////                .commit()
////        }


        }
    }
