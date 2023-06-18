package com.jmoreno.superpoderespractica.data.local

import com.jmoreno.superpoderespractica.data.local.model.LocalHero
import com.jmoreno.superpoderespractica.model.Heroe

interface LocalDataSource {
    suspend fun getHeros(): List<LocalHero>

    //suspend fun insertHero(localSuperhero: LocalHero)

    suspend fun insertHeros(localSuperheros: List<LocalHero>)
}