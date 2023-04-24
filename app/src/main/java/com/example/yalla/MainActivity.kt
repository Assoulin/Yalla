package com.example.yalla

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.yalla.databinding.ActivityMainBinding
import com.example.yalla.models.Destination
import com.example.yalla.utils.hideBnv
import com.example.yalla.utils.hideTopLine


class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    lateinit var binding: ActivityMainBinding
    private lateinit var navView: BottomNavigationView
    var chosenDestination: Destination? = null


    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar!!.hide()

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navView = binding.navView

        navController = findNavController(R.id.nav_host_fragment_activity_main)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_search,
                R.id.navigation_surprise,
                R.id.navigation_yalla_specials,
                R.id.navigation_restaurants
            )
        )

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id != R.id.navigation_home) {
                setupActionBarWithNavController(navController, appBarConfiguration)
                navView.setupWithNavController(navController)
            }
        }

        binding.arrowBack.setOnClickListener {
            navController.popBackStack()
        }

        binding.fabSettings.setOnClickListener {
            navController.navigate(R.id.chooseDestinationFragment)
            hideTopLine()
            hideBnv()
        }
        binding.btnGoToCart.setOnClickListener {
            navController.navigate(R.id.cartFragment)
            hideBnv()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        navController = findNavController(R.id.nav_host_fragment_activity_main)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }



}