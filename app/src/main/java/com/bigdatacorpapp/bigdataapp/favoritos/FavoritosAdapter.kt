package com.bigdatacorpapp.bigdataapp.favoritos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class FavoritosAdapter( val list: List<Favorito>):
    RecyclerView.Adapter<FavoritosViewHolder>(){

    // Instancia el ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritosViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return FavoritosViewHolder(inflater, parent)
    }

    // Indica el num elementos a mostrar
    override fun getItemCount(): Int {
        return list.size
    }

    // Asignarle datos al viewholder
    override fun onBindViewHolder(holder: FavoritosViewHolder, position: Int) {
        val favorito = list[position]
        holder.bind(favorito)
    }


}