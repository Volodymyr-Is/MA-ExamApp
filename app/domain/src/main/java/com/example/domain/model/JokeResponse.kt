package com.example.domain.model

import com.squareup.moshi.Json

data class JokeResponse(
    @Json(name = "data") val data: String
)