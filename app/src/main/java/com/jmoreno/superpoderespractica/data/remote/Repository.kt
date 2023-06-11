package com.jmoreno.superpoderespractica.data.remote

import com.jmoreno.superpoderespractica.model.Hero

interface Repository {
    suspend fun login(user: String, password: String): String
    suspend fun getHeros(): List<Hero>
}
