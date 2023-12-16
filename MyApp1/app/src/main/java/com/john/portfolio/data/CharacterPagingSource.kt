package com.john.portfolio.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.john.portfolio.domain.repository.CharacterRepository
import com.john.portfolio.models.Characters
import retrofit2.Response
import javax.inject.Inject

class CharacterPagingSource @Inject constructor(
    private val repository: CharacterRepository
): PagingSource<Int, Response<Characters>>() {
    
    override fun getRefreshKey(state: PagingState<Int, Response<Characters>>): Int = FIRST_PAGE

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Response<Characters>> {
        val page = params.key?: FIRST_PAGE
        return kotlin.runCatching {
            repository.getApiCharacters(page)
        }.fold(
            onSuccess = {
                LoadResult.Page(
                    data = listOf(it),
                    prevKey = null,
                    nextKey = if (listOf(it).isEmpty()) null else page + 1
                )
            },
            onFailure = {LoadResult.Error(it)}
        )
    }
      private companion object{
        private const val FIRST_PAGE = 1
    }
}