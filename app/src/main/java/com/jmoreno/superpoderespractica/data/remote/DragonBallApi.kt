package com.jmoreno.superpoderespractica.data.remote

import com.jmoreno.superpoderespractica.data.remote.request.GetHerosRequest
import com.jmoreno.superpoderespractica.model.Hero
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface DragonBallApi {
    @POST("api/auth/login")
    suspend fun login(@Header("Authorization") token: String): String

    @POST("api/heros/all")
    suspend fun getHeros(@Header("Authorization") token: String, @Body getHerosRequest: GetHerosRequest): List<Hero>
}
