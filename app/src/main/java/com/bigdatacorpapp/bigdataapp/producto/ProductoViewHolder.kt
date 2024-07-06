package com.bigdatacorpapp.bigdataapp.producto

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bigdatacorpapp.bigdataapp.R
import com.bumptech.glide.Glide

class ProductoViewHolder(inflater: LayoutInflater, viewGroup: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_producto, viewGroup, false)) {


    private var titulo: TextView? = null
    private var imagen: ImageView? = null
    private var marca: TextView? = null
    private var precioReal: TextView? = null
    private var precioOferta: TextView? = null

    private var favoriteButton: ImageView? = null

    init {
        titulo = itemView.findViewById(R.id.nombreProducto)
        imagen = itemView.findViewById(R.id.imgProducto)
        marca = itemView.findViewById(R.id.marcaProducto)
        precioReal = itemView.findViewById(R.id.precioRealProducto)
        precioOferta = itemView.findViewById(R.id.precioOfertaProducto)

        favoriteButton = itemView.findViewById(R.id.favoritoButton)
    }

    fun bind(producto: Producto, addToFavorites: (Producto) -> Unit) {
        titulo?.text = producto.titulo
        marca?.text = producto.marca
        precioReal?.text = producto.precioReal
        precioOferta?.text = producto.precioOferta

        imagen?.let {
            Glide.with(it.context)
                .load(producto.imagen)
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_placeholder)
                .into(it)
        }

        favoriteButton?.setOnClickListener {
            addToFavorites(producto)
        }
    }
}
