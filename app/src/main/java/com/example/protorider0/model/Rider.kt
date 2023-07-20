package com.example.protorider0.model

import java.io.Serializable

data class Rider(
    val id: Int,
    val rider: String,
    val identidad: String,
    val armor: String,
    val serie: String,
    var isFav: Boolean = false
) : Serializable