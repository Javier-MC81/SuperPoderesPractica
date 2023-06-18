package com.jmoreno.superpoderespractica.data.remote

import com.jmoreno.superpoderespractica.data.local.model.LocalHero
import com.jmoreno.superpoderespractica.model.Empty
import com.jmoreno.superpoderespractica.model.ResultComics
import com.jmoreno.superpoderespractica.model.Welcome

interface Repository {
    suspend fun getWelcome(): List<LocalHero>
    suspend fun getSeries(id: Long): Empty
    suspend fun getComics(id: Long): ResultComics
}
