package com.bigdatacorpapp.bigdataapp.favoritos

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bigdatacorpapp.bigdataapp.R
import com.bumptech.glide.Glide

class FavoritosViewHolder(inflater: LayoutInflater, viewGroup: ViewGroup, private val viewModel: FavoritosViewModel) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_favorito, viewGroup, false)) {


    private var titulo: TextView? = itemView.findViewById(R.id.nombreFavorito)
    private var imagen: ImageView? = itemView.findViewById(R.id.imgFavorito)
    private var marca: TextView? = itemView.findViewById(R.id.marcaFavorito)
    private var precioReal: TextView? = itemView.findViewById(R.id.precioRealFavorito)
    private var precioOferta: TextView? = itemView.findViewById(R.id.precioOfertaFavorito)

    var btnEliminar: ImageView? = itemView.findViewById(R.id.btnEliminar)



    fun bind(favoritos: Favoritos) {
        titulo?.text = favoritos.titulo
        marca?.text = favoritos.marca
        precioReal?.text = favoritos.precioReal
        precioOferta?.text = favoritos.precioOferta

        imagen?.let {
            Glide.with(itemView)
                .load(favoritos.imagen)
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_placeholder)
                .into(it)
        }

        btnEliminar?.setOnClickListener {
            // Aquí puedes llamar a un método en el ViewModel para eliminar este favorito
            viewModel.eliminarFavorito(favoritos) { success ->
                if (success) {
                    // Eliminación exitosa, puedes actualizar la interfaz si es necesario
                } else {
                    // Manejar el error si la eliminación falla
                    Log.e("FavoritosViewHolder", "Error al eliminar favorito")
                }
            }
        }
    }


}
