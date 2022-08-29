package com.kkp.berryfarmer.data.network

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import com.kkp.berryfarmer.core.Constants.BERRY_TABLE
import com.kkp.berryfarmer.domain.model.Berry
import kotlinx.coroutines.flow.Flow

@Dao
interface BerryDao {
    @Query("SELECT * FROM $BERRY_TABLE ORDER BY id ASC")
    fun getBerries() : Flow<List<Berry>>

    @Query("SELECT * FROM $BERRY_TABLE WHERE id = :id")
    fun getBerry(id : Int) : Flow<Berry>

    @Insert(onConflict = IGNORE)
    fun addBerry(berry: Berry)

    @Delete
    fun deleteBerry(berry: Berry)
}