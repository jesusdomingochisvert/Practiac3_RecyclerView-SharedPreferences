package com.example.practica3

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.practica3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    val url = "https://w0.peakpx.com/wallpaper/460/802/HD-wallpaper-periodico-papel-thanks-verses.jpg"

    var position: Int = 0

    var lastTitle: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        loadImage(url)

        val preferences = getPreferences(Context.MODE_PRIVATE)

        remmember(preferences)

        // Guardamos los datos de los EditText de Nombre Usuario y Contraseña en un SharedPreferences
        // al clickar en el FloatingAppButton.

        setupSharedPreferences(preferences)

    }

    private fun setupSharedPreferences(preferences: SharedPreferences) {

        val intent = Intent(this, NoticiasActivity::class.java)

        binding.fabConfirm.setOnClickListener {

            preferences.edit().putString("nombreUsuario", binding.tietUser.text.toString()).apply()
            preferences.edit().putString("contraseñaUsuario", binding.tietPass.text.toString()).apply()

            val nombreUsuario = preferences.getString("nombreUsuario", binding.tietUser.text.toString())
            val contraseñaUsuario = preferences.getString("contraseñaUsuario", binding.tietPass.text.toString())

            Log.i("Shared", "${nombreUsuario}")
            Log.i("Shared", "${contraseñaUsuario}")

            startActivity(intent)

        }

    }

    private fun remmember(preferences: SharedPreferences) {

        val nombreUsuario = preferences.getString("nombreUsuario", binding.tietUser.text.toString())
        val contraseñaUsuario = preferences.getString("contraseñaUsuario", binding.tietPass.text.toString())

        if (nombreUsuario!!.isNotEmpty() && contraseñaUsuario!!.isNotEmpty()) {

            binding.tilUser.editText!!.setText(nombreUsuario)
            binding.tilPass.editText!!.setText(contraseñaUsuario)

        }

    }

    private fun loadImage(url: String) {

        Glide.with(this)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .centerCrop()
            .into(binding.iv)

    }

}