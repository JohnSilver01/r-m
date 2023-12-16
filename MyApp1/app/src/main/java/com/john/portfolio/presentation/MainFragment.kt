package com.john.portfolio.presentation

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.john.portfolio.presentation.adapter.CharacterAdapter
import com.john.portfolio.R
import com.john.portfolio.presentation.adapter.MainLoadStateAdapter
import com.john.portfolio.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

private const val IMAGE = "image"
private const val SPECIES = "species"
private const val GENDER = "gender"
private const val NAME = "name"

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val adapter = CharacterAdapter()
    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by viewModels()

    private var broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val notConnected = intent.getBooleanExtra(ConnectivityManager
                .EXTRA_NO_CONNECTIVITY, false)
            if (notConnected) {
                disconnected()
            } else {
                connected()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recycler = view.findViewById<RecyclerView>(R.id.recycler_view)

        recycler.adapter = adapter.withLoadStateFooter(MainLoadStateAdapter())

        binding.swipeRefresh.setOnRefreshListener {
            adapter.refresh()
        }

        adapter.loadStateFlow.onEach {
            binding.swipeRefresh.isRefreshing = it.refresh == LoadState.Loading
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.pagedCharacters.onEach {
            adapter.submitData(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        adapter.onItemClick = {
            SheetFragment().show(parentFragmentManager, "bottomSheetTag")
            setFragmentResult("requestKey1", bundleOf(IMAGE to it.results.first().image))
            setFragmentResult("requestKey2", bundleOf(NAME to it.results.first().name))
            setFragmentResult("requestKey3", bundleOf(SPECIES to it.results.first().species))
            setFragmentResult("requestKey4", bundleOf(GENDER to it.results.first().gender))
        }

        binding.swipeRefresh.setColorSchemeResources(
            android.R.color.holo_green_light
        )
        }

    @SuppressWarnings("deprecation")
    override fun onStart() {
        super.onStart()
        context?.let {
            LocalBroadcastManager.getInstance(it)
                .registerReceiver(broadcastReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        }
    }

    @SuppressWarnings("deprecation")
    override fun onStop() {
        super.onStop()
        context?.let {
            LocalBroadcastManager.getInstance(it)
                .registerReceiver(broadcastReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        }
    }

    private fun disconnected() {
        binding.recyclerView.visibility = View.INVISIBLE
        binding.noInternetLottie?.visibility = View.VISIBLE
        binding.noInternetTv?.visibility = View.VISIBLE
    }

    private fun connected() {
                binding.recyclerView.visibility = View.VISIBLE
                binding.noInternetLottie?.visibility = View.INVISIBLE
                binding.noInternetTv?.visibility = View.INVISIBLE
                fetchFeeds()
    }

    private fun fetchFeeds() {
        val recycler = requireView().findViewById<RecyclerView>(R.id.recycler_view)

        recycler.adapter = adapter.withLoadStateFooter(MainLoadStateAdapter())

        binding.swipeRefresh.setOnRefreshListener {
            adapter.refresh()
        }

        adapter.loadStateFlow.onEach {
            binding.swipeRefresh.isRefreshing = it.refresh == LoadState.Loading
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.pagedCharacters.onEach {
            adapter.submitData(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        adapter.onItemClick = {
            SheetFragment().show(parentFragmentManager, "bottomSheetTag")
            setFragmentResult("requestKey1", bundleOf(IMAGE to it.results.first().image))
            setFragmentResult("requestKey2", bundleOf(NAME to it.results.first().name))
            setFragmentResult("requestKey3", bundleOf(SPECIES to it.results.first().species))
            setFragmentResult("requestKey4", bundleOf(GENDER to it.results.first().gender))
        }

        binding.swipeRefresh.setColorSchemeResources(
            android.R.color.holo_green_light
        )
    }
}
