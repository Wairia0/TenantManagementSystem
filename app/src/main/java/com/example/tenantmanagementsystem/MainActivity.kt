
package com.example.tenantmanagementsystem

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.tenantmanagementsystem.databinding.ActivityMainBinding
//import com.example.tenantmanagementsystem.databinding.NavHeaderBinding
import android.widget.TextView
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("TENANT_LIFECYCLE", "MainActivity: onCreate")

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Use the Toolbar as the App Bar
        setSupportActionBar(binding.toolbar)

        // 2. Get the NavController from the NavHost
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = navHostFragment.navController

        // 3. Configure top-level destinations and the drawer
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.dashboardFragment,
                R.id.addTenantFragment,
                R.id.viewTenantsFragment,
                R.id.aboutFragment
            ),
            binding.drawerLayout
        )

        // 4. Connect the App Bar to navigation
        setupActionBarWithNavController(
            navController,
            appBarConfiguration
        )

        // 5. Display the signed-in user's email in the drawer header
        val email = intent.getStringExtra("EMAIL")

        val headerView = binding.navView.getHeaderView(0)
        val headerEmail = headerView.findViewById<TextView>(R.id.headerEmailTextView)

        headerEmail.text = email ?: "Signed in"

        // 6. Handle drawer selections, including Logout
        binding.navView.setNavigationItemSelectedListener { item ->

            when (item.itemId) {

                R.id.action_logout -> {
                    val logoutIntent = Intent(
                        this,
                        LoginActivity::class.java
                    )

                    logoutIntent.flags =
                        Intent.FLAG_ACTIVITY_NEW_TASK or
                                Intent.FLAG_ACTIVITY_CLEAR_TASK

                    startActivity(logoutIntent)
                    true
                }

                else -> {
                    val handled =
                        item.onNavDestinationSelected(navController)

                    binding.drawerLayout.closeDrawers()
                    handled
                }
            }
        }

        // 7. Connect BottomNavigationView to the same NavController
        binding.bottomNav.setupWithNavController(navController)
    }

    // Handle the Toolbar's Up button and drawer indicator
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) ||
                super.onSupportNavigateUp()
    }

    // Load main_menu.xml into the Toolbar
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    // Handle Toolbar menu selections
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.action_logout) {
            val logoutIntent = Intent(
                this,
                LoginActivity::class.java
            )

            logoutIntent.flags =
                Intent.FLAG_ACTIVITY_NEW_TASK or
                        Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(logoutIntent)
            return true
        }

        return item.onNavDestinationSelected(navController) ||
                super.onOptionsItemSelected(item)
    }

    // Keep your existing lifecycle logging methods here if required
}
