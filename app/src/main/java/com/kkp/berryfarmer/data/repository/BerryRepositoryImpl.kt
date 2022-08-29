package com.kkp.berryfarmer.data.repository

import com.kkp.berryfarmer.data.network.BerryDao
import com.kkp.berryfarmer.domain.model.Berry
import com.kkp.berryfarmer.domain.repository.BerryRepository
import kotlinx.coroutines.flow.Flow

class BerryRepositoryImpl(
    private val berryDao: BerryDao
) : BerryRepository{
    override suspend fun getBerriesFromRoom(): Flow<List<Berry>> = berryDao.getBerries()

    override suspend fun getBerryFromRoom(id: Int): Flow<Berry> = berryDao.getBerry(id)

    override suspend fun addBerryToRoom(berry: Berry) = berryDao.addBerry(berry)

    override suspend fun deleteBerryFromRoom(berry: Berry) = berryDao.deleteBerry(berry)

}