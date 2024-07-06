package com.bigdatacorpapp.bigdataapp.favoritos

import android.util.Log
import com.bigdatacorpapp.bigdataapp.producto.Producto
import com.google.firebase.firestore.FirebaseFirestore

class FavoritosRepository {
    private val db = FirebaseFirestore.getInstance()
    private val userCollection = db.collection("usuarios")

    fun agregarAFavoritos(userId: String, producto: Producto, onComplete: (Boolean) -> Unit) {
        val favoritosCollection = userCollection.document(userId).collection("favoritos")
        favoritosCollection.document(producto.id).set(producto)
            .addOnSuccessListener { onComplete(true) }
            .addOnFailureListener { onComplete(false) }
    }

    fun eliminarFavorito(userId: String, favoritos: Favoritos, onComplete: (Boolean) -> Unit) {
        val favoritosCollection = db.collection("usuarios").document(userId).collection("favoritos")
        favoritosCollection.document(favoritos.id)
            .delete()
            .addOnSuccessListener {
                onComplete(true)
            }
            .addOnFailureListener { e ->
                Log.e("FavoritosRepository", "Error al eliminar favorito", e)
                onComplete(false)
            }
    }


}