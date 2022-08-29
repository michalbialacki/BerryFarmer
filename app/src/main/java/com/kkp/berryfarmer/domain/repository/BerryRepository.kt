package com.kkp.berryfarmer.domain.repository

import com.kkp.berryfarmer.domain.model.Berry
import kotlinx.coroutines.flow.Flow

interface BerryRepository {
    suspend fun getBerriesFromRoom(): Flow<List<Berry>>

    suspend fun getBerryFromRoom(Long : Int) : Flow<Berry>

    suspend fun addBerryToRoom(berry: Berry)

    suspend fun deleteBerryFromRoom(berry: Berry)
}