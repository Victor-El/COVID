package me.codeenzyme.covid.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import me.codeenzyme.covid.repository.GlobalStatRepository

class GlobalStatViewModel @ViewModelInject constructor(
    private val globalStatRepository: GlobalStatRepository
): ViewModel() {

    suspend fun getGlobalStat() = globalStatRepository.fetchCoronaGlobalStat()

}