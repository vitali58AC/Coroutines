package com.skillbox.github.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(
    val login: String,
    val id: Long?,
    @Json(name = "avatar_url")
    val avatarUrl: String,
    val name: String?,
    @Json(name = "html_url")
    val url: String,
    val type: String,
    val company: String?,
    val location: String?,
    @Json(name = "public_repos")
    val publicRepos: Long?,
    val followers: Long?,
    val following: Long?,
    @Json(name = "created_at")
    val createdAt: String?,
    @Json(name = "updated_at")
    val updatedAt: String?

)
