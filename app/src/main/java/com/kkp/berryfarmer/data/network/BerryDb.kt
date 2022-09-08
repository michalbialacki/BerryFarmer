package com.kkp.berryfarmer.data.network

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kkp.berryfarmer.domain.model.Berry

@Database(
    entities = [Berry::class],
    version = 2,
    exportSchema = false,
    )
@TypeConverters(Converters::class)
abstract class BerryDb : RoomDatabase() {
    abstract fun berryDao() : BerryDao
}