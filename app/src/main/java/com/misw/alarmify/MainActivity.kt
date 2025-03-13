package com.misw.alarmify

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.google.android.material.textfield.TextInputLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var emailLayout: TextInputLayout
    private lateinit var passwordLayout: TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializa las vistas claramente
        emailLayout = findViewById(R.id.textField1)
        passwordLayout = findViewById(R.id.textField2)

        val navigateButton = findViewById<Button>(R.id.loginButton)
        navigateButton.setOnClickListener {
            if (validateFields()) {
                val intent = Intent(this, SecondActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun validateFields(): Boolean {
        val email = emailLayout.editText?.text.toString().trim()
        val password = passwordLayout.editText?.text.toString().trim()

        var valid = true

        if (email.isEmpty()) {
            emailLayout.error = "Introduce tu email"
            valid = false
        } else {
            emailLayout.error = null
        }

        if (password.isEmpty()) {
            passwordLayout.error = "Introduce tu contraseña"
            valid = false
        } else {
            passwordLayout.error = null
        }

        return valid
    }
}