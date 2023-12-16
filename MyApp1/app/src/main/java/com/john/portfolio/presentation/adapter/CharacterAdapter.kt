package com.john.portfolio.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.john.portfolio.models.Characters
import com.john.portfolio.databinding.CharacterItemBinding
import retrofit2.Response

class CharacterAdapter
    : PagingDataAdapter<Response<Characters>, CharacterAdapter.CharacterHolder>(
    MainDiffUtilItemCallback
) {

    var onItemClick: ((Characters) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterHolder {
        val binding = CharacterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterHolder, position: Int) {
        val character = getItem(position)
        with(holder.binding){
            character?.let{
            characterName.text = character.body()?.results?.first()?.name
            species.text = character.body()?.results?.first()?.species
            location.text = character.body()?.results?.first()?.location?.name
            gender.text = character.body()?.results?.first()?.gender
            Glide
                .with(characterImage.context)
                .load(character.body()?.results?.first()?.image)
                .into(characterImage)

            detailBtn.setOnClickListener{
                character.body()?.let { it1 -> onItemClick?.invoke(it1) }
            }
        }
        }
    }

    object MainDiffUtilItemCallback: DiffUtil.ItemCallback<Response<Characters>>(){
        override fun areItemsTheSame(oldItem: Response<Characters>, newItem: Response<Characters>): Boolean {
            return oldItem.body()?.results?.first()?.id == newItem.body()?.results?.first()?.id
        }

        override fun areContentsTheSame(oldItem: Response<Characters>, newItem: Response<Characters>): Boolean {
            return oldItem.body() == newItem.body()
        }
    }

    inner class CharacterHolder(val binding: CharacterItemBinding): RecyclerView.ViewHolder(binding.root)
}
