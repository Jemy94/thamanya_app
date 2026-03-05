package com.jemy.thamanya.ui.screens.home

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jemy.thamanya.data.models.Section
import com.jemy.thamanya.data.repo.HomeRepository

class HomePagingSource(
    private val repository: HomeRepository
) : PagingSource<Int, Section>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Section> {
        return try {
            val page = params.key ?: 1

            val response = repository.getHomeData(page)

            val sections = response.sections ?: emptyList()

            LoadResult.Page(
                data = sections,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (sections.isEmpty()) null else page + 1
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Section>): Int? {
        return state.anchorPosition?.let { anchor ->
            val page = state.closestPageToPosition(anchor)
            page?.prevKey?.plus(1) ?: page?.nextKey?.minus(1)
        }
    }
}