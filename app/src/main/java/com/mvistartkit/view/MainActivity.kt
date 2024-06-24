package com.mvistartkit.view

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.mvistartkit.R
import com.mvistartkit.base.BaseActivity
import com.mvistartkit.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    override fun init() {
        initBottomNavigation()
    }

    private fun initBottomNavigation() {
        if (this::navController.isInitialized.not()) {
            navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_main) as NavHostFragment
            navController = navHostFragment.navController

            navController.apply {
                setGraph(R.navigation.nav_graph_main)
                binding.bnMain.setupWithNavController(this)
            }
        }
    }
}
