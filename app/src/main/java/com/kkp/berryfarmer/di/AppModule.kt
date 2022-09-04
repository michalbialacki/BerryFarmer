package com.kkp.berryfarmer.di

import android.content.Context
import androidx.room.Room
import com.kkp.berryfarmer.core.Constants.BERRY_TABLE
import com.kkp.berryfarmer.data.network.BerryDao
import com.kkp.berryfarmer.data.network.BerryDb
import com.kkp.berryfarmer.data.repository.BerryRepositoryImpl
import com.kkp.berryfarmer.domain.repository.BerryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext


@Module
@InstallIn(ViewModelComponent::class)
class AppModule {
    @Provides
    fun provideBerryDb(
        @ApplicationContext
        context : Context
    ) = Room.databaseBuilder(
        context,
        BerryDb::class.java,
        BERRY_TABLE
    ).build()

    @Provides
    fun provideBerryDao(
        berryDb: BerryDb
    ) = berryDb.berryDao()

    @Provides
    fun provideBerryRepository(
        berryDao: BerryDao
    ) : BerryRepository = BerryRepositoryImpl(
        berryDao = berryDao
    )
}