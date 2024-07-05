package com.bigdatacorpapp.bigdataapp.mapa

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.bigdatacorpapp.bigdataapp.R
import com.bigdatacorpapp.bigdataapp.menu.MenuActivity
import com.cibertec.cibertecapp.map.MapFragment

class MapaActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mapa)

        if (savedInstanceState == null) {
            val mapFragment = MapFragment.newInstance()

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, mapFragment)
                .commit()
        }

        val btnback: ImageButton = findViewById(R.id.btnBack)
        btnback.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }
    }
}