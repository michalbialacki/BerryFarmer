package com.kkp.berryfarmer.data.network

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kkp.berryfarmer.domain.model.Berry

@Database(
    entities = [Berry::class],
    version = 1,
    exportSchema = false)
abstract class BerryDb : RoomDatabase() {
    abstract fun berryDao() : BerryDao
}