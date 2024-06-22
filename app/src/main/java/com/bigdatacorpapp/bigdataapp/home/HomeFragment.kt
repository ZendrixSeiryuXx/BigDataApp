package com.bigdatacorpapp.bigdataapp.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bigdatacorpapp.bigdataapp.R
import com.bigdatacorpapp.bigdataapp.marca.Marca
import com.bigdatacorpapp.bigdataapp.marca.MarcaAdapter
import com.bigdatacorpapp.bigdataapp.producto.Producto
import com.bigdatacorpapp.bigdataapp.producto.ProductoAdapter
class HomeFragment:Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerInicio1 = view.findViewById<RecyclerView>(R.id.recyclerMarcas)
        val recyclerInicio2 = view.findViewById<RecyclerView>(R.id.recyclerProductos)

        val listInicio1 = listOf<Marca>(
            Marca("Asus"),
            Marca("HP"),
            Marca("Lenovo"),
            Marca("Toshiba")
        )

        val listInicio2 = listOf<Producto>(
            Producto("Rog Strix G15","Asus", R.drawable.item_laptop,"S/. 7650","Norma S/. 8650","-10%"),
            Producto("Rog Strix G15","Asus", R.drawable.item_laptop,"S/. 7650","Norma S/. 8650","-10%"),
            Producto("Rog Strix G15","Asus", R.drawable.item_laptop,"S/. 7650","Norma S/. 8650","-10%"),
            Producto("Rog Strix G15","Asus", R.drawable.item_laptop,"S/. 7650","Norma S/. 8650","-10%"),
            Producto("Rog Strix G15","Asus", R.drawable.item_laptop,"S/. 7650","Norma S/. 8650","-10%"),
            Producto("Rog Strix G15","Asus", R.drawable.item_laptop,"S/. 7650","Norma S/. 8650","-10%"),
            Producto("Rog Strix G15","Asus", R.drawable.item_laptop,"S/. 7650","Norma S/. 8650","-10%"),
            Producto("Rog Strix G15","Asus", R.drawable.item_laptop,"S/. 7650","Norma S/. 8650","-10%")

        )

        val adapter1 = MarcaAdapter(listInicio1)
        val adapter2 = ProductoAdapter(listInicio2)
        recyclerInicio1.adapter = adapter1
        recyclerInicio2.adapter = adapter2
        recyclerInicio1.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        recyclerInicio2.layoutManager = GridLayoutManager(context,2)


    }

    companion object{
        fun newInstance() : HomeFragment = HomeFragment()
    }
}