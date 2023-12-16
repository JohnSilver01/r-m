package com.john.portfolio.models

import com.google.gson.annotations.SerializedName

data class Episodes(
    @SerializedName("results")
    var results: List<DataEpisode>
)
