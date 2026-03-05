package com.jemy.thamanya.data.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HomeResponse(
    @SerializedName("pagination")
    @SerialName("pagination")
    val pagination: Pagination? = Pagination(),
    @SerializedName("sections")
    @SerialName("sections")
    val sections: List<Section>? = listOf()
)