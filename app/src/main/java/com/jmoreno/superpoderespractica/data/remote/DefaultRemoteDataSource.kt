package com.jmoreno.superpoderespractica.data.remote

import com.jmoreno.superpoderespractica.model.Empty
import com.jmoreno.superpoderespractica.model.ResultComics
import com.jmoreno.superpoderespractica.model.Welcome
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultRemoteDataSource @Inject constructor(private val api: MarvelApi) : RemoteDataSource {


    override suspend fun getWelcome(): Welcome {
        return api.getWelcome()
    }
    override suspend fun getSeries(id: Long): Empty {
        return api.getSeries(id)
    }
    override suspend fun getComics(id: Long): ResultComics {
        return api.getComics(id)
    }

}
