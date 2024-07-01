package com.bigdatacorpapp.bigdataapp.marca

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MarcaAdapter(val list: List<Marca>): RecyclerView.Adapter<MarcaViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarcaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MarcaViewHolder(inflater,parent)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MarcaViewHolder, position: Int) {
        val marca = list[position]
        holder.bind(marca)
    }
}