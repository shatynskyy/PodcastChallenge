package com.podcastchallenge.application.di

import com.podcastchallenge.data.repository.Repository
import com.podcastchallenge.data.repository.RepositoryImpl
import com.podcastchallenge.data.retrofit.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object AppModule {

    @Provides
    fun provideRepository(apiService: ApiService): Repository =
        RepositoryImpl(apiService = apiService)

}