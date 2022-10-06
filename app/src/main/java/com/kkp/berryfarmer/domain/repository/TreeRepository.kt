package com.kkp.berryfarmer.domain.repository

import com.kkp.berryfarmer.domain.model.Berry
import com.kkp.berryfarmer.domain.model.Tree
import kotlinx.coroutines.flow.Flow

interface TreeRepository {
    suspend fun getTreesFromRoom(): Flow<List<Tree>>

    suspend fun getTreeFromRoom(id : Long) : Flow<Tree>

    suspend fun addTreeToRoom(tree: Tree)

    suspend fun deleteTreeFromRoom(tree: Tree)

    suspend fun updateTreeFromRoom(harvestDay : Long, id : Long)
}