package com.skillbox.github.network

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PatchUser(
    val name: String
)