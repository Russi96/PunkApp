package com.app.requestmanager

import androidx.lifecycle.MutableLiveData
import com.app.data.RemoteDataSource
import com.app.data.utils.NetworkResult
import com.app.domain.Beer
import com.app.requestmanager.network.BeerApi
import javax.inject.Inject

class BeerDataSource @Inject constructor(
    private val service: BeerApi
) : RemoteDataSource {
    private var beersListResponse: MutableLiveData<NetworkResult<Beer>> = MutableLiveData()
    override suspend fun getBeers(): MutableLiveData<NetworkResult<Beer>> {
        val response =
            service.getBeer()

        when {
            response.message().toString().contains("timeout") -> {
                beersListResponse.value = NetworkResult.Error(message = "Timeout")
            }
            response.code() == 403 -> {
                beersListResponse.value = NetworkResult.Error(message = "API Key Limited.")
            }

            response.isSuccessful -> {
                val beers = response.body()
                beers?.let { beersList ->
                    beersListResponse.value = NetworkResult.Success(data = beersList)

                }
            }
            else -> {
                beersListResponse.value = NetworkResult.Error(message = response.message())
            }
        }

        return beersListResponse
    }
}