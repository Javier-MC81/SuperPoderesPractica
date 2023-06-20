package com.jmoreno.superpoderespractica.data.local

import com.jmoreno.superpoderespractica.data.local.model.LocalHero
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultLocalDataSource @Inject constructor(private val dao: SuperheroDAO): LocalDataSource {
    override suspend fun getHeros(): List<LocalHero> {
        return dao.getAll()
    }
    override suspend fun getHerosFlow(): Flow<List<LocalHero>> {
        return dao.getAllFlow()
    }
    /*override suspend fun insertHero(localSuperhero: LocalSuperhero){
        dao.insertAllList(listOf(localSuperhero))
    }
    */
    override suspend fun insertHeros(localSuperheros: List<LocalHero>){
        dao.insertAllList(localSuperheros)
    }
    override suspend fun insertHero(localSuperhero: LocalHero){
        dao.insertHero(localSuperhero)
    }
    override suspend fun getHero(id: Long): LocalHero {
        return dao.getHero(id)
    }
    override suspend fun getHeroFlow(id: Long):Flow<LocalHero> {
        return dao.getHeroFlow(id)
    }
}