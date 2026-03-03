package com.jemy.thamanya.data.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResult(
    @SerialName("sections")
    val sections: List<Section>? = listOf()
)