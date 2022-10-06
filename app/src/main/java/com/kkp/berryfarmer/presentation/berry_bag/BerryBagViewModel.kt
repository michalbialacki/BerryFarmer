package com.kkp.berryfarmer.presentation.berry_bag

import android.icu.text.SimpleDateFormat
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kkp.berryfarmer.domain.model.Berry
import com.kkp.berryfarmer.domain.repository.BerryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class BerryBagViewModel @Inject constructor(
    private val repo : BerryRepository
) : ViewModel() {
    var berries = mutableStateOf(emptyList<Berry>())
    var berry = mutableStateOf(Berry(0, "", emptyMap<String, String>(), 0, 0))
    var dialogOpen by mutableStateOf(false)
    var dateToCard = mutableStateOf("")

    init {
        getBerries()
    }

    fun getBerries() = viewModelScope.launch {
        repo.getBerriesFromRoom().collect() { dbBerries ->
            berries.value = dbBerries
        }
    }

    fun getBerry(workingBerry: Berry) = viewModelScope.launch {
        berry.value = workingBerry
    }

    fun passBerry(): Berry {
        return berry.value
    }


    fun addBerry(berry: Berry) = viewModelScope.launch(Dispatchers.IO) {
        repo.addBerryToRoom(berry)
    }

    fun deleteBerry(berry: Berry) = viewModelScope.launch(Dispatchers.IO) {
        repo.deleteBerryFromRoom(berry)
    }

    fun openDialog() {
        dialogOpen = true
    }

    fun closeDialog() {
        dialogOpen = false
    }


    fun convertDate(date: String): String {
        val year = date.slice(0 until 4)
        val month = date.slice(4 until 6)
        val day = date.slice(6 until 8)
        val time = date.slice(8 until 10)+":"+date.slice(10 until 12)
        return "Berry picked on ${day}.${month}.${year} at ${time}"
    }
}