package com.bigdatacorpapp.bigdataapp.favoritos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bigdatacorpapp.bigdataapp.R

class FavoritosFragment : Fragment() {

    private lateinit var viewModel: FavoritosViewModel
    private lateinit var adapter: FavoritosAdapter

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
        adapter = FavoritosAdapter()

        recyclerFavoritos.adapter = adapter
        recyclerFavoritos.layoutManager = LinearLayoutManager(activity)

        viewModel = ViewModelProvider(this).get(FavoritosViewModel::class.java)
        viewModel.getFavoritos()
        viewModel.favoritosListMutable.observe(viewLifecycleOwner) { favoritosList ->
            adapter.setFavoritos(favoritosList)
        }
    }

    companion object {
        fun newInstance(): FavoritosFragment = FavoritosFragment()
    }
}
