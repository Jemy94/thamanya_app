package com.jemy.thamanya.data.remote

import com.jemy.thamanya.data.models.HomeResponse
import com.jemy.thamanya.data.models.SearchResult
import retrofit2.http.GET

interface ApiService {
    @GET(EndPoints.HOME)
    suspend fun getHomeDate(): HomeResponse

}