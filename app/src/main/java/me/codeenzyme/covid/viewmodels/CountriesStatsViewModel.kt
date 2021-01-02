package me.codeenzyme.covid.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import me.codeenzyme.covid.repository.CountriesStatsPagingSource

class CountriesStatsViewModel @ViewModelInject constructor(
    private val countriesStatsPagingSource: CountriesStatsPagingSource
) : ViewModel() {

    val pagedData = Pager(
        PagingConfig(
            pageSize = 20
        )
    ) {
        countriesStatsPagingSource
    }.flow.cachedIn(viewModelScope)

}