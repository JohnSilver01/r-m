package com.john.portfolio.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.john.portfolio.databinding.LoadStateBinding

class MainLoadStateAdapter: LoadStateAdapter<LoadStateVH>() {
    override fun onBindViewHolder(holder: LoadStateVH, loadState: LoadState) = Unit

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateVH {
        val binding = LoadStateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LoadStateVH(binding)
    }
}
class LoadStateVH(binding: LoadStateBinding):RecyclerView.ViewHolder(binding.root)