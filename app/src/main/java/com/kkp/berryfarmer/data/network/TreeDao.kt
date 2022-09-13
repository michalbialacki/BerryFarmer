package com.kkp.berryfarmer.data.network

import androidx.room.*
import com.kkp.berryfarmer.core.Constants
import com.kkp.berryfarmer.domain.model.Berry
import com.kkp.berryfarmer.domain.model.Tree
import kotlinx.coroutines.flow.Flow

@Dao
interface TreeDao {
    @Query("SELECT * FROM ${Constants.TREE_TABLE} ORDER BY id ASC")
    fun getTrees() : Flow<List<Tree>>

    @Query("SELECT * FROM ${Constants.TREE_TABLE} WHERE id = :id")
    fun getTree(id : Long) : Flow<Tree>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addTree(tree : Tree)

    @Query("UPDATE ${Constants.TREE_TABLE} SET harvested =:harvested WHERE id = :id")
    fun updateTree(harvested : Boolean, id: Long)

    @Delete
    fun deleteTree(tree : Tree)
}