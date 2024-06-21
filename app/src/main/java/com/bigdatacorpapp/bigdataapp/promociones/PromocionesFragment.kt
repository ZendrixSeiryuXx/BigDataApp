package com.bigdatacorpapp.bigdataapp.promociones

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bigdatacorpapp.bigdataapp.R

class PromocionesFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_promociones, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerPromociones = view.findViewById<RecyclerView>(R.id.recyclerPromociones)


        val listPromociones = listOf(
            Promociones(R.drawable.aaa),
            Promociones(R.drawable.promocion_1),
            Promociones(R.drawable.aaa),
            Promociones(R.drawable.promocion_1),
            Promociones(R.drawable.aaa),
            Promociones(R.drawable.promocion_1),
            Promociones(R.drawable.aaa),
            Promociones(R.drawable.promocion_1),
            Promociones(R.drawable.aaa),
            Promociones(R.drawable.promocion_1),
            Promociones(R.drawable.aaa),
            Promociones(R.drawable.promocion_1),

        )


        val adapter = PromocionesAdapter(listPromociones)
        recyclerPromociones.adapter = adapter
        recyclerPromociones.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    companion object{
        fun newInstance():PromocionesFragment = PromocionesFragment()
    }

}