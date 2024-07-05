package com.bigdatacorpapp.bigdataapp.register

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bigdatacorpapp.bigdataapp.R
import com.bigdatacorpapp.bigdataapp.login.LoginActivity
import com.bigdatacorpapp.bigdataapp.menu.MenuActivity
import com.google.android.material.textfield.TextInputEditText

class RegisterActivity: AppCompatActivity() {

    private lateinit var viewModel: RegisterViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]

        val edtNombres = findViewById<TextInputEditText>(R.id.txtNombreUsuario)
        val edtApellidos = findViewById<TextInputEditText>(R.id.txtApellidosUsuario)
        val edtGenero = findViewById<TextInputEditText>(R.id.txtGeneroUsuario)
        val edtCorreo = findViewById<TextInputEditText>(R.id.txtCorreoUsuario)
        val edtContrasena = findViewById<TextInputEditText>(R.id.txtContrasenaUsuario1)
        val btnCrearCuenta = findViewById<Button>(R.id.btnCrearCuenta)
        val btnRegresar = findViewById<Button>(R.id.btnRegresarCrearCuenta)

        btnCrearCuenta.setOnClickListener {
            val nombres = edtNombres.text.toString()
            val apellidos = edtApellidos.text.toString()
            val genero = edtGenero.text.toString()
            val correo = edtCorreo.text.toString()
            val contrasena = edtContrasena.text.toString()
            viewModel.verifyRegister(nombres, apellidos, genero, correo, contrasena)
        }
        btnRegresar.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        observerLiveData()
    }

    private fun observerLiveData() {
        viewModel.userRegisterStatus.observe(this) {
            if (it) {
                Toast.makeText(this, "Usuario Registrado correctamente",
                    Toast.LENGTH_SHORT).show()
                // Ir a ventana de Inicio de sesión
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Verifique los datos ingresados",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }

    
}