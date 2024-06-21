package com.bigdatacorpapp.bigdataapp.promociones

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bigdatacorpapp.bigdataapp.R

class PromocionesViewHolder(inflater: LayoutInflater, viewGroup: ViewGroup):
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_promociones, viewGroup, false)){


        private var promocion : ImageView? = null


    init{
        promocion = itemView.findViewById(R.id.imgPromocion)

    }


    fun bind(promociones: Promociones){
        promocion?.setImageResource(promociones.promocion)
    }









    }