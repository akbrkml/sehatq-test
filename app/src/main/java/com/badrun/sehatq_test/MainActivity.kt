package com.badrun.sehatq_test

import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.badrun.sehatq_test.base.BaseActivity
import com.badrun.sehatq_test.databinding.MainActivityBinding


class MainActivity : BaseActivity<MainActivityBinding>() {

    override val bindingInflater: (LayoutInflater) -> MainActivityBinding
        get() = MainActivityBinding::inflate

    override fun onCreated(state: Bundle?) {
        setUpNavigation()
    }

    private fun setUpNavigation() {
        val navController = findNavController(R.id.nav_host_main)
        NavigationUI.setupWithNavController(
            binding.bottomNav,
            navController
        )
        setupActionBarWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id) {
                R.id.mainFragment, R.id.historyFragment -> binding.bottomNav.visibility = View.VISIBLE
                else -> binding.bottomNav.visibility = View.GONE
            }
        }
    }
}