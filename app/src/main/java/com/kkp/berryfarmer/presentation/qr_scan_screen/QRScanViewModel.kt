package com.kkp.berryfarmer.presentation.qr_scan_screen

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kkp.berryfarmer.domain.model.Berry
import com.kkp.berryfarmer.domain.model.Tree
import com.kkp.berryfarmer.domain.repository.BerryRepository
import com.kkp.berryfarmer.domain.repository.TreeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QRScanViewModel @Inject constructor(
    private val berryRepo : BerryRepository,
    private val treeRepo : TreeRepository
    ) : ViewModel() {

    var isDialogOpen by mutableStateOf(false)

    fun openDialog() {
        isDialogOpen = true
    }
    fun closeDialog(){
        isDialogOpen = false
    }

    fun addBerryTree(tree: Tree) = viewModelScope.launch(Dispatchers.IO) {
        treeRepo.addTreeToRoom(tree)
    }

    fun checkIfHarvestAvaliable(berry: Berry) = viewModelScope.launch ( Dispatchers.IO ){
        try {
            treeRepo.getTreeFromRoom(berry.id).collect(){
                if (!it.harvested){
//                    TODO("Add logic")
                }
            }
        } catch (e:Exception){

        }
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