package com.app.data

import androidx.paging.PagingSource
import com.app.data.database.BeerDao
import com.app.data.database.entities.BeerEntity
import com.app.domain.BeerItem
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val beerDao: BeerDao
) {

    fun searchBeer(searchQuery: String): PagingSource<Int, BeerItem> {
        return beerDao.searchBeerDatabase(searchQuery)
    }

    suspend fun insertBeer(beerEntity: BeerEntity) {
        beerDao.insertBeerDao(beerEntity)
    }
}