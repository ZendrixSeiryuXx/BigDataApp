package com.bigdatacorpapp.bigdataapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bigdatacorpapp.bigdataapp.usuario.Usuario
import com.bigdatacorpapp.bigdataapp.usuario.UsuarioDAO

@Database(entities = [Usuario::class], version=1)
abstract class BigDataDatabase : RoomDatabase(){

    abstract fun usuarioDao() : UsuarioDAO

    companion object{
        private const val DATABASE_NAME = "BigData_Database"

        @Volatile
        private var INSTANCE : BigDataDatabase? = null

        private fun buildDatabase(context: Context): BigDataDatabase{
            return Room.databaseBuilder(
                context.applicationContext,
                BigDataDatabase::class.java,
                DATABASE_NAME
            ).build()
        }
        fun getInstance(context: Context) : BigDataDatabase{
            return INSTANCE ?: synchronized(this){
                INSTANCE ?: buildDatabase(context).also {
                    INSTANCE = it
                }
            }
        }
    }
}


