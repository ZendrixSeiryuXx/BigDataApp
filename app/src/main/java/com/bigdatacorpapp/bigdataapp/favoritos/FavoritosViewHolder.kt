package com.bigdatacorpapp.bigdataapp.favoritos

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bigdatacorpapp.bigdataapp.R

class FavoritosViewHolder(inflater: LayoutInflater, viewGroup: ViewGroup):
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_favorito,viewGroup, false)){

        private var imagenProducto: ImageView? = null
        private var nombre : TextView? = null
        private var descripcion : TextView? = null
        private var precio : TextView? = null

    init {
        imagenProducto = itemView.findViewById(R.id.imgProducto)
        nombre = itemView.findViewById(R.id.textNombreProducto)
        descripcion = itemView.findViewById(R.id.textDescripcion)
        precio = itemView.findViewById(R.id.textPrecio)
    }

    fun bind(favorito: Favorito){
        imagenProducto?.setImageResource(favorito.imagenProducto)
        nombre?.text = (favorito.nombre)
        descripcion?.text = (favorito.descripcion)
        precio?.text = (favorito.precio).toString()
    }


}