package com.app.punkapp.di

import com.app.data.LocalDataSource
import com.app.data.RemoteDataSource
import com.app.data.Repository
import com.app.data.database.BeerDatabase
import com.app.requestmanager.BeerDataSource
import com.app.requestmanager.network.BeerApi
import com.app.usescases.BeerUsesCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BeerModule {

    @Singleton
    @Provides
    fun repository(
        beerDataSource: BeerDataSource,
        localDataSource: LocalDataSource
    ) = Repository(
        remoteDataSource = beerDataSource,
        localDataSource = localDataSource
    )

    @Singleton
    @Provides
    fun beerDataSource(beerApi: BeerApi) =
        BeerDataSource(beerApi)




    @Singleton
    @Provides
    fun beerUsesCases(repository: Repository) =
        BeerUsesCases(repository = repository)
}