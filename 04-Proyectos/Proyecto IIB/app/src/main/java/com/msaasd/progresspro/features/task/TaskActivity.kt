package com.msaasd.progresspro.features.task

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.ListView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.msaasd.progresspro.R
import com.msaasd.progresspro.features.history.HistoryActivity
import com.msaasd.progresspro.features.profile.ProfileActivity

class TaskActivity : AppCompatActivity() {
    private lateinit var drawer: DrawerLayout
    private lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.task)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        drawer = findViewById(R.id.drawer_layout)

        val toggle = ActionBarDrawerToggle(
            this, drawer, toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()


        val listView: ListView = findViewById(R.id.listTask)
        // Datos de ejemplo (puedes reemplazarlos con tus propios datos)
        val items = arrayOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5")
        // Crear un adaptador personalizado
        val adapter = ArrayAdapter<String>(this, R.layout.list_item, R.id.textViewTask, items)
        // Establecer el adaptador en el ListView
        listView.adapter = adapter

        val buttonMoveToAddTaskActivity = findViewById<ImageButton>(R.id.imageButtonMoveToAddTaskActivity)
        buttonMoveToAddTaskActivity.setOnClickListener {
            val intent = Intent(this, AddTaskActivity::class.java)
            startActivity(intent)
        }

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