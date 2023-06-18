package com.jmoreno.superpoderespractica.data.local

import com.jmoreno.superpoderespractica.data.local.model.LocalHero
import javax.inject.Inject

class DefaultLocalDataSource @Inject constructor(private val dao: SuperheroDAO): LocalDataSource {

    override suspend fun getHeros(): List<LocalHero>{
        return dao.getAll()
    }

    /*override suspend fun insertHero(localSuperhero: LocalSuperhero){
        dao.insertAllList(listOf(localSuperhero))
    }
    */
    override suspend fun insertHeros(localSuperheros: List<LocalHero>){
        dao.insertAllList(localSuperheros)
    }

}