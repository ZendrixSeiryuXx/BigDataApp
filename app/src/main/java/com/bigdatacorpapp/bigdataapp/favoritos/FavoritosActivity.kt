package com.bigdatacorpapp.bigdataapp.favoritos

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bigdatacorpapp.bigdataapp.R
import com.bigdatacorpapp.bigdataapp.carrito.CarritoFragment
import com.bigdatacorpapp.bigdataapp.menu.MenuActivity

class FavoritosActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favoritos)


        val menuback = findViewById<ImageView>(R.id.backMenus)

        menuback.setOnClickListener{
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }

        if(savedInstanceState == null){
            openFragment(FavoritosFragment.newInstance())
        }


    }
    fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_favoritos, fragment)
        transaction.commit()


    }

}