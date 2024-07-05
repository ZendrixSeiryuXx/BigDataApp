package com.bigdatacorpapp.bigdataapp.promociones

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bigdatacorpapp.bigdataapp.producto.Producto
import com.google.firebase.firestore.FirebaseFirestore

class PromocionesViewModel : ViewModel(){

    private lateinit var firestore: FirebaseFirestore
    val promocionesListMutable = MutableLiveData<List<Promociones>>()
    var promocionesList  = arrayListOf<Promociones>()

    fun getPromociones() {
        firestore = FirebaseFirestore.getInstance()
        firestore.collection("promociones")
            .get()
            .addOnSuccessListener { result ->

                for (document in result) {
                    val id = document.id
                    val data = document.data

                    val nombre = data["nombre"] as? String?: ""


                    promocionesList.add(
                        Promociones(
                            nombre = nombre
                        )
                    )

                }
                promocionesListMutable.value = promocionesList
            }
            .addOnFailureListener { exception ->
                Log.e("PromocionesViewModel", "Error al obtener productos", exception)
            }
    }


}