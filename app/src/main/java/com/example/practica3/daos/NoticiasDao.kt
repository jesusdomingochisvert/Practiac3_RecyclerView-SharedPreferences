package com.example.practica3.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.practica3.entities.NoticiasEntity

@Dao
interface NoticiasDao {

    @Query("SELECT * FROM NoticiasEntity")
    fun getAllNews() : MutableList<NoticiasEntity>

    @Insert
    fun addNew(newEntity: NoticiasEntity)

    @Update
    fun updateNew(newEntity: NoticiasEntity)

    @Delete
    fun deleteNew(newEntity: NoticiasEntity)

}