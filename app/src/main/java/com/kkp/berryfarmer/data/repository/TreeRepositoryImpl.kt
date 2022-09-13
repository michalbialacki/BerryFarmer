package com.kkp.berryfarmer.data.repository

import com.kkp.berryfarmer.data.network.BerryDao
import com.kkp.berryfarmer.data.network.TreeDao
import com.kkp.berryfarmer.domain.model.Berry
import com.kkp.berryfarmer.domain.model.Tree
import com.kkp.berryfarmer.domain.repository.BerryRepository
import com.kkp.berryfarmer.domain.repository.TreeRepository
import kotlinx.coroutines.flow.Flow

class TreeRepositoryImpl(
    private val treeDao: TreeDao
) : TreeRepository {
    override suspend fun getTreesFromRoom(): Flow<List<Tree>> = treeDao.getTrees()

    override suspend fun getTreeFromRoom(id: Long): Flow<Tree> = treeDao.getTree(id)

    override suspend fun addTreeToRoom(tree: Tree) = treeDao.addTree(tree)

    override suspend fun deleteTreeFromRoom(tree: Tree) = treeDao.deleteTree(tree)

}