package com.example.pid.Entidad

data class Proyecto(
    val id: Int? = null,  // El id puede ser null si solo lo esperas en la respuesta
    val nombre: String,
    val color: String,
    val tema: String
)