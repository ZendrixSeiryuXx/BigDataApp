package com.bigdatacorpapp.bigdataapp.carrito

import android.util.Log
import com.bigdatacorpapp.bigdataapp.favoritos.Favoritos
import com.bigdatacorpapp.bigdataapp.producto.Producto
import com.google.firebase.firestore.FirebaseFirestore

class CarritoRepository {

    private val db = FirebaseFirestore.getInstance()
    private val userCollection = db.collection("usuarios")

    fun agregarACarritop(userId: String, producto: Producto, onComplete: (Boolean) -> Unit) {
        val carritoCollection = userCollection.document(userId).collection("carrito")
        carritoCollection.document(producto.id).set(producto)
            .addOnSuccessListener { onComplete(true) }
            .addOnFailureListener { onComplete(false) }
    }

    fun eliminarCarrito(userId: String, carrito: Carrito, onComplete: (Boolean) -> Unit) {
        val carritoCollection = db.collection("usuarios").document(userId).collection("carrito")
        carritoCollection.document(carrito.id)
            .delete()
            .addOnSuccessListener {
                onComplete(true)
            }
            .addOnFailureListener { e ->
                Log.e("CarritoRepository", "Error al eliminar producto", e)
                onComplete(false)
            }
    }
}