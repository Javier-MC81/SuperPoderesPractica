package com.jmoreno.superpoderespractica.data.remote

import com.jmoreno.superpoderespractica.data.remote.request.GetHerosRequest
import com.jmoreno.superpoderespractica.model.Empty
import com.jmoreno.superpoderespractica.model.Hero
import com.jmoreno.superpoderespractica.model.Welcome
import okhttp3.Credentials
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultRemoteDataSource @Inject constructor(private val api: DragonBallApi) : RemoteDataSource {


    override suspend fun getWelcome(): Welcome {
        return api.getWelcome()
    }
    override suspend fun getSeries(id: Long): Empty {
        return api.getSeries(id)
    }

}
