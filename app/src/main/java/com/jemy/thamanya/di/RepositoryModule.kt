package com.jemy.thamanya.di


import com.jemy.thamanya.data.remote.ApiService
import com.jemy.thamanya.data.remote.ApiServiceForSearch
import com.jemy.thamanya.data.repo.HomeRepository
import com.jemy.thamanya.data.repo.SearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideUserHomeRepository(api: ApiService): HomeRepository {
        return HomeRepository(api)
    }

    @Singleton
    @Provides
    fun provideUserSearchRepository(api: ApiServiceForSearch): SearchRepository {
        return SearchRepository(api)
    }
}