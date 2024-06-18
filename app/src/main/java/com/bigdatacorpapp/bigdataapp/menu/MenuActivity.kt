package com.bigdatacorpapp.bigdataapp.menu

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.bigdatacorpapp.bigdataapp.R
import com.bigdatacorpapp.bigdataapp.carrito.CarritoActivity
import com.bigdatacorpapp.bigdataapp.favoritos.FavoritosFragment
import com.bigdatacorpapp.bigdataapp.home.HomeFragment
import com.bigdatacorpapp.bigdataapp.perfil.PerfilFragment
import com.bigdatacorpapp.bigdataapp.promociones.PromocionesFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MenuActivity: AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val nav_carrito = findViewById<ImageView>(R.id.view_carrito)
        val nav_view = findViewById<BottomNavigationView>(R.id.nav_view)


        nav_carrito.setOnClickListener {
            val intent = Intent(this, CarritoActivity::class.java)
            startActivity(intent)

        }


        nav_view.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.itemHome -> {
                    val fragment = HomeFragment.newInstance()
                    openFragment(fragment)
                    true
                }

                R.id.itemPromociones -> {
                    val fragment = PromocionesFragment.newInstance()
                    openFragment(fragment)
                    true
                }

                R.id.itemPerfil -> {
                    val fragment = PerfilFragment.newInstance()
                    openFragment(fragment)
                    true
                }

                R.id.itemFavoritos ->{
                    val fragment = FavoritosFragment.newInstance()
                    openFragment(fragment)
                    true
                }

                else -> false

            }
        }

        nav_view.selectedItemId = R.id.itemHome

    }

    fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_menu, fragment)
        transaction.commit()

    }

}

