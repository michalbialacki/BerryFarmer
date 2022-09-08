package com.kkp.berryfarmer.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kkp.berryfarmer.core.Constants.BERRY_TABLE


@Entity(tableName = BERRY_TABLE)
data class Berry (
    @PrimaryKey(autoGenerate = false)
    val id : Long,
    val name : String,
    val taste : Map<String,String>
    )