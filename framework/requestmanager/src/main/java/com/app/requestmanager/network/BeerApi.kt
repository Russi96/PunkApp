package com.app.requestmanager.network

import com.app.domain.Beer
import com.app.domain.BeerItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap


interface BeerApi {

    @GET("beers")
    suspend fun getBeerPagination(
        @Query("page") page: Int,
        @Query("per_page") per_page: Int
    ): Response<List<BeerItem>>

    @GET("beers")
    suspend fun getBeer(@Query("per_page") per_page: Int = 80): Response<Beer>

}