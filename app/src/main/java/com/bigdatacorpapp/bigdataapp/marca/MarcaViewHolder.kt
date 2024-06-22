package com.bigdatacorpapp.bigdataapp.marca

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bigdatacorpapp.bigdataapp.R

class MarcaViewHolder(inflater: LayoutInflater,viewGroup: ViewGroup): RecyclerView.ViewHolder(inflater.inflate(R.layout.item_marca,viewGroup,false)) {
    private var titleMarca: TextView? = null

    init {
        titleMarca = itemView.findViewById(R.id.titleMarca)
    }

    fun bind(marca: Marca){
        titleMarca?.text = marca.nombre
    }
}