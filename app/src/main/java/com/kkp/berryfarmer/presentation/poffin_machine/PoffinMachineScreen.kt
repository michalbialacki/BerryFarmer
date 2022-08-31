package com.kkp.berryfarmer.presentation.poffin_machine

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kkp.berryfarmer.presentation.poffin_machine.components.BerryDialog
import com.kkp.berryfarmer.presentation.poffin_machine.components.MainSurface


@Composable
fun PoffinMachineScreen(
    navController: NavController?,
    viewModel: PoffinMachineViewModel = hiltViewModel()
) {
//    viewModel.getAcceleration()
    val berryList by remember { viewModel.berries}
    var dialogOpen = viewModel.dialogOpen
    if (dialogOpen){
        BerryDialog(
            berryList = berryList,
            onDismiss = { viewModel.closeDialog() }
        )
    }

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            MainSurface(berryList = berryList)
        }

        Log.d("Accl", "PoffinMachineScreen: ${viewModel.prevAccl}")

    }
}