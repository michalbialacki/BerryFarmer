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
import java.lang.Exception
import java.lang.NullPointerException
import javax.inject.Inject

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
    var poffinNameResult by mutableStateOf("")

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
        viewModelScope.launch(Dispatchers.IO) {
            if(berriesUsed.value[berryIndex].name.length > 1){
                repo.addBerryToRoom(berriesUsed.value[berryIndex])
            }
            berriesUsed.value[berryIndex] = berry
            repo.deleteBerryFromRoom(berry = berry)
        }
    }

    fun useBerries () : String {
        var poffinName = ""
        viewModelScope.launch {
            val poffinTasteMap = mutableMapOf<String, Int>(
                "spicy" to 0,
                "bitter" to 0,
                "dry " to 0,
                "sour" to 0,
                "sweet" to 0
            )
            for (berry in berriesUsed.value) {
                for (key in berry.taste.keys){
                    try {
                        val addMap = mapOf<String,Int>(key to poffinTasteMap[key]!! + berry.taste[key]!!.toInt())
                        poffinTasteMap += addMap
                    }
                    catch (e:NullPointerException){
                        Log.d("Cheque", " DEAD but: ${poffinTasteMap.keys}")
                    }

                }
            }
            val maxValueName = poffinTasteMap.maxByOrNull { it.value }
            var sameValueName = poffinTasteMap.filter { (_, value) ->
                value == maxValueName!!.value
            }
            poffinName = "${maxValueName!!.key}"
            if (sameValueName.keys.size > 1){
                sameValueName -= maxValueName!!.key
                poffinName += (" - " + sameValueName.keys.first())
            }

            berriesUsed.value.fill(Berry(0, "", testFlavMap, 0, 0))
        }
        return "Congratulations! You have made a ${poffinName} Poffin!"
    }

    fun berryRemovedFromMixer (index : Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val berry = berriesUsed.value[index]
                if (berry.id.toString().length > 1){
                    repo.addBerryToRoom(berry = berry)
                }
                berriesUsed.value[index] = Berry(0,"",testFlavMap,0,0)
            } catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
}