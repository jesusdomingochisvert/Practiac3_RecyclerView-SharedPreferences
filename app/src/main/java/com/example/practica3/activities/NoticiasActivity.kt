package com.example.practica3.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practica3.adapters.NoticiasAdapter
import com.example.practica3.applications.NoticiasApplication
import com.example.practica3.entities.NoticiasEntity
import com.example.practica3.interfaces.OnClickListener
import com.example.practica3.databinding.ActivityNoticiasBinding
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class NoticiasActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityNoticiasBinding

    private lateinit var newsAdapter: NoticiasAdapter

    private lateinit var linearLayoutManager: RecyclerView.LayoutManager

    private val openURL = Intent(Intent.ACTION_VIEW)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityNoticiasBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val preferences = getPreferences(Context.MODE_PRIVATE)

        val intent = Intent(this, CrearNoticiasActivity::class.java)

        // Obtenemos la ultima noticia leida.

        lastTitleNew(preferences)

        // Iniciamos el RecyclerView

        setupRecyclerView()

        // Vamos a crear noticias

        goActivity(intent)

        // Cerrar sesión.

        binding.btnLogOut.setOnClickListener {

            logOut()

        }

    }

    private fun setupRecyclerView() {

        newsAdapter = NoticiasAdapter(mutableListOf(), this)

        linearLayoutManager = LinearLayoutManager(this)

        getNews()

        binding.rv.apply {

            layoutManager = linearLayoutManager

            adapter = newsAdapter

        }

    }

    private fun lastTitleNew(preferences: SharedPreferences) {

        val lastTitle = preferences.getString("lastTitle", "")

        if (lastTitle!!.isNotEmpty()) {

            Toast.makeText(this, "Titulo Última Noticias Leída: $lastTitle", Toast.LENGTH_SHORT).show()

        }

    }

    private fun getNews() {

        doAsync {

            val news = NoticiasApplication.database.newDao().getAllNews()

            uiThread {

                newsAdapter.setNoticias(news)


            }

        }

    }

    private fun goActivity(intent: Intent) {

        binding.fab.setOnClickListener {

            startActivity(intent)

        }

    }

    private fun logOut() {

        val intent = Intent(this, MainActivity::class.java)

        val preferences = getPreferences(Context.MODE_PRIVATE)

        with(preferences.edit()) {

            putString("active", "false")

            apply()

        }

        startActivity(intent)

        finish()

    }

    override fun onClick(news: NoticiasEntity, url: String, position: Int) {

        val preferences = getPreferences(Context.MODE_PRIVATE)

        preferences.edit().putString("lastTitle", news.titulo).apply()

        openURL.data = Uri.parse(url)

        startActivity(openURL)

    }

    override fun onDeleteNew(newEntity: NoticiasEntity) {

        doAsync {

            NoticiasApplication.database.newDao().deleteNew(newEntity)

            uiThread {

                newsAdapter.delete(newEntity)

            }

        }

    }

}
