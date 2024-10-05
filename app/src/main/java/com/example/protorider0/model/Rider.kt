package com.example.protorider0.model

import java.io.Serializable

data class Rider(
    val id: Int,
    val armadura: String,
    val identidad_usuario: String,
    val nombre: String,
    val serie: String,
) // : Serializable