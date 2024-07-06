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
    private val favoritosRepository = FavoritosRepository()

    fun getFavoritos() {
        firestore = FirebaseFirestore.getInstance()
        firestore.collection("usuarios").document(userId)
            .collection("favoritos")
            .get()
            .addOnSuccessListener { result ->
                val favoritosList = mutableListOf<Favoritos>()
                for (document in result) {

                    val data = document.data
                    val id = document.id

                    val titulo = data["titulo"] as? String ?: ""
                    val imagen = data["imagen"] as? String ?: ""
                    val marca = data["marca"] as? String ?: ""
                    val descripción = data["descripción"] as? String ?: ""
                    val precioReal = data["precioReal"]as? String ?: ""
                    val precioOferta = data["precioOferta"] as? String ?: ""

                    val favorito = Favoritos(id, titulo, imagen, marca, descripción , precioReal, precioOferta)
                    favoritosList.add(favorito)
                }
                favoritosListMutable.value = favoritosList
            }
            .addOnFailureListener { exception ->
                Log.e("FavoritosViewModel", "Error al obtener favoritos", exception)
                favoritosListMutable.value = emptyList() // Manejo de errores, se puede manejar según la lógica de la aplicación
            }
    }


    fun eliminarFavorito(favoritos: Favoritos, onComplete: (Boolean) -> Unit) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: ""
        favoritosRepository.eliminarFavorito(userId, favoritos) { success ->
            onComplete(success)
        }
    }


}