package com.bigdatacorpapp.bigdataapp.recuperar

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bigdatacorpapp.bigdataapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ResetPasswordActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        val recoveryCode = findViewById<EditText>(R.id.recoveryCode)
        val newPassword = findViewById<EditText>(R.id.newPassword)
        val confirmPassword = findViewById<EditText>(R.id.confirmPassword)
        val btnEnviar = findViewById<Button>(R.id.btnEnviar)

        btnEnviar.setOnClickListener {
            val recoveryCodeText = recoveryCode.text.toString().trim()
            val newPasswordText = newPassword.text.toString().trim()
            val confirmPasswordText = confirmPassword.text.toString().trim()

            if (recoveryCodeText.isNotEmpty() && newPasswordText.isNotEmpty() && confirmPasswordText.isNotEmpty()) {
                if (newPasswordText == confirmPasswordText) {
                    validateRecoveryCodeAndResetPassword(recoveryCodeText, newPasswordText)
                } else {
                    Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun validateRecoveryCodeAndResetPassword(code: String, newPassword: String) {
        val email = auth.currentUser?.email ?: return

        db.collection("recoveryCodes").document(email)
            .get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val storedCode = document.getString("code")
                    if (storedCode == code) {
                        auth.currentUser?.updatePassword(newPassword)
                            ?.addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    Toast.makeText(this, "Contraseña restablecida con éxito", Toast.LENGTH_SHORT).show()
                                } else {
                                    val errorMessage = task.exception?.message ?: "Error desconocido"
                                    Toast.makeText(this, "Error al restablecer la contraseña: $errorMessage", Toast.LENGTH_SHORT).show()
                                }
                            }
                    } else {
                        Toast.makeText(this, "Código de recuperación incorrecto", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "No se encontró un código de recuperación para este correo", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener {
                Toast.makeText(this, "Error al validar el código de recuperación", Toast.LENGTH_SHORT).show()
            }
    }
}