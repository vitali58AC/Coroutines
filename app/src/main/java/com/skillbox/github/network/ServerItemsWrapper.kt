package com.skillbox.github.network

import com.squareup.moshi.JsonClass


//Это для обертки объектов внутри JSON!
@JsonClass(generateAdapter = true)
data class ServerItemsWrapper<T>(
    val items: List<T>
)
