package com.example.practica3.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.practica3.applications.NoticiasApplication
import com.example.practica3.entities.UsuariosEntity
import com.example.practica3.databinding.ActivityRegistroBinding

class RegistroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityRegistroBinding.inflate(layoutInflater)

        setContentView(binding.root)

        saveBD()

    }

    private fun saveBD() {

        binding.fabConfirmRegistro.setOnClickListener {

            val userEntity = UsuariosEntity(username = binding.tietUserRegistro.text.toString().trim(),
            password = binding.tietPassRegistro.text.toString().trim())

            Thread {

                NoticiasApplication.database.userDao().addUser(userEntity)

            }.start()

        }

    }

}