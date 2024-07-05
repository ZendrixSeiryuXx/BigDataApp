package com.bigdatacorpapp.bigdataapp.favoritos

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class FavoritosViewModel : ViewModel() {
    private lateinit var firestore: FirebaseFirestore
    val favoritosListMutable = MutableLiveData<List<Favoritos>>()
    private val userId = FirebaseAuth.getInstance().currentUser?.uid ?: ""

    fun getFavoritos() {
        firestore = FirebaseFirestore.getInstance()
        firestore.collection("usuarios").document(userId)
            .collection("favoritos")
            .get()
            .addOnSuccessListener { result ->
                val favoritosList = mutableListOf<Favoritos>()
                for (document in result) {
                    val data = document.data
                    val titulo = data["titulo"] as String
                    val marca = data["marca"] as String
                    val imagen = data["imagen"] as String
                    val precio1 = data["precio1"] as String
                    val precio2 = data["precio2"] as String
                    val descuento = data["descuento"] as String

                    favoritosList.add(
                        Favoritos(
                            titulo,
                            marca,
                            imagen,
                            precio1,
                            precio2,
                            descuento
                        )
                    )
                }
                favoritosListMutable.value = favoritosList
            }
            .addOnFailureListener { exception ->
                Log.e("FavoritosViewModel", "Error al obtener favoritos", exception)
                favoritosListMutable.value = emptyList() // Manejo de errores, se puede manejar según la lógica de la aplicación
            }
    }
}
