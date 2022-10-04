package com.kkp.berryfarmer.core

import com.kkp.berryfarmer.domain.model.Berry

object TestBerry {
    private val testFlavMap = mapOf<String,String>(
        "spicy" to "0",
        "bitter" to "0",
        "dry " to "0",
        "sour" to "0",
        "sweet" to "0"
    )
    val testBerry = Berry(
        name = "Test Berry",
        id = 0,
        growthTime = 4,
        maxHarvest = 5,
        taste = testFlavMap

    )
}