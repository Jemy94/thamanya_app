package com.jemy.thamanya.ui.ui.screens.home.mvicontract

sealed interface HomeIntent {
    data object Load : HomeIntent
    data object Retry : HomeIntent
}