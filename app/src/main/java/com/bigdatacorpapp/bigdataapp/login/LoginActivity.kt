package com.bigdatacorpapp.bigdataapp.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bigdatacorpapp.bigdataapp.R
import com.bigdatacorpapp.bigdataapp.menu.MenuActivity
import com.bigdatacorpapp.bigdataapp.recuperar.RecuperarActivity
import com.bigdatacorpapp.bigdataapp.register.RegisterActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val email = findViewById<EditText>(R.id.Icorreo)
        val password = findViewById<EditText>(R.id.Icontrasena)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnCrearCuenta = findViewById<Button>(R.id.btnCrearCuenta)
        val olvpassword = findViewById<TextView>(R.id.olvidasteContra)

        btnLogin.setOnClickListener {
            val emailValue = email.text.toString()
            val passwordValue = password.text.toString()

            if (emailValue == "BigData@gmail.com" && passwordValue == "123456") {
                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
                Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(
                    this,
                    "Verifique datos", Toast.LENGTH_SHORT
                ).show()
            }
        }

        btnCrearCuenta.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        olvpassword.setOnClickListener{
            val intent= Intent(this, RecuperarActivity::class.java)
            startActivity(intent)
        }
    }
}