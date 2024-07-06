package com.bigdatacorpapp.bigdataapp.producto

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ProductoAdapter(private val addToFavorites: (Producto) -> Unit) :
    RecyclerView.Adapter<ProductoViewHolder>() {

    private var productoList = emptyList<Producto>()

    fun setProducto(producto: List<Producto>) {
        productoList = producto
        notifyDataSetChanged()
    }

    //new

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ProductoViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int = productoList.size

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val producto = productoList[position]
        holder.bind(producto, addToFavorites)
    }
    }
