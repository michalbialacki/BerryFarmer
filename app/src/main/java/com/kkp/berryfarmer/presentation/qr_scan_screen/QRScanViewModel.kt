package com.kkp.berryfarmer.presentation.qr_scan_screen

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kkp.berryfarmer.domain.model.Berry
import com.kkp.berryfarmer.domain.model.Tree
import com.kkp.berryfarmer.domain.repository.BerryRepository
import com.kkp.berryfarmer.domain.repository.TreeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class QRScanViewModel @Inject constructor(
    private val berryRepo : BerryRepository,
    private val treeRepo : TreeRepository
    ) : ViewModel() {

    var isDialogOpen by mutableStateOf(false)
    var isTreeToHarvest by mutableStateOf(false)

    fun openDialog() {
        isDialogOpen = true
    }
    fun closeDialog(){
        isDialogOpen = false
    }

    fun addBerryTree(tree: Berry) = viewModelScope.launch(Dispatchers.IO) {
        val newHarvestTime = Calendar.getInstance()
        newHarvestTime.add(Calendar.DATE, 3)
        val harvestDay = SimpleDateFormat("yyyyMMddHHmmss", Locale.GERMAN )
            .format(newHarvestTime.time).toLong()
        val treeToRoom = Tree(
            id = tree.id,
            name = tree.name,
            harvestDay = harvestDay
        )
        treeRepo.addTreeToRoom(treeToRoom)
        isTreeToHarvest = false
    }

    fun checkIfHarvestAvaliable(tree: Berry) {
        viewModelScope.launch ( Dispatchers.IO ){
            try {
                treeRepo.getTreeFromRoom(tree.id).collect(){
                    val harvestDate = SimpleDateFormat("yyyyMMddHHmmss", Locale.GERMAN)
                        .parse("${it.harvestDay}")
                    var currentDate = SimpleDateFormat("yyyyMMddHHmmss", Locale.GERMAN )
                        .format(Calendar.getInstance().time)
                    val frmtdCurrDate = SimpleDateFormat("yyyyMMddHHmmss", Locale.GERMAN)
                        .parse(currentDate)
                    Log.d("Tree", "${harvestDate.compareTo(frmtdCurrDate)}")

                    if (frmtdCurrDate.compareTo(harvestDate) < 0){
                        Log.d("Tree", "We're here")
                        isTreeToHarvest = false
                    } else{
                        isTreeToHarvest = true

                    }
                }
            } catch (e:Exception){
                addBerryTree(tree = tree)
                isTreeToHarvest = false
            }
        }
    }

    fun harvestBerry(berry: Berry) {
        viewModelScope.launch(Dispatchers.IO) {
            val newBerryQuantity = (1..berry.maxHarvest).random()
            for(index in 0 until (newBerryQuantity-1)){
                val newBerryToBackpack = berry.copy(id = ((berry.id).toString() + index.toString()).toLong())
                berryRepo.addBerryToRoom(newBerryToBackpack)
            }
        }
    }



    fun convertQRToBerry (string : String) : Berry{
        var flavMap = emptyMap<String,String>()
        val stringToList = string.removePrefix("BerryFarmerApplication:").split(";")
        val flavList = stringToList[1].removeSurrounding("{","}").split(",")
        flavList.map {
            val temp = it.split("=")
            flavMap += mapOf<String,String>(temp[0] to temp[1])
        }
        val growthTime = stringToList[3].removePrefix(" ").toInt()
        val maxHarvest = stringToList[4].toInt()
        return Berry(stringToList[2].toLong(),stringToList[0],flavMap, growthTime, maxHarvest)


    }
}