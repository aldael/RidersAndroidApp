package com.example.protorider0.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "riders")
data class RiderLocal (
    @PrimaryKey val id: Int,
    val armadura: String,
    val identidad_usuario: String,
    val nombre: String,
    val serie: String
    )