package com.jemy.thamanya.data.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Section(
    @SerializedName("content")
    @SerialName("content")
    val content: List<Content?>? = listOf(),
    @SerializedName("content_type")
    @SerialName("content_type")
    val contentType: String? = "",
    @SerializedName("name")
    @SerialName("name")
    val name: String? = "",
    @SerializedName("order")
    @SerialName("order")
    val order: String? = "0",
    @SerializedName("type")
    @SerialName("type")
    val type: String? = ""
)