package com.jmoreno.superpoderespractica.data.local

import com.jmoreno.superpoderespractica.data.local.model.LocalHero
import com.jmoreno.superpoderespractica.model.Heroe
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun getHeros(): List<LocalHero>
    suspend fun getHerosFlow(): Flow<List<LocalHero>>
    suspend fun insertHero(localSuperhero: LocalHero)
    suspend fun insertHeros(localSuperheros: List<LocalHero>)
    suspend fun getHero(id: Long): LocalHero
    suspend fun getHeroFlow(id: Long): Flow<LocalHero>
}