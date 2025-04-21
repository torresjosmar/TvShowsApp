package com.example.tvshowsapp.core.network

import retrofit2.Response
import com.example.tvshowsapp.core.network.model.NetworkData

class NetworkResponse<T>(responseNetwork: Response<T>) {
    var network: NetworkData = NetworkData(responseNetwork.code(), responseNetwork.message(), responseNetwork.errorBody())
    var data: T? = responseNetwork.body()
}