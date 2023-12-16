package com.john.portfolio.models

import com.google.gson.annotations.SerializedName

data class Characters(
    @SerializedName("results")
    var results: List<DataCharacter>
)