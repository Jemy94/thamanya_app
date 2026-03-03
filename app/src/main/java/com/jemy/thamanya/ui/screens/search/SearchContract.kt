package com.jemy.thamanya.ui.ui.screens.search

import com.jemy.thamanya.utils.UiState

sealed interface SearchIntent {
    data class Load(
        val catId: String,
        val subCatId: String,
        val key: String
    ) : SearchIntent

    data object Retry : SearchIntent

    // optional for pagination
    data object LoadNextPage : SearchIntent
}

data class SearchState(
    val catId: String = "",
    val subCatId: String = "",
    val key: String = "",

    val page: Int = 1,
    val canLoadMore: Boolean = true,

    val results: UiState<List<Corporate>> = UiState.Idle
)