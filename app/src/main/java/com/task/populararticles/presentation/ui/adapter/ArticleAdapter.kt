package com.task.populararticles.presentation.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.task.populararticles.databinding.ItemRecyclerArticleBinding
import com.task.populararticles.domain.model.ArticleData
import com.task.populararticles.presentation.ui.interfaces.OnRecyclerItemClickListener
import com.task.populararticles.utile.click
import javax.inject.Inject

class ArticleAdapter @Inject constructor() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var list: List<ArticleData>? = null
    private lateinit var context: Context
    private var listener: OnRecyclerItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecyclerArticleBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = list?.size ?: 0

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val holder = holder as ViewHolder
        with(holder.binding)
        {
            model = list!![holder.layoutPosition]
            layoutItemRecyclerArticle.click {
                listener?.let {
                    listener?.onRecyclerItemClickListener(list!![holder.layoutPosition])
                }
            }
            showDivider = position != list?.size!! - 1
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun addData(list: List<ArticleData>) {
        this.list = list
        notifyDataSetChanged()

    }

    fun addListener(listener: OnRecyclerItemClickListener) {
        this.listener = listener
    }

    class ViewHolder(var binding: ItemRecyclerArticleBinding) :
        RecyclerView.ViewHolder(binding.root)

}