package com.jmoreno.superpoderespractica.data.remote

import com.jmoreno.superpoderespractica.model.Empty
import com.jmoreno.superpoderespractica.model.ResultComics
import com.jmoreno.superpoderespractica.model.Welcome
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query



interface MarvelApi {

    @GET("v1/public/characters")
    suspend fun getWelcome(@Query("ts") ts:String,@Query("apikey") apikey:String,@Query("hash") hash:String,@Query("orderBy") orderBy: String): Welcome
    @GET("v1/public/characters/{heroId}/series")
    suspend fun getSeries(@Path("heroId") heroId: Long,@Query("ts") ts:String,@Query("apikey") apikey:String,@Query("hash") hash:String,@Query("orderBy") orderBy: String): Empty

    @GET("v1/public/characters/{heroId}/comics")
    suspend fun getComics(@Path("heroId") heroId: Long,@Query("ts") ts:String,@Query("apikey") apikey:String,@Query("hash") hash:String,@Query("orderBy") orderBy: String): ResultComics


}
