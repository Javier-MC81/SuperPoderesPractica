package com.jmoreno.superpoderespractica.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
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

class SuperHeroListViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    // UUT o SUT
    private lateinit var viewModel: SuperHeroListViewModel

    // Mocks
    private lateinit var repository: Repository


    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        repository = mockk()
        viewModel = SuperHeroListViewModel(repository)
    }

    @Test
    fun `WHEN findHero EXPECT The same hero`() = runTest  {
        // GIVEN
        val heroCreated = generateRandomHero(1)
        coEvery { repository.getHero(heroCreated.id) } returns generateLocalCloneHero(heroCreated)

        // WHEN
        viewModel.findHero(heroCreated.id)
        val actualLiveData = viewModel.hero.getOrAwaitValue()

        // THEN
        assert(!actualLiveData.favorite && actualLiveData.name == "Name 1")
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

}
/*
Se trata de un test real del método findHero del viewModel. Para hacerlo he mockeado el repository y simulado
su la devolución de el mismo héroe que enviamos a la base de datos. Como este método se establece en el contexto de corrutinas
he utilizado el coevery. Al comprobar la llegada en el LiveData del héroe en el viewModel, he utilizado la función
getOrAwaitValue() necesaria para observar el cambio que experimenta esta variable a la vuelta del héroe de la base de datos.
Una vez actualizado el valor, comprobamos que tiene los mismos datos del héroe que generamos para hacer la búsqueda en BBDD.
*/
