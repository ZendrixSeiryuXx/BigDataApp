package com.bigdatacorpapp.bigdataapp.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegisterViewModel: ViewModel() {

    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    val userRegisterStatus = MutableLiveData<Boolean>()

    fun verifyRegister(nombres: String, apellidos: String, genero: String, correo: String, contrasena: String) {
        registerFirebase(nombres, apellidos, genero, correo, contrasena)
    }

    private fun registerFirebase(nombres: String, apellidos: String, genero: String, correo: String, contrasena: String){
        auth = FirebaseAuth.getInstance()
        auth.createUserWithEmailAndPassword(correo, contrasena)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    val uid = it.result.user?.uid
                    uid?.let {
                        registerFirestore(uid, nombres, apellidos, genero, correo)
                    }
                } else {
                    userRegisterStatus.value = false
                }
            }
    }

    private fun registerFirestore(uid: String, nombres: String, apellidos: String, genero: String, correo: String) {
        firestore = FirebaseFirestore.getInstance()

        // val docRef = firestore.collection("libros").document("tJtznhEVtPYhVWkceNOg")

        val user = hashMapOf(
            "nombres" to nombres,
            "apellidos" to apellidos,
            "genero" to genero,
            "correo" to correo
            // "documentoLibro" to docRef
        )
        firestore.collection("usuarios").document(uid)
            .set(user)
            .addOnSuccessListener {
                userRegisterStatus.value = true
            }
            .addOnFailureListener {
                userRegisterStatus.value = false
            }
    }
}