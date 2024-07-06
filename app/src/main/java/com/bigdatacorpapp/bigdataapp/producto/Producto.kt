package com.bigdatacorpapp.bigdataapp.producto
data class Producto(
    val id: String = "",
    val titulo: String,
    val imagen: String,
    val marca: String,
    val descripción: String,
    val precioReal: String,
    val precioOferta: String
)
