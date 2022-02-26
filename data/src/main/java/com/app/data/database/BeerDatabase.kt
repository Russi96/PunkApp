package com.app.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.app.data.database.entities.BeerEntity

@Database(entities = [BeerEntity::class], version = 1, exportSchema = false)
@TypeConverters(BeerTypeConvert::class)
abstract class BeerDatabase: RoomDatabase() {
    abstract fun beerDao() : BeerDao
}