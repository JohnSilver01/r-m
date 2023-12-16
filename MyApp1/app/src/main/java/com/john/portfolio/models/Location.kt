package com.john.portfolio.models

import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("name")
    var name: String,
    @SerializedName("url")
    var url: String
)
