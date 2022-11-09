package com.example.practica3.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.practica3.applications.NoticiasApplication
import com.example.practica3.databinding.ActivityMainBinding
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    val url = "https://w0.peakpx.com/wallpaper/460/802/HD-wallpaper-periodico-papel-thanks-verses.jpg"

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        loadImage(url)

        val preferences = getPreferences(Context.MODE_PRIVATE)

        val intentRegistro = Intent(this, RegistroActivity::class.java)
        val intentNoticias = Intent(this, NoticiasActivity::class.java)

        // Recordar el último usuario login.

        //remmember(preferences)

        // Checkear Usuario y Contraseña.

        checkLogIn(preferences)

        // Guardamos los datos de los EditText de Nombre Usuario y Contraseña en un SharedPreferences
        // al clickar en el FloatingAppButton.

        setupSharedPreferences(preferences, intentNoticias)

        // Volvemos al Login

        goActivity(intentRegistro)

    }

    private fun goActivity(intent: Intent) {

        binding.btnRegister.setOnClickListener {

            startActivity(intent)

        }

    }

    private fun setupSharedPreferences(preferences: SharedPreferences, intent: Intent) {

        binding.fabConfirm.setOnClickListener {

            // Recordar el usuario login con CheckBox.

            rememberUser(preferences, intent)

        }

    }

    private fun rememberUser(preferences: SharedPreferences, intent: Intent) {

        val nombreUsuario = binding.tietUser.text.toString()
        val contraseñaUsuario = binding.tietPass.text.toString()

        doAsync {

            val user = NoticiasApplication.database.userDao().getUser(nombreUsuario, contraseñaUsuario)

            uiThread {

                if (user.username != nombreUsuario && user.password != contraseñaUsuario) {

                    toast("Usuario NO encontrado")

                } else {

                    toast("Usuario encontrado")

                    if (nombreUsuario.isNotEmpty() && contraseñaUsuario.isNotEmpty()) {

                        val checkBox = binding.chbRemember

                        if (checkBox.isChecked) {

                            with(preferences.edit()) {

                                putString("nombreUsuario", nombreUsuario)
                                putString("contraseñaUsuario", contraseñaUsuario)
                                putString("remember", "true")

                                clear()

                                apply()

                            }

                        } else {

                            with(preferences.edit()) {

                                putString("remember", "false")



                                apply()

                            }

                        }

                        startActivity(intent)

                        //finish()

                    } else {

                        toast("Failed... Try again")

                    }

                }

            }

        }

        Thread.sleep(1000)

        //Log.i("Shared", "${nombreUsuario}")
        //Log.i("Shared", "${contraseñaUsuario}")

    }

    private fun checkLogIn(preferences: SharedPreferences) {

        val intent = Intent(this, NoticiasActivity::class.java)

        val usernameET = binding.tietUser
        val passwordET = binding.tietPass
        val username = preferences.getString("nombreUsuario", "")
        val password = preferences.getString("contraseñaUsuario", "")
        val remember = preferences.getString("remember", "")

        if (remember == "true") {

            usernameET.setText(username)
            passwordET.setText(password)

            startActivity(intent)

        } else {

            usernameET.setText(username)
            passwordET.setText(password)

        }

    }

    /*private fun remmember(preferences: SharedPreferences) {

        val nombreUsuario = preferences.getString("nombreUsuario", binding.tietUser.text.toString())
        val contraseñaUsuario = preferences.getString("contraseñaUsuario", binding.tietPass.text.toString())

        if (nombreUsuario!!.isNotEmpty() && contraseñaUsuario!!.isNotEmpty()) {

            binding.tilUser.editText!!.setText(nombreUsuario)
            binding.tilPass.editText!!.setText(contraseñaUsuario)

        }

    }*/

    private fun loadImage(url: String) {

        Glide.with(this)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .centerCrop()
            .into(binding.iv)

    }

}