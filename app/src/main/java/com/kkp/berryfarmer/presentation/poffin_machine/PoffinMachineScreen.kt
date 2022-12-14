package com.kkp.berryfarmer.presentation.poffin_machine

import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kkp.berryfarmer.core.sensors.SensorData
import com.kkp.berryfarmer.core.sensors.SensorHandler
import com.kkp.berryfarmer.presentation.poffin_machine.components.AddToMixer
import com.kkp.berryfarmer.presentation.poffin_machine.components.BerryDialog
import com.kkp.berryfarmer.presentation.poffin_machine.components.MainSurface
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.util.*


@Composable
fun PoffinMachineScreen(
    navController: NavController?,
    viewModel: PoffinMachineViewModel = hiltViewModel()
) {
    val berryList by remember { viewModel.berries}
    val berriesInMixer by remember { viewModel.berriesUsed}
    var dialogOpen = viewModel.dialogOpen
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var accelData by remember { mutableStateOf(SensorData("")) }
    var poffinDone = viewModel.poffinDone


    if (dialogOpen){
        BerryDialog(
            berryList = berryList,
            onDismiss = { viewModel.closeDialog() }
        )
    }
    BackHandler {
        if(berriesInMixer.isNotEmpty()){
            for (berryIndex in 0 until (berriesInMixer.size)){
                viewModel.berryRemovedFromMixer(berryIndex)
            }
        }
        viewModel.timeOfShaking(Calendar.getInstance().timeInMillis)
        navController!!.navigate("Start_Screen")
    }

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {


        DisposableEffect(Unit){
            val dataManager = SensorHandler(context)
            val accelJob = scope.launch {
                dataManager.accelData.receiveAsFlow().collect{
                    accelData = it
                }
            }
            onDispose {
                dataManager.cancel()
                accelJob.cancel()
            }
        }

        when(accelData.accelerometer){
            in listOf<String>("0.00","") -> {
                viewModel.startShaking(Calendar.getInstance().timeInMillis)

            }
            else ->{
                poffinDone = viewModel.timeOfShaking(Calendar.getInstance().timeInMillis)
                if(poffinDone){
                    Toast
                        .makeText(
                            context,
                        "${viewModel.useBerries()}",
                            Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.primary),
            contentAlignment = Alignment.Center){
//            TODO("Change it a little to make it look better")
//            MainSurface(berryList = berryList)
            AddToMixer(berryList = berryList)
        }
    }
}