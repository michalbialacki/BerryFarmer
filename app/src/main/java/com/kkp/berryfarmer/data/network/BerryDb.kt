package com.kkp.berryfarmer.data.network

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kkp.berryfarmer.domain.model.Berry
import com.kkp.berryfarmer.domain.model.Tree

@Database(
    entities = [Berry::class, Tree::class],
    version = 3,
    exportSchema = false,
    )
@TypeConverters(Converters::class)
abstract class BerryDb : RoomDatabase() {
    abstract fun berryDao() : BerryDao
    abstract fun TreeDao() : TreeDao
}