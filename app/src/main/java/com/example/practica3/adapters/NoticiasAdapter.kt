package com.example.practica3.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.practica3.entities.NoticiasEntity
import com.example.practica3.interfaces.OnClickListener
import com.example.practica3.R
import com.example.practica3.databinding.ItemNewsBinding

class NoticiasAdapter(private var news: MutableList<NoticiasEntity>,
                      private val listener: OnClickListener) : RecyclerView.Adapter<NoticiasAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        context = parent.context

        val view = LayoutInflater.from(context).inflate(R.layout.item_news, parent, false)

        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val new = news.get(position)

        val url = new.enlaceNoticia

        val news = NoticiasEntity(new.id, new.titulo, new.resumen, new.fecha, new.imagenPortada, new.enlaceNoticia)

        with(holder) {

            setListener(news, url, position)

            with(binding) {

                tvTitle.text = new.titulo
                tvSummary.text = new.resumen
                tvDate.text = new.fecha

            }

            Glide.with(context)
                .load(new.imagenPortada)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .circleCrop()
                .into(binding.imgNews)

        }

    }

    override fun getItemCount(): Int = news.size

    /*fun add(newEntity: NoticiasEntity) {

        news.add(newEntity)

        notifyDataSetChanged()

    }*/

    fun setNoticias(newsEntity: MutableList<NoticiasEntity>) {

        this.news = newsEntity

        notifyDataSetChanged()

    }

    /*fun update(newEntity: NoticiasEntity) {

        val index = news.indexOf(newEntity)

        if (index != -1) {

            news.set(index, newEntity)

            notifyItemChanged(index)

        }

    }*/

    fun delete(newEntity: NoticiasEntity) {

        val index = news.indexOf(newEntity)

        if (index != -1) {

            news.removeAt(index)

            notifyItemChanged(index)

        }

    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val binding = ItemNewsBinding.bind(view)

        fun setListener(news: NoticiasEntity, url: String, position: Int) {

            with(binding) {

                root.setOnClickListener {

                    listener.onClick(news, url, position)

                }

                root.setOnLongClickListener {

                    listener.onDeleteNew(news)

                    true

                }

            }

        }

    }

}