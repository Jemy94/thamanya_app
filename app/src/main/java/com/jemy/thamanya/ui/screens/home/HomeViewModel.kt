package com.jemy.thamanya.ui.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jemy.thamanya.data.repo.HomeRepository
import com.jemy.thamanya.ui.ui.screens.home.mvicontract.HomeIntent
import com.jemy.thamanya.ui.ui.screens.home.mvicontract.HomeState
import com.jemy.thamanya.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val appRepository: HomeRepository) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    init {
        onIntent(HomeIntent.Load)
    }

    fun onIntent(intent: HomeIntent) {
        when (intent) {
            HomeIntent.Load,
            HomeIntent.Retry -> loadHome()
        }
    }

    private fun loadHome() {
        viewModelScope.launch {
            // set both to Loading
            _state.update {
                it.copy(
                    categories = UiState.Loading,
                    banners = UiState.Loading
                )
            }

            // run both calls in parallel
            supervisorScope {
                launch { loadCategories() }
                launch { loadBanners() }
            }
        }
    }

    private suspend fun loadCategories() {
        try {
            val data = appRepository.getCategories()
            _state.update { it.copy(categories = UiState.Success(data)) }
        } catch (e: Exception) {
            _state.update {
                it.copy(categories = UiState.Error(e.message ?: "Failed to load categories", e))
            }
        }
    }

    private suspend fun loadBanners() {
        try {
            val data = appRepository.getBanner()
            _state.update { it.copy(banners = UiState.Success(data)) }
        } catch (e: Exception) {
            _state.update {
                it.copy(banners = UiState.Error(e.message ?: "Failed to load banners", e))
            }
        }
    }

}