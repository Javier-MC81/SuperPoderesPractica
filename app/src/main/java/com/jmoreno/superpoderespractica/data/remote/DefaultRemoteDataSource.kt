package com.jmoreno.superpoderespractica.data.remote

import com.jmoreno.superpoderespractica.BuildConfig
import com.jmoreno.superpoderespractica.model.Empty
import com.jmoreno.superpoderespractica.model.ResultComics
import com.jmoreno.superpoderespractica.model.Welcome
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultRemoteDataSource @Inject constructor(private val api: MarvelApi) : RemoteDataSource {
    private val apikey = BuildConfig.API_KEY
    private val hash = BuildConfig.HASH
    private val ts = BuildConfig.TS
    private val orderBy = BuildConfig.ORDERBY


    override suspend fun getWelcome(): Welcome {
        return api.getWelcome(ts,apikey,hash,orderBy)
    }
    override suspend fun getSeries(id: Long): Empty {
        return api.getSeries(id,ts,apikey,hash,orderBy)
    }
    override suspend fun getComics(id: Long): ResultComics {
        return api.getComics(id,ts,apikey,hash,orderBy)
    }

}
