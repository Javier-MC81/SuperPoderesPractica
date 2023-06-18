package com.jmoreno.superpoderespractica.di


import com.jmoreno.superpoderespractica.data.local.DefaultLocalDataSource
import com.jmoreno.superpoderespractica.data.local.LocalDataSource
import com.jmoreno.superpoderespractica.data.remote.Repository
import com.jmoreno.superpoderespractica.data.remote.DefaultRemoteDataSource
import com.jmoreno.superpoderespractica.data.remote.DefaultRepository
import com.jmoreno.superpoderespractica.data.remote.RemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepository(defaultRepository: DefaultRepository): Repository

    @Binds
    abstract fun bindRemoteDataSource(defaultRemoteDataSource: DefaultRemoteDataSource): RemoteDataSource

    @Binds
    abstract fun bindsLocalDataSource(defaultLocalDataSource: DefaultLocalDataSource): LocalDataSource
}
