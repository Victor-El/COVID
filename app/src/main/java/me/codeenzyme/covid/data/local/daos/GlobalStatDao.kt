package me.codeenzyme.covid.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import me.codeenzyme.covid.data.local.entities.GlobalStat

@Dao
interface GlobalStatDao {

    @Query("SELECT * FROM GlobalStat WHERE uid = 1")
    fun getGlobalStats(): Flow<GlobalStat>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGlobalStat(stat: GlobalStat)

}