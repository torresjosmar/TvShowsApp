package com.example.tvshowsapp.core.network.model

import okhttp3.ResponseBody

data class NetworkData(
    val httpCode: Int,
    val message: String,
    val error: ResponseBody? = null
)