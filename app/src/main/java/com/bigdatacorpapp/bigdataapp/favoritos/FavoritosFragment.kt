package com.bigdatacorpapp.bigdataapp.favoritos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bigdatacorpapp.bigdataapp.R
import com.bigdatacorpapp.bigdataapp.home.HomeFragment

class FavoritosFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favoritos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerFavoritos = view.findViewById<RecyclerView>(R.id.recyclerFavoritos)

        val listFavoritos = listOf<Favorito>(
            Favorito(R.drawable.rogstrix_g15, "Rog Strix G15", "JA JA JA si funciona", 5000.0),
            Favorito(R.drawable.rogstrix_g15, "Rog  G15", "JA JA JA si funciona", 5.000),
            Favorito(R.drawable.rogstrix_g15, " G15", "JA JA JA si funciona", 5.000),
            Favorito(R.drawable.rogstrix_g15, " G15", "JA JA JA si funciona", 5.000),
            Favorito(R.drawable.rogstrix_g15, " G15", "JA JA JA si funciona", 5.000),
            Favorito(R.drawable.rogstrix_g15, " G15", "JA JA JA si funciona", 5.000),
            Favorito(R.drawable.rogstrix_g15, " G15", "JA JA JA si funciona", 5.000),
            Favorito(R.drawable.rogstrix_g15, " G15", "JA JA JA si funciona", 5.000),
            Favorito(R.drawable.rogstrix_g15, " G15", "JA JA JA si funciona", 5.000),
            Favorito(R.drawable.rogstrix_g15, " G15", "JA JA JA si funciona", 5.000),
            Favorito(R.drawable.rogstrix_g15, " G15", "JA JA JA si funciona", 5.000),
            Favorito(R.drawable.rogstrix_g15, " G15", "JA JA JA si funciona", 5.000),
            Favorito(R.drawable.rogstrix_g15, " G15", "JA JA JA si funciona", 5.000),
            Favorito(R.drawable.rogstrix_g15, " G15", "JA JA JA si funciona", 5.000),
            Favorito(R.drawable.rogstrix_g15, " G15", "JA JA JA si funciona", 5.000),
            Favorito(R.drawable.rogstrix_g15, " G15", "JA JA JA si funciona", 5.000),
            Favorito(R.drawable.rogstrix_g15, " G15", "JA JA JA si funciona", 5.000),
            Favorito(R.drawable.rogstrix_g15, " G15", "JA JA JA si funciona", 5.000),
            Favorito(R.drawable.rogstrix_g15, " G15", "JA JA JA si funciona", 5.000),
            Favorito(R.drawable.rogstrix_g15, " G15", "JA JA JA si funciona", 5.000),
            Favorito(R.drawable.rogstrix_g15, " G15", "JA JA JA si funciona", 5.000),

            )

        val adapter = FavoritosAdapter(listFavoritos)
        recyclerFavoritos.adapter = adapter
        recyclerFavoritos.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }


    companion object{
        fun newInstance() : FavoritosFragment = FavoritosFragment()
    }
}