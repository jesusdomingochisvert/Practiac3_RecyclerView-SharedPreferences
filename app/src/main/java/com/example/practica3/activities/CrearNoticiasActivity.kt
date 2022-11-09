package com.example.practica3.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.practica3.adapters.NoticiasAdapter
import com.example.practica3.applications.NoticiasApplication
import com.example.practica3.entities.NoticiasEntity
import com.example.practica3.databinding.ActivityCrearNoticiasBinding

class CrearNoticiasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCrearNoticiasBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityCrearNoticiasBinding.inflate(layoutInflater)

        setContentView(binding.root)

        // Guardamos en BD al darle al boton Save.

        saveBD()

    }

    private fun saveBD() {

        binding.btnSave.setOnClickListener {

            val newEntity = NoticiasEntity(titulo = binding.titleEt.text.toString().trim(),
                resumen = binding.summaryEt.text.toString().trim(),
                fecha = binding.dateEt.text.toString().trim(),
                imagenPortada = binding.urlImgEt.text.toString().trim(),
                enlaceNoticia = binding.urlEt.text.toString().trim())

            // Guardar en BD

            Thread {

                NoticiasApplication.database.newDao().addNew(newEntity)

            }.start()

            //newsAdapter.add(newEntity)

            val intent = Intent(this, NoticiasActivity::class.java)

            startActivity(intent)

        }

    }

}