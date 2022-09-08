package com.kkp.berryfarmer.presentation.qr_scan_screen

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kkp.berryfarmer.domain.model.Berry
import com.kkp.berryfarmer.domain.repository.BerryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QRScanViewModel @Inject constructor(
    private val repo : BerryRepository
) : ViewModel() {

    var isDialogOpen by mutableStateOf(false)

    fun openDialog() {
        isDialogOpen = true
    }
    fun closeDialog(){
        isDialogOpen = false
    }

    fun addBerry(berry: Berry) = viewModelScope.launch(Dispatchers.IO) {
        repo.addBerryToRoom(berry)
    }


    fun convertQRToBerry (string : String) : Berry{
        var flavMap = emptyMap<String,String>()
        val stringToList = string.removePrefix("BerryFarmerApplication:").split(";")
        val flavList = string[1].toString().removeSurrounding("{","}").split(",")
        flavList.map {
            val temp = it.split("=")
            flavMap += mapOf<String,String>(temp[0] to temp[1])
        }
        return Berry(stringToList[2].toLong(),stringToList[0],flavMap)


    }
}