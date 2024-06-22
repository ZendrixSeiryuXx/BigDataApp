package com.bigdatacorpapp.bigdataapp.usuario

import android.app.Application
import androidx.lifecycle.LiveData
import com.bigdatacorpapp.bigdataapp.database.BigDataDatabase
import com.bigdatacorpapp.bigdataapp.R.layout.activity_login
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UsuarioRepository(application: Application) {

    private val usuarioDAO = BigDataDatabase.getInstance(application).usuarioDao()

    fun getUsuarioByEmail(email: String) : LiveData<Usuario>{
        return usuarioDAO.getUserByEmail(email)
    }

    suspend fun insertUsuario(usuario: Usuario){
        withContext(Dispatchers.IO){
            usuarioDAO.insert(usuario)
        }
    }
}