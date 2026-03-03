package com.jemy.thamanya.data.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pagination(
    @SerialName("next_page")
    val nextPage: String? = "",
    @SerialName("total_pages")
    val totalPages: Int? = 0
)