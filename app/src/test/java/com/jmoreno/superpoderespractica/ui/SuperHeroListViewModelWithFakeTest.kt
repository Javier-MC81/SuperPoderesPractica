package com.jmoreno.superpoderespractica.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.jmoreno.superpoderespractica.data.FakeRepository
import com.jmoreno.superpoderespractica.data.remote.Repository
import com.jmoreno.superpoderespractica.ui.superherolist.SuperHeroListViewModel
import com.jmoreno.superpoderespractica.utils.generateLocalCloneHero

import com.jmoreno.superpoderespractica.utils.generateRandomHero
import com.jmoreno.superpoderespractica.utils.getOrAwaitValue
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SuperHeroListViewModelWithFakeTest {

   @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    // UUT o SUT
    private lateinit var viewModel: SuperHeroListViewModel

    // Mocks
    private lateinit var repository: Repository


    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        repository = FakeRepository()
        viewModel = SuperHeroListViewModel(repository)
    }

    @Test
    fun `WHEN getSeries EXPECT the number of series of the superhero with the heroID`() = runTest  {
        // GIVEN
        val heroCreated = generateRandomHero(101)

        // WHEN
        viewModel.getSeries(heroCreated.id)
        val actualLiveData = viewModel.series.getOrAwaitValue()

        // THEN
        assert(actualLiveData.size == 20)

    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

}
