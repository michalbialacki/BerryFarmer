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
        "spicy" to "10",
        "bitter" to "10",
        "dry " to "10",
        "sour" to "10",
        "sweet" to "10"
    )
    private val berriesUsed = mutableStateOf(mutableListOf<Berry>(
        Berry(0,"",testFlavMap),
        Berry(0,"",testFlavMap), Berry(0,"",testFlavMap)
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
        viewModelScope.launch (Dispatchers.IO){
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
        viewModelScope.launch(Dispatchers.IO) {
            berriesUsed.value.fill(Berry(0,"",testFlavMap))
        }
    }

    fun berryRemovedFromMixer (index : Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val berry = berriesUsed.value[index]
            if (berry.id.toString().length == 14){
                repo.addBerryToRoom(berry = berry)
            }
            berriesUsed.value[index] = Berry(0,"",testFlavMap)
        }
    }



}