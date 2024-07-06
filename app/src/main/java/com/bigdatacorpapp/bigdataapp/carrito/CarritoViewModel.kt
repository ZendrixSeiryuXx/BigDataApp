package com.bigdatacorpapp.bigdataapp.carrito

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bigdatacorpapp.bigdataapp.favoritos.Favoritos
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class CarritoViewModel: ViewModel() {

    private lateinit var firestore: FirebaseFirestore
    val carritoListMutable = MutableLiveData<List<Carrito>>()
    private val userId = FirebaseAuth.getInstance().currentUser?.uid?: ""
    private val carritoRepository = CarritoRepository()

    fun getCarrito(){
        firestore = FirebaseFirestore.getInstance()
        firestore.collection("usuarios").document(userId)
            .collection("carrito")
            .get()
            .addOnSuccessListener { result ->
                val carritoList = mutableListOf<Carrito>()
                for (document in result) {
                    val data = document.data
                    val id = document.id

                    val titulo = data["titulo"] as? String ?: ""
                    val imagen = data["imagen"] as? String ?: ""
                    val marca = data["marca"] as? String ?: ""
                    val descripción = data["descripción"] as? String ?: ""
                    val precioReal = data["precioReal"]as? String ?: ""
                    val precioOferta = data["precioOferta"] as? String ?: ""

                    val carrito = Carrito(id, titulo, imagen, marca, descripción , precioReal, precioOferta)
                    carritoList.add(carrito)
                }
                carritoListMutable.value = carritoList
            }
            .addOnFailureListener { exception ->
                Log.e("CarritoViewModel", "Error al obtener los productos", exception)
                carritoListMutable.value = emptyList()
            }
    }

    fun eliminarCarrito(carrito: Carrito, onComplete: (Boolean) -> Unit) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: ""
        carritoRepository.eliminarCarrito(userId, carrito) { success ->
            onComplete(success)
        }
    }

}