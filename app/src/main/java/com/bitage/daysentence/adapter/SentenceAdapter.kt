package com.bitage.daysentence.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bitage.daysentence.R
import com.bitage.daysentence.databinding.DaySentenceListItemBinding
import com.bitage.daysentence.dto.SentenceDTO

class SentenceAdapter(val items: List<SentenceDTO>) : RecyclerView.Adapter<SentenceAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binder = DaySentenceListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binder)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sentence = items[position]
        holder.binder.sentenceTitle.text = sentence.title
        holder.binder.sentenceContent.text = sentence.content
        holder.binder.sentenceAuthor.text = sentence.author
    }

    class ViewHolder(val binder: DaySentenceListItemBinding): RecyclerView.ViewHolder(binder.root)
}