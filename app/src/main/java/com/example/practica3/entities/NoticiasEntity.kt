package com.example.practica3.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "NoticiasEntity")
data class NoticiasEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    var titulo: String,
    var resumen: String,
    var fecha: String,
    var imagenPortada: String,
    var enlaceNoticia: String) {}
