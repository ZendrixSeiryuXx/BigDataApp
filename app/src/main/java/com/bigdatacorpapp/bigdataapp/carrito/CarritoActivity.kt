package com.bigdatacorpapp.bigdataapp.carrito

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bigdatacorpapp.bigdataapp.R
import com.bigdatacorpapp.bigdataapp.menu.MenuActivity
import com.bigdatacorpapp.bigdataapp.register.RegisterActivity

class CarritoActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carrito)


        val backMenu = findViewById<ImageView>(R.id.backMenu)

        backMenu.setOnClickListener{
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)



        }

        if(savedInstanceState == null){
            openFragment(CarritoFragment.newInstance())
        }


    }

    fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_carrito, fragment)
        transaction.commit()

    }


}