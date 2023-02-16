package com.example.yalla

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.yalla.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    lateinit var binding: ActivityMainBinding
    private lateinit var navView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar!!.hide();
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navView = binding.navView

        navController = findNavController(R.id.nav_host_fragment_activity_main)
        val graph = navController.graph
        graph.setStartDestination(R.id.chooseDestinationFragment)
        navController.setGraph(graph, null)


        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
//                R.id.chooseDestinationFragment,
                R.id.navigation_search,
                R.id.navigation_surprise,
                R.id.navigation_yalla_specials,
                R.id.navigation_restaurants
            )
        )

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id != R.id.chooseDestinationFragment) {
                setupActionBarWithNavController(navController, appBarConfiguration)
                navView.setupWithNavController(navController)
            }
        }

        binding.toolbar.arrowBack.setOnClickListener {
            navController.popBackStack()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        navController = findNavController(R.id.nav_host_fragment_activity_main)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}