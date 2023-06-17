package com.jmoreno.superpoderespractica.data.remote

import com.jmoreno.superpoderespractica.model.Empty
import com.jmoreno.superpoderespractica.model.Hero
import com.jmoreno.superpoderespractica.model.Welcome


interface RemoteDataSource {
    suspend fun getWelcome(): Welcome
    suspend fun getSeries(id: Long): Empty
}
