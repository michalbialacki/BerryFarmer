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
}