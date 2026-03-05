package com.jemy.thamanya.data.remote

import com.jemy.thamanya.data.models.SearchResult
import retrofit2.http.GET

interface ApiServiceForSearch {
    @GET(EndPoints.SEARCH)
    suspend fun getSearchResult(): SearchResult

}