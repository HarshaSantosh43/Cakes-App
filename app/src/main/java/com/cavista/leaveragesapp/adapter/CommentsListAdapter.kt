package com.cavista.leaveragesapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.cavista.leaveragesapp.R
import com.cavista.leaveragesapp.models.CommentsModel


class CommentsListAdapter internal constructor(context: Context) : RecyclerView.Adapter<CommentsListAdapter.CommentsViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var comments = emptyList<CommentsModel>()

    inner class CommentsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wordItemView: TextView = itemView.findViewById(R.id.textView_Comments)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
        val itemView = inflater.inflate(R.layout.item_comments_list, parent, false)
        return CommentsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        val current = comments[position]
        holder.wordItemView.text = current.comment
    }

    internal fun setComments(comments: List<CommentsModel>) {
        this.comments = comments
        notifyDataSetChanged()
    }

    override fun getItemCount() = comments.size
}


