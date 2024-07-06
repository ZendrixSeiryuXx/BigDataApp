package com.bigdatacorpapp.bigdataapp.carrito

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bigdatacorpapp.bigdataapp.R
import com.bigdatacorpapp.bigdataapp.favoritos.Favoritos
import com.bumptech.glide.Glide

class CarritoViewHolder(inflater: LayoutInflater, viewGroup: ViewGroup, private val viewModel: CarritoViewModel):
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_carrito, viewGroup, false)){



    private var titulo: TextView? = itemView.findViewById(R.id.nombreCarrito)
    private var imagen: ImageView? = itemView.findViewById(R.id.imgCarrito)
    private var marca: TextView? = itemView.findViewById(R.id.marcaCarrito)
    private var precioReal: TextView? = itemView.findViewById(R.id.precioRealCarrito)
    private var precioOferta: TextView? = itemView.findViewById(R.id.precioOfertaCarrito)

    var btnEliminarCarrito: ImageView? = itemView.findViewById(R.id.btnEliminarCarrito)


    fun bind(carrito: Carrito) {
        titulo?.text = carrito.titulo
        marca?.text = carrito.marca
        precioReal?.text = carrito.precioReal
        precioOferta?.text = carrito.precioOferta

        imagen?.let {
            Glide.with(itemView)
                .load(carrito.imagen)
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_placeholder)
                .into(it)
        }

        btnEliminarCarrito?.setOnClickListener {
            viewModel.eliminarCarrito(carrito) { success ->
                if (success) {

                } else {

                    Log.e("FavoritosViewHolder", "Error al eliminar favorito")
                }
            }
        }
    }

}