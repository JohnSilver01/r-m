package com.john.portfolio.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.john.portfolio.R
import com.john.portfolio.models.DataComments
import com.john.portfolio.databinding.CommentItemBinding

class CommentAdapter: RecyclerView.Adapter<CommentAdapter.CommentHolder>() {

    var commentList: List<DataComments> = emptyList()
    var onItemClick: ((Int) -> Unit)? = null

    fun setComment(commentList: List<DataComments>){
        this.commentList = commentList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentHolder {
        val binding = CommentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentHolder(binding)
    }

    override fun getItemCount(): Int = commentList.size

    override fun onBindViewHolder(holder: CommentHolder, position: Int) {
        val comment = commentList.getOrNull(position)
        with(holder.binding) {
            //itemEpisodeNumber.text = comment?.id.toString()
            itemRating.text = comment?.rating
            itemComments.text = comment?.comments

            deleteBtn.setOnClickListener {
                    onItemClick?.invoke(position)
                    Toast.makeText(holder.itemView.context, R.string.toast_vm_3, Toast.LENGTH_LONG).show()
            }
        }
    }

    inner class CommentHolder(val binding: CommentItemBinding): RecyclerView.ViewHolder(binding.root)
}