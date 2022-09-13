package com.kkp.berryfarmer.di

import android.content.Context
import androidx.room.Room
import com.kkp.berryfarmer.core.Constants.BERRY_TABLE
import com.kkp.berryfarmer.data.network.BerryDao
import com.kkp.berryfarmer.data.network.BerryDb
import com.kkp.berryfarmer.data.network.TreeDao
import com.kkp.berryfarmer.data.repository.BerryRepositoryImpl
import com.kkp.berryfarmer.data.repository.TreeRepositoryImpl
import com.kkp.berryfarmer.domain.repository.BerryRepository
import com.kkp.berryfarmer.domain.repository.TreeRepository
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
        ).fallbackToDestructiveMigration()
        .build()

    @Provides
    fun provideBerryDao(
        berryDb: BerryDb
    ) = berryDb.berryDao()

    @Provides
    fun provideTreeDao(
        berryDb: BerryDb
    ) = berryDb.TreeDao()

    @Provides
    fun provideBerryRepository(
        berryDao: BerryDao
    ) : BerryRepository = BerryRepositoryImpl(
        berryDao = berryDao
    )

    @Provides
    fun provideTreeRepository(
        treeDao: TreeDao
    ) : TreeRepository = TreeRepositoryImpl(
        treeDao = treeDao
    )
}