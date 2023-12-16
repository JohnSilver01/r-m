package com.john.portfolio.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.john.portfolio.databinding.EpisodeItemBinding
import com.john.portfolio.models.Episodes
import retrofit2.Response

class EpisodeAdapter(val onClickShow: (Int) -> Unit) : PagingDataAdapter<Response<Episodes>, EpisodeAdapter.EpisodeVH>(
MainDiffUtilItemCallback
) {
    var onItemClick: ((Episodes) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeVH {
        val binding = EpisodeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EpisodeVH(binding)
    }

    override fun onBindViewHolder(holder: EpisodeVH, position: Int) {
        val episode = getItem(position)
        with(holder.binding){
            episodeName.text = episode?.body()?.results?.first()?.name
            episodeCount.text = episode?.body()?.results?.first()?.id.toString()
            airDate.text = episode?.body()?.results?.first()?.airDate
            this.episode.text = episode?.body()?.results?.first()?.episode

            itemHolder2.setOnClickListener {
                episode?.body()?.let { it1 -> onItemClick?.invoke(it1) }
            }

            btnComments.setOnClickListener {
                showDialog(position)
                }
            }
        }

    fun showDialog(index: Int){
        onClickShow(index)
    }

    object MainDiffUtilItemCallback: DiffUtil.ItemCallback<Response<Episodes>>(){
        override fun areItemsTheSame(oldItem: Response<Episodes>, newItem: Response<Episodes>): Boolean {
            return oldItem.body()?.results?.first()?.id == newItem.body()?.results?.first()?.id
        }

        override fun areContentsTheSame(oldItem: Response<Episodes>, newItem: Response<Episodes>): Boolean {
            return oldItem.body() == newItem.body()
        }
    }

    inner class EpisodeVH(val binding: EpisodeItemBinding): RecyclerView.ViewHolder(binding.root)
}