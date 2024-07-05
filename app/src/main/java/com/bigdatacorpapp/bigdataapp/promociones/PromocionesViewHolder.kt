package com.bigdatacorpapp.bigdataapp.promociones

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bigdatacorpapp.bigdataapp.R
import com.bumptech.glide.Glide

class PromocionesViewHolder(inflater: LayoutInflater, viewGroup: ViewGroup):
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_promociones, viewGroup, false)){

        private var promocion : ImageView? = null


    init{
        promocion = itemView.findViewById(R.id.imgPromocion)

    }


    fun bind(promociones: Promociones){
        promocion?.let{
            Glide.with(it.context)
                .load(promociones.nombre)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(it)
        }
    }









    }