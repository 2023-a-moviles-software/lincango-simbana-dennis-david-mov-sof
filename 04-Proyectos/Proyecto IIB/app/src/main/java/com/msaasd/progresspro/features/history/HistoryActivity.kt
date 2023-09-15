package com.msaasd.progresspro.features.history

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.msaasd.progresspro.R
import com.msaasd.progresspro.features.profile.ProfileActivity
import com.msaasd.progresspro.features.task.TaskActivity
import ir.mahozad.android.PieChart

class HistoryActivity : AppCompatActivity() {
    private lateinit var drawer: DrawerLayout
    private lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.history)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        drawer = findViewById(R.id.drawer_layout)

        val toggle = ActionBarDrawerToggle(
            this, drawer, toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()


        val pieChart = findViewById<PieChart>(R.id.pieChart)

        pieChart.slices = listOf(
            PieChart.Slice(0.6f, Color.BLUE),
            PieChart.Slice(0.4f, Color.GREEN)
        )
        pieChart.invalidate()

        navView = findViewById(R.id.nav_view)
        navView.getHeaderView(0).findViewById<ImageButton>(R.id.imageProfileButtonNav).setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
        // Manejar las selecciones de elementos del menú
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_task -> {
                    val intent = Intent(this, TaskActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_history -> {
                    val intent = Intent(this, HistoryActivity::class.java)
                    startActivity(intent)
                }
                else -> {
                    super.onContextItemSelected(menuItem)
                }
            }
            // Cierra el cajón de navegación
            drawer.closeDrawers()
            true
        }

    }
}