package com.john.portfolio.data.repository

import com.john.portfolio.data.api.RetrofitInstance
import com.john.portfolio.domain.repository.CharacterRepository
import com.john.portfolio.models.Characters
import com.john.portfolio.models.Episodes
import retrofit2.Response
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(): CharacterRepository {

    override suspend fun getApiCharacters(page: Int): Response<Characters> {
        return RetrofitInstance.searchApi.getAllCharacters(page)
    }

    override suspend fun getApiEpisodes(page: Int): Response<Episodes> {
        return RetrofitInstance.searchApi.getAllEpisodes(page)
    }
}