package com.example.practica3.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UsuariosEntity")
data class UsuariosEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    var username: String,
    var password: String) {}