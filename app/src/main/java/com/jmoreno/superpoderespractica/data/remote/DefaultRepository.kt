package com.jmoreno.superpoderespractica.data.remote

import com.jmoreno.superpoderespractica.model.Empty
import com.jmoreno.superpoderespractica.model.Hero
import com.jmoreno.superpoderespractica.model.Welcome
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) : Repository {

    override suspend fun getWelcome(): Welcome {
        return remoteDataSource.getWelcome()
    }
    override suspend fun getSeries(id: Long): Empty {
        return remoteDataSource.getSeries(id)
    }

}
