package com.kkp.berryfarmer.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kkp.berryfarmer.core.Constants
import java.util.*

@Entity(tableName = Constants.TREE_TABLE)
data class Tree (
    @PrimaryKey(autoGenerate = false)
    val id : Long,
    val name : String,
    val harvestDay : Long,
)