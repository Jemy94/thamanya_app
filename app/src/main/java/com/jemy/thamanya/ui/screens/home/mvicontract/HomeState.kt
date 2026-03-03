package com.jemy.thamanya.ui.ui.screens.home.mvicontract

import com.jemy.thamanya.utils.UiState

data class HomeState(
    val categories: UiState<CategoriesResponse> = UiState.Idle,
    val banners: UiState<BannersResponse> = UiState.Idle
)
