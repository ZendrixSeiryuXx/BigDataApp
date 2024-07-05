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
import com.bigdatacorpapp.bigdataapp.favoritos.FavoritosActivity
import com.bigdatacorpapp.bigdataapp.favoritos.FavoritosFragment
import com.bigdatacorpapp.bigdataapp.login.LoginActivity
import com.bigdatacorpapp.bigdataapp.perfil.PerfilFragment
import com.bigdatacorpapp.bigdataapp.promociones.PromocionesFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.bigdatacorpapp.bigdataapp.home.HomeFragment
import com.bigdatacorpapp.bigdataapp.mapa.MapaActivity

class MenuActivity : AppCompatActivity(), MenuDraweAction {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationViewLateral: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val nav_carrito = findViewById<ImageView>(R.id.view_carrito)
        val nav_view = findViewById<BottomNavigationView>(R.id.nav_view)

        nav_carrito.setOnClickListener {
            val intent = Intent(this, CarritoActivity::class.java)
            startActivity(intent)
        }

        nav_view.setOnItemSelectedListener {
            when (it.itemId) {
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


                else -> false
            }
        }
        nav_view.selectedItemId = R.id.itemHome

        drawerLayout = findViewById(R.id.drawerLayout)
        navigationViewLateral = findViewById(R.id.nav_view_lateral)
        val menuLateral = findViewById<ImageView>(R.id.menuLateral)

        menuLateral.setOnClickListener {
            drawerLayout.openDrawer(navigationViewLateral)
        }

        navigationViewLateral.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {

                R.id.itemFavoritos -> {
                    startActivity(Intent(this, FavoritosActivity::class.java))
                    true
                }

                R.id.itemMap -> {
                    startActivity(Intent(this, MapaActivity::class.java))
                    true
                }

                R.id.itemSalir -> {
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                    true
                }
                else -> false
            }.also {
                drawerLayout.closeDrawer(navigationViewLateral)
            }
        }
    }

    fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_menu, fragment)
        transaction.commit()
    }

    override fun openMenu() {
        drawerLayout.openDrawer(GravityCompat.START)
    }
}

interface MenuDraweAction {
    fun openMenu()
}