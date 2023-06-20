package com.jmoreno.superpoderespractica.data.remote

import com.jmoreno.superpoderespractica.data.local.LocalDataSource
import com.jmoreno.superpoderespractica.data.local.mappers.RemoteToLocalMapper
import com.jmoreno.superpoderespractica.data.local.model.LocalHero
import com.jmoreno.superpoderespractica.model.Empty
import com.jmoreno.superpoderespractica.model.ResultComics
import com.jmoreno.superpoderespractica.model.Welcome
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.count
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultRepository @Inject constructor
    (private val localDataSource: LocalDataSource,
     private val remoteDataSource: RemoteDataSource,
     //private val localToPresentationMapper: LocalToPresentationMapper,
     private val remoteToLocalMapper: RemoteToLocalMapper
)
    : Repository {

    override suspend fun getWelcome(): List<LocalHero> {
        if (localDataSource.getHeros().isEmpty()) {
            val remoteWelcome = remoteDataSource.getWelcome()
            localDataSource.insertHeros(remoteToLocalMapper.mapGetHeroResponse(remoteWelcome.data.results))
        }

        return localDataSource.getHeros()

    }
    override suspend fun getSeries(id: Long): Empty {
        return remoteDataSource.getSeries(id)
    }
    override suspend fun getComics(id: Long): ResultComics {
        return remoteDataSource.getComics(id)
    }
    override suspend fun updateHero(heroe: LocalHero){
        localDataSource.insertHero(heroe)
    }
    override suspend fun getHero(id: Long): LocalHero{
        return localDataSource.getHero(id)
    }
    override suspend fun getHeroFlow(id: Long): Flow<LocalHero>{
        return localDataSource.getHeroFlow(id)
    }
    override suspend fun getHeroesFlow(): Flow<List<LocalHero>>{
        return localDataSource.getHerosFlow()
    }
}
