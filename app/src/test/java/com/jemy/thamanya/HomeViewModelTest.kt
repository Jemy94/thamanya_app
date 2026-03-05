package com.jemy.thamanya

import app.cash.turbine.test
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {

    // 1. Swap the Main dispatcher for tests
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    // 2. Mock the repository (so we don't hit the real API)
    private val repository = mockk<AppRepository>()
    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
        // 3. Initialize ViewModel with the mock
        viewModel = HomeViewModel(repository)
    }

    @Test
    fun `when loading data, state should update to success`() = runTest {
        // Given: Define what the mock should return
        val mockData = listOf("Story 1", "Story 2")
        coEvery { repository.getHomeData() } returns mockData

        // When & Then: Use Turbine to observe the StateFlow
        viewModel.uiState.test {
            // Check initial state
            assertEquals(HomeUiState.Loading, awaitItem())

            // Trigger the action
            viewModel.loadData()

            // Check if success state is emitted with our mock data
            val finalState = awaitItem()
            assert(finalState is HomeUiState.Success)
            assertEquals(mockData, (finalState as HomeUiState.Success).data)
        }
    }
}