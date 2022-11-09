package com.example.practica3.interfaces

import com.example.practica3.entities.NoticiasEntity

interface OnClickListener {

    fun onClick(news: NoticiasEntity, url: String, position: Int)

    fun onDeleteNew(newEntity: NoticiasEntity)

}