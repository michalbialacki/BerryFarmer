package com.kkp.berryfarmer.presentation.poffin_machine

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kkp.berryfarmer.core.sensors.Accelerometer
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
    private val accelerometer: Accelerometer
) : ViewModel () {

    val berriesUsed = mutableStateOf(mutableListOf<Berry>(Berry(0,"",""), Berry(0,"",""), Berry(0,"","")))
    var berries = mutableStateOf(emptyList<Berry>())
    var dialogOpen by mutableStateOf(false)
    var berryIndex by mutableStateOf(0)
    var prevAccl by mutableStateOf(0.0)

    init {
        getBerriesToChose()
    }

    /*fun getAcceleration(){
        accelerometer.startListening()
        accelerometer.setOnSensorValuesChangedListener { values ->
            val x = values[0]
            val y = values[1]
            val z = values[2]
            var delta = 0.0
            val accl = sqrt((x*x)+(y*y)+(z*z))
            delta = accl - prevAccl
            prevAccl = accl.toDouble()
        }
    }*/


    fun chooseBerry(index : Int){
        berryIndex = index
    }

    fun openDialog(){
        dialogOpen = true
    }
    fun closeDialog(){
        dialogOpen = false
    }


    fun getBerriesToChose () {
        viewModelScope.launch(Dispatchers.IO) {
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
            berriesUsed.value.fill(Berry(0,"",""))
        }
    }

    fun berryRemovedFromMixer (index : Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val berry = berriesUsed.value[index]
            if (berry.id.toString().length == 14){
                repo.addBerryToRoom(berry = berry)
            }
            berriesUsed.value[index] = Berry(0,"","")
        }
    }



}