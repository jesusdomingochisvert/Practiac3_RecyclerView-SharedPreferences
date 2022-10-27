package com.example.practica3

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.practica3.databinding.ItemNewsBinding

class NoticiasAdapter(private val news: List<Noticias>, private val listener: OnClickListener) : RecyclerView.Adapter<NoticiasAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        context = parent.context

        val view = LayoutInflater.from(context).inflate(R.layout.item_news, parent, false)

        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val new = news.get(position)

        val url = new.enlaceNoticia

        val news = Noticias(new.id, new.titulo, new.resumen, new.fecha, new.imagenPortada, new.enlaceNoticia)

        with(holder) {

            setListener(news, url, position)

            binding.tvTitle.text = new.titulo
            binding.tvSummary.text = new.resumen
            binding.tvDate.text = new.fecha

            Glide.with(context)
                .load(new.imagenPortada)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .circleCrop()
                .into(binding.imgNews)

        }

    }

    override fun getItemCount(): Int = news.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val binding = ItemNewsBinding.bind(view)

        fun setListener(news: Noticias, url: String, position: Int) {

            binding.root.setOnClickListener {

                listener.onClick(news, url, position)

            }

        }

    }

}