package com.bigdatacorpapp.bigdataapp.favoritos

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bigdatacorpapp.bigdataapp.R
import com.bigdatacorpapp.bigdataapp.menu.MenuActivity
import com.bigdatacorpapp.bigdataapp.producto.ProductoAdapter
import com.google.firebase.auth.FirebaseAuth

class FavoritosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favoritos)

        val fragment = FavoritosFragment.newInstance()
        openFragment(fragment)

        val menuBack = findViewById<ImageView>(R.id.backMenus)
        menuBack.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }
    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_favoritos, fragment)
            .commit()
    }
}