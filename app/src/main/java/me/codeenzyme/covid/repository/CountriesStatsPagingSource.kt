package me.codeenzyme.covid.repository

import androidx.paging.PagingSource
import me.codeenzyme.covid.data.remote.CoronaService
import me.codeenzyme.covid.models.CountriesCasesResponseItem
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

class CountriesStatsPagingSource @Inject constructor(
    private val coronaService: CoronaService
): PagingSource<Int, CountriesCasesResponseItem>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CountriesCasesResponseItem> {
        try {
            val nextPageNumber = params.key ?: 1
            val countries = coronaService.getCountriesStat()

            val nextKey = nextPageNumber + 1
            val prevKey = if (nextPageNumber == 1) null else nextPageNumber - 1
            return LoadResult.Page(
                countries,
                nextKey = null, // don't load next page
                prevKey = prevKey
            )
        } catch (e: Exception) {
            Timber.d(e.localizedMessage)
            return LoadResult.Error(e)
        }
    }


}