package com.example.navigation_coding_flow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.*
import com.example.navigation_coding_flow.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()

        //AppBarConfiguration is used to remove the up bar navigation in the provided resources file
        //it uses here bez when we go to the search par there also the top up navigation bar was showing
        //so for solving this we uses it for removing
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.homeFragment, R.id.searchFragment),
            binding.drawerLayout
        )

        //here in the setupActionBarWithNavController we passes appBarConfig bez we want to works our
        //navController according to the define above code
        setSupportActionBar(binding.toobar)
        setupActionBarWithNavController(
            navController,
            appBarConfiguration
        ) //it help the action bar to move according to the navigation graph

        //setupWithNavController is used for working the bottom nav view according to the nav controller
        //but it only works when the bottom nav bar element has the same id as define there fragment in the navigation graph
        //it simple take the nav bar element id and compare there nav graph fragment id if match then it will move to that fragment
        binding.bottomNav.setupWithNavController(navController)

        //it uses for creating the drawer menu element movement according to navigation controller
        binding.navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //if user will click on the terms menu then will move according to the define definition
        return if (item.itemId == R.id.termsAndCondition) {
            val action = NavGraphDirections.actionGlobalTermsFragment()
            navController.navigate(action)

            true
        }else{
            item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        //here without passing the appBarConfiguration in the navigateUp() the drawer menu will not be visible and handle according to the navigation Controller
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        if(binding.drawerLayout.isDrawerOpen(GravityCompat.START)){
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        }else{
            super.onBackPressed()
        }
    }
}