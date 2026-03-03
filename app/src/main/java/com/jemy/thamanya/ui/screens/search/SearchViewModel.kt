package com.jemy.thamanya.ui.ui.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jemy.thamanya.data.repo.HomeRepository
import com.jemy.thamanya.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(private val appRepository: HomeRepository) : ViewModel() {

    private val _state = MutableStateFlow(SearchState())
    val state: StateFlow<SearchState> = _state.asStateFlow()

    fun onIntent(intent: SearchIntent) {
        when (intent) {
            is SearchIntent.Load -> loadFirstPage(intent.catId, intent.subCatId, intent.key)
            SearchIntent.Retry -> retry()
            SearchIntent.LoadNextPage -> loadNextPage()
        }
    }

    private fun loadFirstPage(catId: String, subCatId: String, key: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    catId = catId,
                    subCatId = subCatId,
                    key = key,
                    page = 1,
                    canLoadMore = true,
                    results = UiState.Loading
                )
            }

            try {
                val response = appRepository.getSearchResults(
                    catId = catId,
                    suCatId = subCatId,
                    key = key,
                    page = 1
                )
                val list = response.data ?: emptyList()

                _state.update {
                    it.copy(
                        results = UiState.Success(list),
                        // If backend returns empty list => no more pages
                        canLoadMore = list.isNotEmpty()
                    )
                }
            } catch (t: Exception) {
                _state.update {
                    it.copy(results = UiState.Error(t.message ?: "Failed to load results", t))
                }
            }
        }
    }

    private fun retry() {
        val s = _state.value
        loadFirstPage(s.catId, s.subCatId, s.key)
    }

    // Optional paging: append results
    private fun loadNextPage() {
        val s = _state.value
        val current = (s.results as? UiState.Success)?.data ?: return
        if (!s.canLoadMore) return

        viewModelScope.launch {
            val nextPage = s.page + 1

            try {
                val response = appRepository.getSearchResults(
                    catId = s.catId,
                    suCatId = s.subCatId,
                    key = s.key,
                    page = nextPage
                )

                val newItems = response.data ?: emptyList()
                val merged = current + newItems

                _state.update {
                    it.copy(
                        page = nextPage,
                        results = UiState.Success(merged),
                        canLoadMore = newItems.isNotEmpty()
                    )
                }
            } catch (t: Throwable) {
                // keep old list but you can show a toast/snackbar with Effect if you want
                _state.update {
                    it.copy(results = UiState.Error(t.message ?: "Failed to load more", t))
                }
            }
        }
    }

}