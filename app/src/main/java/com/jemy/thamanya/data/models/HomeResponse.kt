package com.jemy.thamanya.data.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HomeResponse(
    @SerialName("pagination")
    val pagination: Pagination? = Pagination(),
    @SerialName("sections")
    val sections: List<Section?>? = listOf()
)