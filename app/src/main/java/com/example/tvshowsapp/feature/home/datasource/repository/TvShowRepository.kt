package com.example.tvshowsapp.feature.home.datasource.repository

import com.example.tvshowsapp.feature.home.datasource.service.TvShowService

class TvShowRepository(private val tvShowService: TvShowService) {
    suspend fun getShows() = tvShowService.getShows()
}