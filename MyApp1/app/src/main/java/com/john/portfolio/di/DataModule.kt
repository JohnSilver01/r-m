package com.john.portfolio.di
import com.john.portfolio.data.repository.CharacterRepositoryImpl
import com.john.portfolio.domain.repository.CharacterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideCharacterRepository(): CharacterRepository {
        return CharacterRepositoryImpl()
    }
}