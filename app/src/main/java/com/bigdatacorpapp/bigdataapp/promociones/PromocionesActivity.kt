package com.bigdatacorpapp.bigdataapp.promociones

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bigdatacorpapp.bigdataapp.R
import com.bigdatacorpapp.bigdataapp.favoritos.FavoritosFragment
import com.bigdatacorpapp.bigdataapp.menu.MenuActivity

class PromocionesActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_promociones)




        if(savedInstanceState == null){
            openFragment(FavoritosFragment.newInstance())
        }


    }
    fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_promociones, fragment)
        transaction.commit()


    }
}

