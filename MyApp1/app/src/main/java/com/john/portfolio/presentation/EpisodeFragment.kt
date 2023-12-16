package com.john.portfolio.presentation

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.john.portfolio.R
import com.john.portfolio.data.db.MyDbHelper
import com.john.portfolio.databinding.FragmentEpisodeBinding
import com.john.portfolio.databinding.RatingDialogBinding
import com.john.portfolio.presentation.adapter.EpisodeAdapter
import com.john.portfolio.presentation.adapter.MainLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

private const val EPISODE_NAME = "name"
private const val EPISODE_COUNT = "count"
private const val AIR_DATE = "air_date"
private const val EPISODE = "episode"

@AndroidEntryPoint
class EpisodeFragment : Fragment() {

    private val adapter = EpisodeAdapter {onClickShow()}
    private lateinit var binding: FragmentEpisodeBinding
    private val viewModel: EpisodeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEpisodeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerEpisode.adapter = adapter.withLoadStateFooter(MainLoadStateAdapter())

        binding.swipeRefresh2.setOnRefreshListener {
            adapter.refresh()
        }

        adapter.loadStateFlow.onEach {
            binding.swipeRefresh2.isRefreshing = it.refresh == LoadState.Loading
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.pagedEpisodes.onEach {
            adapter.submitData(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        binding.swipeRefresh2.setColorSchemeResources(
            android.R.color.holo_green_light
        )

        adapter.onItemClick = {
            SheetFragment2().show(parentFragmentManager, "bottomSheetTag2")
            setFragmentResult("requestKey_1", bundleOf(EPISODE_NAME to it.results.first().name))
            setFragmentResult("requestKey_2", bundleOf(EPISODE_COUNT to it.results.first().id))
            setFragmentResult("requestKey_3", bundleOf(AIR_DATE to it.results.first().airDate))
            setFragmentResult("requestKey_4", bundleOf(EPISODE to it.results.first().episode))
        }
    }


            private fun onClickShow() {
                val dialog = BottomSheetDialog(requireContext())
                val customView = RatingDialogBinding.inflate(layoutInflater)
                dialog.setContentView(customView.root)

                dialog.setCancelable(true)

                customView.comBtn.setOnClickListener {
                    val ratingBar = customView.rating
                    val editComments = customView.comments
                    val rating = ratingBar.rating.toString()
                    val comments = editComments.text.toString()
                    if (comments.isEmpty()) {
                        Toast.makeText(context, R.string.toast_vm, Toast.LENGTH_LONG).show()
                    }else{
                        val db = MyDbHelper(requireContext(), null)
                        db.addComment(comments, rating)

                        editComments.text.clear()
                        Toast.makeText(context, R.string.toast_vm_2, Toast.LENGTH_LONG).show()
                        dialog.dismiss()
                    }
                }
                customView.cancelBtn.setOnClickListener {
                    dialog.dismiss()
                }
                dialog.show()
            }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun isNetworkAvailable(context: Context): Boolean{
        val connectionManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities = connectionManager.getNetworkCapabilities(connectionManager.activeNetwork)

        if (capabilities != null){
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)){
                return true
            }else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)){
                return true
            } else if ( capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)){
                return true
            }
        }
        return false
    }
}