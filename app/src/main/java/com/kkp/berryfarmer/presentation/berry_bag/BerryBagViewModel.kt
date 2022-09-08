package com.kkp.berryfarmer.presentation.berry_bag

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

@HiltViewModel
class BerryBagViewModel @Inject constructor(
    private val repo : BerryRepository
) : ViewModel() {
    var berries = mutableStateOf(emptyList<Berry>())
    var berry = mutableStateOf(Berry(0,"", emptyMap<String,String>()))
    var dialogOpen by mutableStateOf(false)

    init {
        getBerries()
    }

    fun getBerries () = viewModelScope.launch {
        repo.getBerriesFromRoom().collect(){ dbBerries ->
            berries.value = dbBerries
        }
    }

    fun getBerry(id : Int) = viewModelScope.launch {
        repo.getBerryFromRoom(id).collect(){ dbBerry ->
            berry.value = dbBerry
        }
    }

    fun addBerry(berry: Berry) = viewModelScope.launch(Dispatchers.IO) {
        repo.addBerryToRoom(berry)
    }

    fun deleteBerry(berry: Berry) = viewModelScope.launch(Dispatchers.IO) {
        repo.deleteBerryFromRoom(berry)
    }
    fun openDialog (){
        dialogOpen = true
    }
    fun closeDialog (){
        dialogOpen = false
    }
}