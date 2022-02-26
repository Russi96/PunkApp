package com.app.requestmanager

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.app.domain.BeerItem
import com.app.requestmanager.network.BeerApi
import com.app.requestmanager.utils.Constants.NETWORK_PAGE_SIZE
import com.app.requestmanager.utils.Constants.TMDB_STARTING_PAGE_INDEX
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject



class BeerPagingSource @Inject constructor(
    private val service: BeerApi
) : PagingSource<Int, BeerItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BeerItem> {
        val pageIndex = params.key ?: TMDB_STARTING_PAGE_INDEX
        return try {
            val response = service.getBeerPagination(
                page = pageIndex,
                per_page = NETWORK_PAGE_SIZE
            )
            val beer = response.body()!!
            val nextKey =
                if (beer.isEmpty()) {
                    null
                } else {
                    // By default, initial load size = 3 * NETWORK PAGE SIZE
                    // ensure we're not requesting duplicating items at the 2nd request
                    pageIndex + (params.loadSize / NETWORK_PAGE_SIZE)
                }
            LoadResult.Page(
                data = beer,
                prevKey = if (pageIndex == TMDB_STARTING_PAGE_INDEX) null else pageIndex,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    /**
     * The refresh key is used for subsequent calls to PagingSource.Load after the initial load.
     */
    override fun getRefreshKey(state: PagingState<Int, BeerItem>): Int? {
        // We need to get the previous key (or next key if previous is null) of the page
        // that was closest to the most recently accessed index.
        // Anchor position is the most recently accessed index.
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}