package com.app.data

import androidx.lifecycle.MutableLiveData
import com.app.data.utils.NetworkResult
import com.app.domain.Beer

interface RemoteDataSource {

   suspend fun getBeers() : MutableLiveData<NetworkResult<Beer>>

}