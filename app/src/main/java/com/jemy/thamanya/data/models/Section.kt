package com.jemy.thamanya.data.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Section(
    @SerialName("content")
    val content: List<Content?>? = listOf(),
    @SerialName("content_type")
    val contentType: String? = "",
    @SerialName("name")
    val name: String? = "",
    @SerialName("order")
    val order: Int? = 0,
    @SerialName("type")
    val type: String? = ""
)