package com.jemy.thamanya.data.repo

import com.jemy.thamanya.data.models.SearchResult
import com.jemy.thamanya.data.remote.ApiServiceForSearch
import javax.inject.Inject

class SearchRepository @Inject constructor(private val apiService: ApiServiceForSearch) {

    suspend fun getSearchResult(): SearchResult = apiService.getSearchResult()


}