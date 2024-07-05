package com.bigdatacorpapp.bigdataapp.recuperar

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bigdatacorpapp.bigdataapp.R
import com.google.firebase.auth.FirebaseAuth

class RecuperarActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clave)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        val email = findViewById<EditText>(R.id.Icorreo)
        val btnRecuperar = findViewById<Button>(R.id.btnRecuperar)

        btnRecuperar.setOnClickListener {
            val emailAddress = email.text.toString().trim()

            if (emailAddress.isEmpty()) {
                Toast.makeText(this, "Por favor, ingrese un correo electrónico", Toast.LENGTH_SHORT).show()
            } else {
                resetPassword(emailAddress)
            }
        }
    }

    private fun resetPassword(email: String) {
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Correo de recuperación enviado", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Error al enviar el correo de recuperación", Toast.LENGTH_SHORT).show()
                }
            }
    }
}