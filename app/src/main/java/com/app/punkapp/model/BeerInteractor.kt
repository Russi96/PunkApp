package com.app.punkapp.model

import com.app.usescases.BeerUsesCases

class BeerInteractor(
    private val beerUsesCases: BeerUsesCases
) {
    fun beerDatabaseRoom() = beerUsesCases.invoke()

    suspend fun getBeers() = beerUsesCases.getBeers()
}