package com.john.portfolio.domain.repository

import com.john.portfolio.data.api.RetrofitInstance
import com.john.portfolio.models.Characters
import com.john.portfolio.models.Episodes
import retrofit2.Response

interface CharacterRepository {

    suspend fun getApiCharacters(page: Int): Response<Characters> {
        return RetrofitInstance.searchApi.getAllCharacters(page)
    }

    suspend fun getApiEpisodes(page: Int): Response<Episodes> {
        return RetrofitInstance.searchApi.getAllEpisodes(page)
    }
}