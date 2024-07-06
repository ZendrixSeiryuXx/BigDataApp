package com.bigdatacorpapp.bigdataapp.carrito

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bigdatacorpapp.bigdataapp.R

class CarritoFragment: Fragment() {

    private lateinit var viewModel: CarritoViewModel
    private lateinit var adapter: CarritoAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_carrito, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val recyclesCarrito = view.findViewById<RecyclerView>(R.id.recyclercarrito)

        viewModel = ViewModelProvider(this).get(CarritoViewModel::class.java)
        adapter = CarritoAdapter(viewModel)


        recyclesCarrito.adapter = adapter
        recyclesCarrito.layoutManager = LinearLayoutManager(activity)


        viewModel.getCarrito()
        viewModel.carritoListMutable.observe(viewLifecycleOwner){ carritoList ->
            adapter.setCarrito(carritoList)
        }

    }

    companion object{
        fun newInstance() : CarritoFragment = CarritoFragment()
    }
}