package com.example.tvshowsapp.feature.home.usecase

import com.example.tvshowsapp.feature.home.datasource.repository.TvShowRepository

class TvShowUseCase(private val tvShowRepository: TvShowRepository)
{
    suspend fun getShows() = tvShowRepository.getShows()
}