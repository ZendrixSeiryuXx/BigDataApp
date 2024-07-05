package com.bigdatacorpapp.bigdataapp.favoritos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bigdatacorpapp.bigdataapp.R

class FavoritosAdapter : RecyclerView.Adapter<FavoritosViewHolder>() {

    private var favoritosList = emptyList<Favoritos>()

    fun setFavoritos(favoritos: List<Favoritos>) {
        favoritosList = favoritos
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritosViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return FavoritosViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int = favoritosList.size

    override fun onBindViewHolder(holder: FavoritosViewHolder, position: Int) {
        val favoritos = favoritosList[position]
        holder.bind(favoritos)
    }
}
