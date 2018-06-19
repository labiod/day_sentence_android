package com.bitage.daysentence.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bitage.daysentence.R
import com.bitage.daysentence.dto.SentenceDTO
import kotlinx.android.synthetic.main.day_sentence_list_item.view.*

class SentenceAdapter(val items: List<SentenceDTO>) : RecyclerView.Adapter<SentenceAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.day_sentence_list_item, parent, false)
        return ViewHolder(root)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sentence = items[position]
        holder.itemView.sentenceTitle.text = sentence.title
        holder.itemView.sentenceContent.text = sentence.content
        holder.itemView.sentenceAuthor.text = sentence.author
    }

    class ViewHolder(root: View): RecyclerView.ViewHolder(root)
}