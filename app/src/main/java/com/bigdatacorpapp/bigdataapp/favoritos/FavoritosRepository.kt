package com.bigdatacorpapp.bigdataapp.favoritos

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

    fun obtenerFavoritos(userId: String, onComplete: (List<Producto>) -> Unit) {
        val favoritosCollection = userCollection.document(userId).collection("favoritos")
        favoritosCollection.get()
            .addOnSuccessListener { result ->
                val favoritos = result.map { it.toObject(Producto::class.java) }
                onComplete(favoritos)
            }
            .addOnFailureListener { onComplete(emptyList()) }
    }
}