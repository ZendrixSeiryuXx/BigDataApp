package com.bigdatacorpapp.bigdataapp.producto

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bigdatacorpapp.bigdataapp.favoritos.FavoritosRepository
import com.google.firebase.firestore.FirebaseFirestore

class ProductoViewModel : ViewModel() {
    private lateinit var firestore: FirebaseFirestore
    val productoListMutable = MutableLiveData<List<Producto>>()
    var productoList = arrayListOf<Producto>()
    private val favoritosRepository = FavoritosRepository()

    fun getProducto() {
        firestore = FirebaseFirestore.getInstance()
        firestore.collection("producto")
            .get()
            .addOnSuccessListener { result ->
                productoList.clear()
                for (document in result) {
                    val id = document.id
                    val data = document.data

                    val titulo = data["titulo"] as String
                    val marca = data["marca"] as String
                    val imagen = data["imagen"] as String
                    val precio1 = data["precio1"] as String
                    val precio2 = data["precio2"] as String
                    val descuento = data["descuento"] as String

                    val producto = Producto(id, titulo, marca, imagen, precio1, precio2, descuento)
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
                // Aquí podrías actualizar la UI o realizar alguna acción adicional si es necesario
            } else {
                Log.e("ProductoViewModel", "Error al agregar a favoritos")
                // Manejo de error si falla la operación de agregar a favoritos
            }
        }
    }


}