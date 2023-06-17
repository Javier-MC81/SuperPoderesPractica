package com.jmoreno.superpoderespractica.data.remote

import com.jmoreno.superpoderespractica.data.remote.request.GetHerosRequest
import com.jmoreno.superpoderespractica.model.Empty
import com.jmoreno.superpoderespractica.model.Hero
import com.jmoreno.superpoderespractica.model.Welcome
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface DragonBallApi {

    @GET("v1/public/characters?ts=1&apikey=4a0923acfe91399aeb6f46ed9191c341&hash=436e126ec9c88a3886912706aa9e885d&orderBy=-modified")
    suspend fun getWelcome(): Welcome

    @GET("v1/public/characters/{heroId}/series?ts=1&apikey=4a0923acfe91399aeb6f46ed9191c341&hash=436e126ec9c88a3886912706aa9e885d&orderBy=-modified")
    suspend fun getSeries(@Path("heroId") heroId: Long): Empty

    /*@POST("api/auth/login")
    suspend fun login(@Header("Authorization") token: String): String

    @POST("api/heros/all")
    suspend fun getHeros(@Header("Authorization") token: String, @Body getHerosRequest: GetHerosRequest): List<Hero>*/
}
