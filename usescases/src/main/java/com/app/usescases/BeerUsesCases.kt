package com.app.usescases

import com.app.data.Repository

class BeerUsesCases(
    private val repository: Repository
) {
    fun invoke() = repository.remote

    suspend fun getBeers() = repository.getBeers()
}