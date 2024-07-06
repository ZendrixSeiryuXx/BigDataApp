package com.bigdatacorpapp.bigdataapp.carrito

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CarritoAdapter(private var viewModel: CarritoViewModel):
    RecyclerView.Adapter<CarritoViewHolder>() {


    private var carritoList = emptyList<Carrito>()

    fun setCarrito(carrito: List<Carrito>) {
        carritoList = carrito
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarritoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CarritoViewHolder(inflater, parent, viewModel)
    }


    override fun getItemCount(): Int = carritoList.size


    override fun onBindViewHolder(holder: CarritoViewHolder, position: Int) {
        val carrito = carritoList[position]
        holder.bind(carrito)

        holder.btnEliminarCarrito?.setOnClickListener {
            viewModel.eliminarCarrito(carrito){ succes ->
                if(succes){
                    val newList = carritoList.toMutableList()
                    newList.removeAt(position)
                    carritoList = newList
                    notifyDataSetChanged()
                }
                else{
                    Log.e("CarritoAdapter", "Error al eliminar producto del carrito")
                }
            }
        }
    }



}