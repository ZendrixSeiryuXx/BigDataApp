package com.bigdatacorpapp.bigdataapp.usuario

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UsuarioDAO {
    @Insert
    fun insert(usuario : Usuario)
    @Update
    fun update(usuario : Usuario)
    @Delete
    fun delete(usuario : Usuario)
    @Query("SELECT * FROM Usuario_table WHERE email_user = :emailUser")
    fun getUserByEmail(emailUser: String): LiveData<Usuario>
}