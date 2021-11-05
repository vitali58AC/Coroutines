package com.skillbox.github.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Repositories(
    val id: Long?,
    val name: String?,
    @Json(name = "full_name")
    val fullName: String?,
    val owner: User,
    @Json(name = "html_url")
    val htmlUrl: String,
    val description: String?
)
