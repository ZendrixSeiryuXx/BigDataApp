package com.bigdatacorpapp.bigdataapp.home

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bigdatacorpapp.bigdataapp.R
import com.bigdatacorpapp.bigdataapp.carrito.CarritoViewModel
import com.bigdatacorpapp.bigdataapp.favoritos.FavoritosViewModel
import com.bigdatacorpapp.bigdataapp.login.LoginActivity
import com.bigdatacorpapp.bigdataapp.marca.Marca
import com.bigdatacorpapp.bigdataapp.marca.MarcaAdapter
import com.bigdatacorpapp.bigdataapp.producto.Producto
import com.bigdatacorpapp.bigdataapp.producto.ProductoAdapter
import com.bigdatacorpapp.bigdataapp.producto.ProductoViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class HomeFragment : Fragment() {

    private lateinit var viewModel: ProductoViewModel
    private lateinit var favoritosViewModel: FavoritosViewModel
    private lateinit var carritoViewModel: CarritoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerInicio1 = view.findViewById<RecyclerView>(R.id.recyclerMarcas)
        val recyclerInicio2 = view.findViewById<RecyclerView>(R.id.recyclerProductos)
        val edtBuscar = view.findViewById<TextInputEditText>(R.id.edtBuscar)

        val listInicio1 = listOf(
            Marca("Asus"),
            Marca("HP"),
            Marca("Lenovo"),
            Marca("Toshiba")
        )

        viewModel = ViewModelProvider(this)[ProductoViewModel::class.java]
        favoritosViewModel = ViewModelProvider(this)[FavoritosViewModel::class.java]
        carritoViewModel = ViewModelProvider(this)[CarritoViewModel::class.java]

        val adapter1 = MarcaAdapter(listInicio1)

        val adapter2 = ProductoAdapter(
            { producto -> addToFavorites(producto) },
            { producto -> addToCarrito(producto) }
        )

        recyclerInicio1.adapter = adapter1
        recyclerInicio2.adapter = adapter2
        recyclerInicio1.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerInicio2.layoutManager = GridLayoutManager(context, 2)

        viewModel.getProducto()
        viewModel.productoListMutable.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                adapter2.setProducto(it)
            }
        }

        // Añadir el TextWatcher para el campo de búsqueda
        edtBuscar.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                // No se necesita implementar
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No se necesita implementar
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val textoBusqueda = s.toString()
                viewModel.buscarProductoPorNombre(textoBusqueda)
            }
        })
    }

    private fun addToFavorites(producto: Producto) {
        if (!checkUserAuthentication()) return

        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            val firestore = FirebaseFirestore.getInstance()
            val favorito = hashMapOf(
                "titulo" to producto.titulo,
                "imagen" to producto.imagen,
                "marca" to producto.marca,
                "precioReal" to producto.precioReal,
                "precioOferta" to producto.precioOferta,
            )
            firestore.collection("usuarios").document(userId)
                .collection("favoritos").add(favorito)
                .addOnSuccessListener {
                    Toast.makeText(context,"Se Agregó con éxito",Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(context,"Error al Agregar",Toast.LENGTH_SHORT).show()
                }
        }
    }

    private fun addToCarrito(producto: Producto) {
        if (!checkUserAuthentication()) return

        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            val firestore = FirebaseFirestore.getInstance()
            val carrito = hashMapOf(
                "titulo" to producto.titulo,
                "imagen" to producto.imagen,
                "marca" to producto.marca,
                "precioReal" to producto.precioReal,
                "precioOferta" to producto.precioOferta,
            )
            firestore.collection("usuarios").document(userId)
                .collection("carrito").add(carrito)
                .addOnSuccessListener {
                    Toast.makeText(context,"Se Agregó con éxito",Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(context,"Error al Agregar",Toast.LENGTH_SHORT).show()
                }
        }
    }

    private fun checkUserAuthentication(): Boolean {
        val currentUser = FirebaseAuth.getInstance().currentUser
        return if (currentUser == null) {
            val intent = Intent(context, LoginActivity::class.java)
            startActivity(intent)
            Toast.makeText(context, "Por favor, inicia sesión para continuar", Toast.LENGTH_SHORT).show()
            false
        } else {
            true
        }
    }

    companion object {
        fun newInstance(): HomeFragment = HomeFragment()
    }
}