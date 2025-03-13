package com.misw.alarmify

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
        enableEdgeToEdge()
        setContentView(R.layout.second_activity)

        // Aplicar correctamente Insets al contenedor principal
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Configura la toolbar superior
        val toolbar = findViewById<MaterialToolbar>(R.id.topAppBar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        // Manejar el clic en la flecha (navegación hacia atrás)
        toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // Cargar fragment inicial (HomeFragment)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment())
                .commit()
        }

        // Bottom Navigation
        val bottomNavigation = findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(R.id.bottom_navigation)

        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home_nav_button -> {
                    toolbar.title = "Home"
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, HomeFragment())
                        .commit()
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                    true
                }
                R.id.alarms_nav_button -> {
                    toolbar.title = "Alarmas"
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, AlarmsFragment())
                        .commit()
                    true
                }
                R.id.settings_nav_button -> {
                    toolbar.title = "Ajustes"
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, SettingsFragment())
                        .commit()
                    true
                }
                R.id.logout_nav_button -> { // Navegación a Activity
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }
}