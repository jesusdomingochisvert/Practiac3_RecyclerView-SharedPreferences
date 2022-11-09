package com.example.practica3.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.practica3.daos.NoticiasDao
import com.example.practica3.daos.UsuariosDao
import com.example.practica3.entities.NoticiasEntity
import com.example.practica3.entities.UsuariosEntity

@Database(entities = arrayOf(NoticiasEntity::class, UsuariosEntity::class), version = 1)
abstract class NoticiasDatabase : RoomDatabase() {

    abstract fun newDao() : NoticiasDao

    abstract fun userDao() : UsuariosDao

}