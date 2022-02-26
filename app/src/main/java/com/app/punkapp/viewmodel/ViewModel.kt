package com.app.punkapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.app.data.database.entities.BeerEntity
import com.app.domain.BeerItem
import com.app.punkapp.model.BeerInteractor
import com.app.punkapp.utils.Constants.NETWORK_PAGE_SIZE
import com.app.requestmanager.network.BeerApi
import com.app.requestmanager.BeerPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModel @Inject constructor(
    private val beerApi: BeerApi,
    private val beerInteractor: BeerInteractor
) : ViewModel() {

    /** Room */

    fun insertBeer(beerEntity: BeerEntity) = viewModelScope.launch(Dispatchers.IO) {
        beerInteractor.beerDatabaseRoom().insertBeer(beerEntity)
    }


    suspend fun getBeer() = beerInteractor.getBeers()

    fun beerPagination(): Flow<PagingData<BeerItem>> {
        val flow = Pager(
            PagingConfig(
                pageSize = NETWORK_PAGE_SIZE
            )
        ) {
            BeerPagingSource(beerApi)
        }.flow.cachedIn(viewModelScope)

        return flow
    }

    fun searchBeerData(searchQuery: String): Flow<PagingData<BeerItem>> {
        val flowSearchView = Pager(
            PagingConfig(
                pageSize = NETWORK_PAGE_SIZE
            )
        ) {
            beerInteractor.beerDatabaseRoom().searchBeer(searchQuery)
        }.flow.cachedIn(viewModelScope)

        return flowSearchView
    }
}