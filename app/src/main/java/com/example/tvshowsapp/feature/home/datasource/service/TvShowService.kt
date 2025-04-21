package com.example.tvshowsapp.feature.home.datasource.service

import com.example.tvshowsapp.feature.home.datasource.model.TvShowResponse
import retrofit2.http.GET
import retrofit2.Response


interface TvShowService {
    @GET("shows")
    suspend fun getShows(): Response<List<TvShowResponse>>
}