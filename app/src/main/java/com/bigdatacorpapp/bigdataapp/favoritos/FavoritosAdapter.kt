package com.bigdatacorpapp.bigdataapp.favoritos

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bigdatacorpapp.bigdataapp.R

class FavoritosAdapter(private val viewModel: FavoritosViewModel) :
    RecyclerView.Adapter<FavoritosViewHolder>() {

    private var favoritosList = emptyList<Favoritos>()

    fun setFavoritos(favoritos: List<Favoritos>) {
        favoritosList = favoritos
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritosViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return FavoritosViewHolder(inflater, parent, viewModel)
    }

    override fun getItemCount(): Int = favoritosList.size

    override fun onBindViewHolder(holder: FavoritosViewHolder, position: Int) {
        val favoritos = favoritosList[position]
        holder.bind(favoritos)

        holder.btnEliminar?.setOnClickListener {
            viewModel.eliminarFavorito(favoritos) { success ->
                if (success) {
                    val newList = favoritosList.toMutableList()
                    newList.removeAt(position)
                    favoritosList = newList
                    notifyDataSetChanged()
                } else {
                    // Manejar error si no se pudo eliminar el favorito
                    Log.e("FavoritosAdapter", "Error al eliminar favorito")
                }
            }
        }
    }
}
