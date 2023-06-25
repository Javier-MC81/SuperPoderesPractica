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
        assert(actualLiveData.size == 20 && actualLiveData[0].title == "Name ${actualLiveData.size} series of ${heroCreated.id} superhero")

    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

}
/*
Test real del viewModel en el que probamos la función getSeries.  Para esto,he hecho un fake del repository
generando el objeto Empty que recibimos de la API de Marvel.
Actualizamos el LiveData de las series en el viewModel y por tanto, utilizamos el getOrAwaitValue(). Una vez actualizado, comprobamos que
las series se han generado a partir del assert. He incluido el nombre del héroe creado en el assert para comprobar que el repository ha enviado
las series del mismo héroe que he generado.
 */
