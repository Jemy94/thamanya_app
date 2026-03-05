package com.jemy.thamanya.data.repo

import com.jemy.thamanya.data.models.HomeResponse
import com.jemy.thamanya.data.remote.ApiService
import javax.inject.Inject

class HomeRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getHomeData(page: Int = 1): HomeResponse = apiService.getHomeDate(page)
}