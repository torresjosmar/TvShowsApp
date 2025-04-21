package com.example.tvshowsapp.feature.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tvshowsapp.core.network.NetworkResponse
import com.example.tvshowsapp.feature.home.datasource.model.TvShowResponse
import com.example.tvshowsapp.feature.home.usecase.TvShowUseCase
import kotlinx.coroutines.launch
import java.net.HttpURLConnection

class TvShowsViewModel(private val tvShowUseCase: TvShowUseCase) : ViewModel()  {
    var dataList: List<TvShowResponse> = listOf()
    private val _onLoading = MutableLiveData<Boolean>()
    val onLoading: LiveData<Boolean> get() = _onLoading

    private val _onNetworkError = MutableLiveData<Unit>()
    val onNetworkError: LiveData<Unit> get() = _onNetworkError

    private val _onGetTvShows = MutableLiveData<List<TvShowResponse>>()
    val onGetTvShows: LiveData<List<TvShowResponse>> get() = _onGetTvShows

    fun getTvShows(){
        viewModelScope.launch {
            loadingPostValue(true)
            val response = NetworkResponse(tvShowUseCase.getShows())
            when(response.network.httpCode){
                HttpURLConnection.HTTP_OK-> {
                    dataList = response.data!!
                    onGetTvShowsPostValue(dataList)
                }else -> {
                    onNetworkErrorPostValue()
                }
            }
        }
    }



    fun loadingPostValue(loading: Boolean) {
        _onLoading.postValue(loading)
    }

    private fun onNetworkErrorPostValue() {
        loadingPostValue(false)
        _onNetworkError.postValue(Unit)
    }

    private fun onGetTvShowsPostValue(data: List<TvShowResponse>) {
        loadingPostValue(false)
        _onGetTvShows.postValue(data)
    }
}