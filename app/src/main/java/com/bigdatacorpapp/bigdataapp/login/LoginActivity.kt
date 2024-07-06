package com.bigdatacorpapp.bigdataapp.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bigdatacorpapp.bigdataapp.R
import com.bigdatacorpapp.bigdataapp.menu.MenuActivity
import com.bigdatacorpapp.bigdataapp.recuperar.RecuperarActivity
import com.bigdatacorpapp.bigdataapp.register.RegisterActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        val email = findViewById<EditText>(R.id.txtCorreoLogin)
        val password = findViewById<EditText>(R.id.txtContrasenaLogin)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val olvpassword = findViewById<TextView>(R.id.olvidasteContra)
        val btnRegistrar = findViewById<Button>(R.id.btnCrearCuenta)

        btnLogin.setOnClickListener {
            val emailValue = email.text.toString()
            val passwordValue = password.text.toString()
            viewModel.verifyLogin(emailValue, passwordValue)
        }

        btnRegistrar.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        olvpassword.setOnClickListener{
            val intent= Intent(this, RecuperarActivity::class.java)
            startActivity(intent)
        }

        observerLiveData()
    }
    private fun observerLiveData() {
        viewModel.userLoginStatus.observe(this) {
            if (it) {
                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Verificar los datos ingresados",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }


}