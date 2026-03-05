package com.jemy.thamanya.ui.ui.screens.search

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
class SearchViewModel @Inject constructor(private val repository: SearchRepository) : ViewModel() {

    private val _state = MutableStateFlow(Resource<List<Section>>(ResourceState.LOADING))
    val state: StateFlow<Resource<List<Section>>> = _state.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private var searchJob: Job? = null

    fun onSearchQueryChange(newQuery: String) {
        _searchQuery.value = newQuery
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(200)
            if (newQuery.isNotEmpty()) {
                loadSearchData()
            } else {
                _state.value = Resource(ResourceState.SUCCESS, data = emptyList())
            }
        }
    }

    private fun loadSearchData() {
        viewModelScope.launch {
            try {
                _state.value = Resource(ResourceState.LOADING)
                val data = repository.getSearchResult().sections
                if (data != null && data.isNotEmpty()) {
                    _state.value = Resource(ResourceState.SUCCESS, data = data)
                } else {
                    _state.value = Resource(ResourceState.ERROR, message = "No Data Found")
                }
            } catch (e: Exception) {
                _state.value = Resource(ResourceState.ERROR, message = e.message ?: "Unknown error")
            }
        }
    }
}