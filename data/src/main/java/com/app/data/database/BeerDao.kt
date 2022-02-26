package com.app.data.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.data.database.entities.BeerEntity
import com.app.domain.BeerItem

@Dao
interface BeerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBeerDao(beerEntity: BeerEntity)

    @Query("SELECT * FROM beer_table WHERE name LIKE :searchQuery")
    fun searchBeerDatabase(searchQuery: String): PagingSource<Int, BeerItem>
}