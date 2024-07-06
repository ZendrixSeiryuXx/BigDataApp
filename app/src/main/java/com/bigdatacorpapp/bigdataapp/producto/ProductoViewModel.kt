package com.bigdatacorpapp.bigdataapp.producto

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bigdatacorpapp.bigdataapp.carrito.CarritoRepository
import com.bigdatacorpapp.bigdataapp.favoritos.FavoritosRepository
import com.google.firebase.firestore.FirebaseFirestore

class ProductoViewModel : ViewModel() {
    private lateinit var firestore: FirebaseFirestore
    val productoListMutable = MutableLiveData<List<Producto>>()
    var productoList = arrayListOf<Producto>()
    private val favoritosRepository = FavoritosRepository()
    private val carritoRepository = CarritoRepository()

    init {
        getProducto()
    }

    fun getProducto() {
        firestore = FirebaseFirestore.getInstance()
        firestore.collection("producto")
            .get()
            .addOnSuccessListener { result ->
                productoList.clear()
                for (document in result) {
                    val id = document.id
                    val data = document.data

                    val titulo = data["titulo"] as? String ?: ""
                    val imagen = data["imagen"] as? String ?: ""
                    val marca = data["marca"] as? String ?: ""
                    val descripción = data["descripción"] as? String ?: ""
                    val precioReal = data["precioReal"] as? String ?: ""
                    val precioOferta = data["precioOferta"]  as? String ?: ""

                    val producto = Producto(id, titulo, imagen, marca, descripción, precioReal, precioOferta)
                    productoList.add(producto)
                }
                productoListMutable.value = productoList
            }
            .addOnFailureListener { exception ->
                Log.e("ProductoViewModel", "Error al obtener productos", exception)
            }
    }

    fun agregarAFavoritos(userId: String, producto: Producto) {
        favoritosRepository.agregarAFavoritos(userId, producto) { success ->
            if (success) {
                Log.d("ProductoViewModel", "Producto agregado a favoritos")

            } else {
                Log.e("ProductoViewModel", "Error al agregar a favoritos")

            }
        }
    }


    fun agregarACarrito(userId: String, producto: Producto) {
        carritoRepository.agregarACarritop(userId, producto){success ->
            if (success) {
                Log.d("ProductoViewModel", "Producto agregado a carrito")

            } else {
                Log.e("ProductoViewModel", "Error al agregar a carrito")

            }
        }
    }


    fun buscarProductoPorNombre(nombre: String) {
        val productosFiltrados = productoList.filter { it.titulo.contains(nombre, ignoreCase = true) }
        productoListMutable.value = productosFiltrados
    }



}