package com.bigdatacorpapp.bigdataapp.usuario

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Usuario_table")
data class Usuario(
    @ColumnInfo(name = "name_user")
    val nameUser : String,
    @ColumnInfo(name = "email_user")
    val emailUser : String,
    @ColumnInfo(name = "password")
    val passwordUser: String,
    @ColumnInfo(name = "quest_security")
    val questSecurity : String
){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_user")
    var noteId : Int = 0
}
