package com.bigdatacorpapp.bigdataapp.bienvenida

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.bigdatacorpapp.bigdataapp.R
import com.bigdatacorpapp.bigdataapp.menu.MenuActivity

class SplashActivity : AppCompatActivity() {

    private val splashTimeOut: Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        Handler().postDelayed({
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            finish()
        }, splashTimeOut)
    }
}