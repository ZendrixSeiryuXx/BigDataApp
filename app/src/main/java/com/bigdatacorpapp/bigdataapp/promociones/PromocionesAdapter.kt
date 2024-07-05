package com.bigdatacorpapp.bigdataapp.promociones

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class PromocionesAdapter(): RecyclerView.Adapter<PromocionesViewHolder>() {

    private var promocionesList = emptyList<Promociones>()

    fun setPromociones(promociones: List<Promociones>){
        promocionesList = promociones
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PromocionesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PromocionesViewHolder(inflater, parent)
    }


    override fun getItemCount(): Int = promocionesList.size

    override fun onBindViewHolder(holder: PromocionesViewHolder, position: Int) {
        val promociones = promocionesList[position]
        holder.bind(promociones)
    }

}