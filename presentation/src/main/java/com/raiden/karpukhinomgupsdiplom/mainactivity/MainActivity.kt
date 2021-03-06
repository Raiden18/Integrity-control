package com.raiden.karpukhinomgupsdiplom.mainactivity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.livetyping.permission.PermissionBinder
import com.raiden.karpukhinomgupsdiplom.R
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private val permissionBinder: PermissionBinder by inject()

    override fun onStart() {
        permissionBinder.attach(this)
        super.onStart()
    }

    override fun onStop() {
        permissionBinder.detach(this)
        super.onStop()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        permissionBinder.onActivityResult(requestCode, data, this)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        permissionBinder.onRequestPermissionResult(requestCode, grantResults)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        setupNavigation()
    }

    private fun setupNavigation() {
        val navController = findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.infoFragment -> {
                    setUpToolbarForInfoScreen()
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.info_screen_menu, menu)
        return true
    }

    private fun setUpToolbarForInfoScreen() {
        toolbar.navigationIcon = null
    }
}
