package com.bigdatacorpapp.bigdataapp.favoritos

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bigdatacorpapp.bigdataapp.R
import com.bumptech.glide.Glide

class FavoritosViewHolder(inflater: LayoutInflater, viewGroup: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_favorito, viewGroup, false)) {

    private var titulo: TextView? = itemView.findViewById(R.id.textNombreFavorito)
    private var marca: TextView? = itemView.findViewById(R.id.textMarcaProducto)
    private var imagen: ImageView? = itemView.findViewById(R.id.imageItem)
    private var precio1: TextView? = itemView.findViewById(R.id.textPrecio)
    private var precio2: TextView? = itemView.findViewById(R.id.textPrecio)
    private var descuento: TextView? = itemView.findViewById(R.id.discount)

    fun bind(favoritos: Favoritos) {
        titulo?.text = favoritos.titulo
        marca?.text = favoritos.marca
        precio1?.text = favoritos.precio1
        precio2?.text = favoritos.precio2
        descuento?.text = favoritos.descuento

        imagen?.let {
            Glide.with(itemView)
                .load(favoritos.imagen)
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_placeholder)
                .into(it)
        }
    }
}
