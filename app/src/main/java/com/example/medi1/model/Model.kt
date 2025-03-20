package com.ejemplo.tuapp.model

data class Item(
    val id: Int,
    val titulo: String,
    val descripcion: String,
    val descripcionlarge: String,
    val imagenUrl: String? = null,

)