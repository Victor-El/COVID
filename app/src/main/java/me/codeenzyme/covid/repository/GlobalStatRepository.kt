package me.codeenzyme.covid.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import me.codeenzyme.covid.data.AppDatabase
import me.codeenzyme.covid.data.local.entities.GlobalStat
import me.codeenzyme.covid.data.remote.CoronaService
import javax.inject.Inject

class GlobalStatRepository @Inject constructor(
    val db: AppDatabase,
    private val coronaService: CoronaService,
) {

    suspend fun fetchCoronaGlobalStat(): Flow<GlobalStat> {
        val stat = coronaService.getGlobalStat()
        return withContext(Dispatchers.IO) {
            db.globalStatDao().insertGlobalStat(GlobalStat(stat))
            return@withContext db.globalStatDao().getGlobalStats()
        }
    }

}