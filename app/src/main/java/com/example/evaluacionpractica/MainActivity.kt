package com.example.evaluacionpractica

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val start = findViewById<Button>(R.id.log_init)
        val exit = findViewById<Button>(R.id.log_exit)

        val name = findViewById<EditText>(R.id.et_log_name)
        val pass = findViewById<EditText>(R.id.et_log_pass)

        start.setOnClickListener{
            val nameText = name.text.toString()
            val passText = pass.text.toString()
            if (nameText == "TM1p25A" && passText == "axel"){
                val intent = Intent(this, MainMenu::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this, "Nombre o contraseña incorrectos",Toast.LENGTH_SHORT).show()
            }
        }

        exit.setOnClickListener {
            Toast.makeText(this, "Hasta Pronto!", Toast.LENGTH_SHORT).show()
            android.os.Handler().postDelayed({
                finishAffinity()
                System.exit(0)
            },2000)
        }
    }
}