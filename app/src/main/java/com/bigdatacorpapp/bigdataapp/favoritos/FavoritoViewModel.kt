package com.bigdatacorpapp.bigdataapp.favoritos

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bigdatacorpapp.bigdataapp.producto.Producto
import com.google.firebase.firestore.FirebaseFirestore

class FavoritoViewModel: ViewModel(){

    private lateinit var firestore: FirebaseFirestore
    val FavoritoListMutable = MutableLiveData<List<Producto>>()
    var FavoritoList  = arrayListOf<Producto>()



}