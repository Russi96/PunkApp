package com.app.data

import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    localDataSource: LocalDataSource
) {
    val remote = localDataSource

   suspend fun getBeers() = remoteDataSource.getBeers()
}