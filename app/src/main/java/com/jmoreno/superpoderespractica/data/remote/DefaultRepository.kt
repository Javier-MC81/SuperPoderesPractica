package com.jmoreno.superpoderespractica.data.remote

import com.jmoreno.superpoderespractica.model.Hero
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    Repository {
    override suspend fun login(user: String, password: String): String {
        return remoteDataSource.login(user, password)
    }

    override suspend fun getHeros(): List<Hero> {
        return remoteDataSource.getHeros()
    }
}
