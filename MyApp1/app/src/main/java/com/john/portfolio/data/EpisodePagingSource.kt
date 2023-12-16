package com.john.portfolio.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.john.portfolio.domain.repository.CharacterRepository
import com.john.portfolio.models.Episodes
import retrofit2.Response
import javax.inject.Inject

class EpisodePagingSource @Inject constructor(
    private val repository: CharacterRepository
): PagingSource<Int, Response<Episodes>>() {

    override fun getRefreshKey(state: PagingState<Int, Response<Episodes>>): Int = FIRST_PAGE

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Response<Episodes>> {
        val page = params.key ?: FIRST_PAGE
        return kotlin.runCatching {
            repository.getApiEpisodes(page)
        }.fold(
            onSuccess = {
                LoadResult.Page(
                    data = listOf(it),
                    prevKey = null,
                    nextKey = if (listOf(it).isEmpty()) null else page + 1
                )
            },
            onFailure = { LoadResult.Error(it) }
        )
    }

    private companion object {
        private const val FIRST_PAGE = 1
    }
}