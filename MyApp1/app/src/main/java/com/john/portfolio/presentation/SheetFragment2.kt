package com.john.portfolio.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.john.portfolio.databinding.FragmentSheet2Binding

private const val EPISODE_NAME = "name"
private const val EPISODE_COUNT = "count"
private const val AIR_DATE = "air_date"
private const val EPISODE = "episode"

class SheetFragment2: BottomSheetDialogFragment() {

    private lateinit var binding: FragmentSheet2Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSheet2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.dismissBtn.setOnClickListener {
            dismiss()
        }

        getData()
    }

    private fun getData(){
        setFragmentResultListener("requestKey_1"){_, bundle ->
            val name = bundle.getString(EPISODE_NAME)
            binding.episodeName.text = "Название: $name"
        }
        setFragmentResultListener("requestKey_2"){_, bundle ->
            val count = bundle.getInt(EPISODE_COUNT)
            binding.episodeCount.text = "№$count"
        }
        setFragmentResultListener("requestKey_3"){_, bundle ->
            val airDate = bundle.getString(AIR_DATE)
            binding.airDate.text = "Дата выхода: $airDate"
        }
        setFragmentResultListener("requestKey_4"){_, bundle ->
            val episode = bundle.getString(EPISODE)
            binding.episode.text = "Эпизод: $episode"
        }
    }
}