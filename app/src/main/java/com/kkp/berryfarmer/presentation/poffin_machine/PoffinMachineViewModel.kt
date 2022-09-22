package com.kkp.berryfarmer.presentation.poffin_machine

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kkp.berryfarmer.domain.model.Berry
import com.kkp.berryfarmer.domain.repository.BerryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.sqrt

@HiltViewModel
class PoffinMachineViewModel @Inject constructor(
    private val repo : BerryRepository,
) : ViewModel () {
    val testFlavMap = mapOf<String,String>(
        "spicy" to "0",
        "bitter" to "0",
        "dry " to "0",
        "sour" to "0",
        "sweet" to "0"
    )
    val berriesUsed = mutableStateOf(mutableListOf<Berry>(
        Berry(0,"",testFlavMap,0,0),
        Berry(0,"",testFlavMap,0,0), Berry(0,"",testFlavMap,0,0)
    ))
    var berries = mutableStateOf(emptyList<Berry>())
    var dialogOpen by mutableStateOf(false)
    var berryIndex by mutableStateOf(0)
    var shakeStarted by mutableStateOf(0L)
    var poffinDone by mutableStateOf(false)

    init {
        getBerriesToChose()
    }

    fun chooseBerry(index : Int){
        berryIndex = index
    }

    fun startShaking (tmstp : Long){
        shakeStarted = tmstp
    }

    fun timeOfShaking (newTmstp : Long) : Boolean {
        return ((newTmstp - shakeStarted) in (10000L..11000L))
    }

    fun openDialog(){
        dialogOpen = true
    }
    fun closeDialog(){
        dialogOpen = false
    }


    fun getBerriesToChose () {
        viewModelScope.launch{
            repo.getBerriesFromRoom().collect(){
                berries.value = it
            }
        }
    }

    fun cacheBerry (
        berry: Berry
    ){
        berriesUsed.value[berryIndex] = berry
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteBerryFromRoom(berry = berry)
        }
    }

    fun useBerries () {
//        TODO("Get logic straight")
        val poffinTasteMap = mutableMapOf<String,Int>()
        for (berry in berriesUsed.value){
            for (key in berry.taste.keys){
                poffinTasteMap += Pair(
                    key,
                    poffinTasteMap[key]!! + berry.taste[key]!!.toInt())
            }
        }
        val maxValueName = poffinTasteMap.maxByOrNull { it.value }
        var sameValueName = poffinTasteMap.filter { (_, value) ->
            value == maxValueName!!.value
        }
        var poffinName = "${maxValueName!!.key}"
        if (sameValueName.keys.size > 1){
            sameValueName -= maxValueName!!.key
            poffinName += (" - " + sameValueName.keys.first())
        }
        Log.d("Poffin Name", "useBerries: ${poffinName}")

        berriesUsed.value.fill(Berry(0,"",testFlavMap,0,0))

    }

    fun berryRemovedFromMixer (index : Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val berry = berriesUsed.value[index]
            if (berry.id.toString().length > 1){
                repo.addBerryToRoom(berry = berry)
            }
            berriesUsed.value[index] = Berry(0,"",testFlavMap,0,0)
        }
    }



}