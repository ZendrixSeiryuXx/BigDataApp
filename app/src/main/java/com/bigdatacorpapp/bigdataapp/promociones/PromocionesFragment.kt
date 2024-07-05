package com.bigdatacorpapp.bigdataapp.promociones

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bigdatacorpapp.bigdataapp.R

class PromocionesFragment: Fragment() {

    private lateinit var viewModel: PromocionesViewModel
    private lateinit var adapter: PromocionesAdapter

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


        val adapter = PromocionesAdapter()
        recyclerPromociones.adapter = adapter
        recyclerPromociones.layoutManager = LinearLayoutManager(activity)

        viewModel = ViewModelProvider(this).get(PromocionesViewModel::class.java)
        viewModel.getPromociones()
        viewModel.promocionesListMutable.observe(viewLifecycleOwner) {
            if(it.isNotEmpty()){
                adapter.setPromociones(it)
            }
        }

    }

    companion object{
        fun newInstance():PromocionesFragment = PromocionesFragment()
    }

}