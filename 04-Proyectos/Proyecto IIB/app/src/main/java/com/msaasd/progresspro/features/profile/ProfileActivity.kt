package com.msaasd.progresspro.features.profile

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ProgressBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.google.android.material.navigation.NavigationView
import com.msaasd.progresspro.R
import com.msaasd.progresspro.features.history.HistoryActivity
import com.msaasd.progresspro.features.task.TaskActivity
import com.msaasd.progresspro.features.task.TaskViewModel
import com.msaasd.progresspro.models.daos.TaskDao
import com.msaasd.progresspro.models.database.ProgressProDatabase
import com.msaasd.progresspro.repositories.TaskRepository

class ProfileActivity : AppCompatActivity(){
    private lateinit var drawer: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var taskViewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        drawer = findViewById(R.id.drawer_layout)

        val toggle = ActionBarDrawerToggle(
            this, drawer, toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()


        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        // Establece el valor máximo de progreso (100)
        progressBar.max = 100
        // Valor de progreso deseado (70)
        val targetProgress = 70

//        val targetProgress: Int
//
//        val allTaskCount = taskViewModel.getTaskCount(1).toString().toInt()
//        val doneTaskCount = taskViewModel.getDoneTasks(1).toString().toInt()
//
//        if (allTaskCount != 0) {
//            targetProgress = doneTaskCount * 100 / allTaskCount
//        } else {
//            targetProgress = 0
//        }

        // Crea un ObjectAnimator para animar el progreso
        val animator = ObjectAnimator.ofInt(progressBar, "progress", targetProgress)
        // Establece la duración de la animación en milisegundos
        animator.duration = 5000 // 5000 milisegundos (5 segundos)
        // Inicia la animación
        animator.start()

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