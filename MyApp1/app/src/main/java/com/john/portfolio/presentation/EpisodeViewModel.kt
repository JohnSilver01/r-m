package com.john.portfolio.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.john.portfolio.data.EpisodePagingSource
import com.john.portfolio.domain.repository.CharacterRepository
import com.john.portfolio.models.DataComments
import com.john.portfolio.models.Episodes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class EpisodeViewModel @Inject constructor(
    private val repository: CharacterRepository
): ViewModel() {
    val pagedEpisodes: Flow<PagingData<Response<Episodes>>> = Pager(
        config = PagingConfig(pageSize = 3),
        pagingSourceFactory = { EpisodePagingSource(repository) }
    ).flow.cachedIn(viewModelScope)
}