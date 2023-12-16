package com.john.portfolio.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.john.portfolio.data.CharacterPagingSource
import com.john.portfolio.data.api.CharacterApi.Companion.PAGE_SIZE
import com.john.portfolio.domain.repository.CharacterRepository
import com.john.portfolio.models.Characters
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: CharacterRepository
): ViewModel() {
    val pagedCharacters: Flow<PagingData<Response<Characters>>> = Pager(
        config = PagingConfig(pageSize = 42),
        pagingSourceFactory = { CharacterPagingSource(repository) }
    ).flow.cachedIn(viewModelScope)
}
