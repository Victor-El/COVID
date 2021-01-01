package me.codeenzyme.covid.data

import androidx.room.Database
import androidx.room.RoomDatabase
import me.codeenzyme.covid.data.local.daos.GlobalStatDao
import me.codeenzyme.covid.data.local.entities.GlobalStat

@Database(entities = [GlobalStat::class], version = 2)
abstract class AppDatabase: RoomDatabase() {

    abstract fun globalStatDao(): GlobalStatDao

}