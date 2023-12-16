package com.john.portfolio.models

import com.google.gson.annotations.SerializedName

data class DataEpisode(
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("air_date")
    var airDate: String,
    @SerializedName("episode")
    var episode: String
)
