package com.jmoreno.superpoderespractica.data

import com.jmoreno.superpoderespractica.data.local.model.LocalHero
import com.jmoreno.superpoderespractica.data.remote.Repository
import com.jmoreno.superpoderespractica.model.Empty
import com.jmoreno.superpoderespractica.model.ResultComics
import com.jmoreno.superpoderespractica.utils.generateEmptyClass
import com.jmoreno.superpoderespractica.utils.generateSeriesResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FakeRepository @Inject constructor(): Repository {
    override suspend fun getWelcome(): List<LocalHero> {
        TODO("Not yet implemented")
    }

    override suspend fun getHeroesFlow(): Flow<List<LocalHero>> {
        TODO("Not yet implemented")
    }

    override suspend fun getSeries(id: Long): Empty {
        return generateEmptyClass(id)
    }

    override suspend fun getComics(id: Long): ResultComics {
        TODO("Not yet implemented")
    }

    override suspend fun updateHero(heroe: LocalHero) {
        TODO("Not yet implemented")
    }

    override suspend fun getHeroFlow(id: Long): Flow<LocalHero> {
        TODO("Not yet implemented")
    }

    override suspend fun getHero(id: Long): LocalHero {
        TODO("Not yet implemented")
    }
}