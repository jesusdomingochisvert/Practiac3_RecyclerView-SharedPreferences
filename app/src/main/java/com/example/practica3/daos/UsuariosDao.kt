package com.example.practica3.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.practica3.entities.UsuariosEntity

@Dao
interface UsuariosDao {

    @Query("SELECT * FROM UsuariosEntity WHERE username = :username AND password = :password")
    fun getUser(username: String, password: String): UsuariosEntity

    @Insert
    fun addUser(usersEntity: UsuariosEntity)

    @Update
    fun updateUser(usersEntity: UsuariosEntity)

    @Delete
    fun deleteUser(usersEntity: UsuariosEntity)

}