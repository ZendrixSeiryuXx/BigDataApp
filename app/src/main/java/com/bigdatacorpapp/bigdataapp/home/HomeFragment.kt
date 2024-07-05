package com.bigdatacorpapp.bigdataapp.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bigdatacorpapp.bigdataapp.R
import com.bigdatacorpapp.bigdataapp.marca.Marca
import com.bigdatacorpapp.bigdataapp.marca.MarcaAdapter
import com.bigdatacorpapp.bigdataapp.producto.Producto
import com.bigdatacorpapp.bigdataapp.producto.ProductoAdapter
import com.bigdatacorpapp.bigdataapp.producto.ProductoViewModel
import com.google.firebase.auth.FirebaseAuth

class HomeFragment : Fragment() {

    private lateinit var viewModel: ProductoViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[ProductoViewModel::class.java]

        // Configuración del RecyclerView para las marcas (esto ya lo tienes configurado)
        val recyclerMarcas = view.findViewById<RecyclerView>(R.id.recyclerMarcas)
        val listMarcas = listOf(
            Marca("Asus"),
            Marca("HP"),
            Marca("Lenovo"),
            Marca("Toshiba")
        )
        val marcaAdapter = MarcaAdapter(listMarcas)
        recyclerMarcas.adapter = marcaAdapter
        recyclerMarcas.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        // Configuración del RecyclerView para los productos
        val recyclerProductos = view.findViewById<RecyclerView>(R.id.recyclerProductos)
        val productoAdapter = ProductoAdapter { producto ->
            // Aquí se maneja el evento de agregar a favoritos
            val userId = FirebaseAuth.getInstance().currentUser?.uid ?: ""
            viewModel.agregarAFavoritos(userId, producto)
            Toast.makeText(context, "Producto agregado a favoritos", Toast.LENGTH_SHORT).show()
        }
        recyclerProductos.adapter = productoAdapter
        recyclerProductos.layoutManager = GridLayoutManager(context, 2)

        // Obtener la lista de productos del ViewModel
        viewModel.getProducto()
        viewModel.productoListMutable.observe(viewLifecycleOwner) { productosList ->
            productoAdapter.setProducto(productosList)
        }
    }

    companion object {
        fun newInstance(): HomeFragment = HomeFragment()
    }
}
