package com.jemy.thamanya.ui.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jemy.thamanya.data.models.Section
import com.jemy.thamanya.data.repo.SearchRepository
import com.jemy.thamanya.utils.Resource
import com.jemy.thamanya.utils.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: SearchRepository
) : ViewModel() {

    private val _state =
        MutableStateFlow(Resource<List<Section>>(ResourceState.SUCCESS, data = emptyList()))
    val state: StateFlow<Resource<List<Section>>> = _state.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private var searchJob: Job? = null

    fun onSearchQueryChange(newQuery: String) {
        _searchQuery.value = newQuery

        searchJob?.cancel()

        searchJob = viewModelScope.launch {
            delay(200) // debounce

            if (newQuery.isBlank()) {
                _state.value = Resource(ResourceState.SUCCESS, data = emptyList())
                return@launch
            }

            loadSearchData(newQuery)
        }
    }

    private suspend fun loadSearchData(query: String) {
        try {
            _state.value = Resource(ResourceState.LOADING)

            val result = repository.getSearchResult()

            val sections = result.sections

            if (!sections.isNullOrEmpty()) {
                _state.value = Resource(ResourceState.SUCCESS, data = sections)
            } else {
                _state.value = Resource(ResourceState.ERROR, message = "No Data Found")
            }

        } catch (e: Exception) {
            _state.value = Resource(ResourceState.ERROR, message = e.message ?: "Unknown error")
        }
    }
}