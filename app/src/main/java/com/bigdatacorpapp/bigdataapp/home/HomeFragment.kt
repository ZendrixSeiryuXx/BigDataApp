package com.bigdatacorpapp.bigdataapp.home

import android.os.Bundle
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
import com.bigdatacorpapp.bigdataapp.favoritos.FavoritosViewModel
import com.bigdatacorpapp.bigdataapp.marca.Marca
import com.bigdatacorpapp.bigdataapp.marca.MarcaAdapter
import com.bigdatacorpapp.bigdataapp.producto.Producto
import com.bigdatacorpapp.bigdataapp.producto.ProductoAdapter
import com.bigdatacorpapp.bigdataapp.producto.ProductoViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class HomeFragment : Fragment() {

    private lateinit var viewModel: ProductoViewModel
    private lateinit var favoritosViewModel: FavoritosViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerInicio1 = view.findViewById<RecyclerView>(R.id.recyclerMarcas)

        val listInicio1 = listOf(
            Marca("Asus"),
            Marca("HP"),
            Marca("Lenovo"),
            Marca("Toshiba")
        )

        viewModel = ViewModelProvider(this)[ProductoViewModel::class.java]
        favoritosViewModel = ViewModelProvider(this)[FavoritosViewModel::class.java]
        val recyclerInicio2 = view.findViewById<RecyclerView>(R.id.recyclerProductos)

        val adapter1 = MarcaAdapter(listInicio1)
        val adapter2 = ProductoAdapter { producto ->
            addToFavorites(producto)
        }
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
    }

    private fun addToFavorites(producto: Producto) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            val firestore = FirebaseFirestore.getInstance()
            val favorito = hashMapOf(
                "titulo" to producto.titulo,
                "marca" to producto.marca,
                "imagen" to producto.imagen,
                "precio1" to producto.precio1,
                "precio2" to producto.precio2,
                "descuento" to producto.descuento
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

    companion object {
        fun newInstance(): HomeFragment = HomeFragment()
    }
}