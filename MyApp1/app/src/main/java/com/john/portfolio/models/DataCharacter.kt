package com.john.portfolio.models

import com.google.gson.annotations.SerializedName

data class DataCharacter(
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("status")
    var status: String,
    @SerializedName("type")
    var type: String,
    @SerializedName("species")
    var species: String,
    @SerializedName("origin")
    var origin: Location,
    @SerializedName("gender")
    var gender: String,
    @SerializedName("image")
    var image: String,
    @SerializedName("location")
    var location: Location,
    @SerializedName("url")
    var url: String,
    @SerializedName("created")
    var created: String,
    @SerializedName("episode")
    var episode: List<String>
)
