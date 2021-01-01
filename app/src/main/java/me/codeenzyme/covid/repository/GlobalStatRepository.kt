package me.codeenzyme.covid.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import me.codeenzyme.covid.data.AppDatabase
import me.codeenzyme.covid.data.local.entities.GlobalStat
import me.codeenzyme.covid.data.remote.CoronaService
import timber.log.Timber
import javax.inject.Inject

class GlobalStatRepository @Inject constructor(
    private val db: AppDatabase,
    private val coronaService: CoronaService,
) {

    suspend fun fetchCoronaGlobalStat(): Flow<GlobalStat> {
        val stat = try {
            coronaService.getGlobalStat()
        } catch (e: Exception) {
            Timber.d(e.localizedMessage!!)
            null
        }

        return withContext(Dispatchers.IO) {
            if (stat != null) {
                db.globalStatDao().insertGlobalStat(GlobalStat(stat))
            }
            return@withContext db.globalStatDao().getGlobalStats()
        }
    }

}