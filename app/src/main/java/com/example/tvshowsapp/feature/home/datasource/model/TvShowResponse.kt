package com.example.tvshowsapp.feature.home.datasource.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TvShowResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("url") val url: String? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("type") val type: String? = null,
    @SerializedName("status") val status: String? = null,
    @SerializedName("language") val language: String? = null,
    @SerializedName("premiered") val premiered: String? = null,
    @SerializedName("ended") val ended: String? = null,
    @SerializedName("officialSite") val officialSite: String? = null,
    @SerializedName("summary") val summary: String? = null,
    @SerializedName("image") val image: Image
): Serializable

data class Image(
    @SerializedName("medium") val medium: String? = null,
    @SerializedName("original") val original: String? = null
): Serializable
