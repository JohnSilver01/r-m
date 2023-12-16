package com.john.portfolio.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.john.portfolio.data.db.MyDbHelper
import com.john.portfolio.databinding.FragmentCommentsBinding
import com.john.portfolio.models.DataComments
import com.john.portfolio.presentation.adapter.CommentAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommentsFragment: Fragment() {

    private lateinit var binding: FragmentCommentsBinding
    private val adapter = CommentAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCommentsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerComments.adapter = adapter

        adapter.onItemClick = {position ->
            delete(position)
        }
        getComment()

        fillAdapter()
    }

    private fun getComment(): List<DataComments> {
        val db = MyDbHelper(requireContext(), null)
        if(adapter.commentList.isEmpty()){
            binding.recyclerComments.isVisible = false
            binding.emptyAdapterTv.isVisible = true
        }else{
            binding.recyclerComments.isVisible = true
            binding.emptyAdapterTv.isVisible = false
        }
        return db.readData()
    }

    private fun delete(id: Int){
        val db = MyDbHelper(requireContext(), null)
        db.delete(id)
    }

    private fun fillAdapter(){
        adapter.setComment(getComment())
    }

    override fun onResume() {
        super.onResume()
        getComment()
        fillAdapter()
    }
}