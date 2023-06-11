package com.jmoreno.superpoderespractica.data.remote

import com.jmoreno.superpoderespractica.data.remote.request.GetHerosRequest
import com.jmoreno.superpoderespractica.model.Hero
import okhttp3.Credentials
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultRemoteDataSource @Inject constructor(private val api: DragonBallApi) : RemoteDataSource {

    private lateinit var token: String
    override suspend fun login(user: String, password: String): String {
        val token = api.login(Credentials.basic(user, password))
        this.token = token

        return token
    }

    override suspend fun getHeros(): List<Hero> {
        return api.getHeros("Bearer $token", GetHerosRequest())
    }
}
