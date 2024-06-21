package com.bigdatacorpapp.bigdataapp.promociones

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class PromocionesAdapter( val list: List<Promociones>):
    RecyclerView.Adapter<PromocionesViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PromocionesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PromocionesViewHolder(inflater, parent)
    }


    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: PromocionesViewHolder, position: Int) {
        val prmocion = list[position]
        holder.bind(prmocion)
    }

}