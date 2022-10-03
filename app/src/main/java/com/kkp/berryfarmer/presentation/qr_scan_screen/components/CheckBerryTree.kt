package com.kkp.berryfarmer.presentation.qr_scan_screen.components

import android.util.Log
import android.widget.Toast
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kkp.berryfarmer.presentation.qr_scan_screen.QRScanViewModel

@Composable
fun CheckBerryTree(
    navController: NavController,
    message : String,
    showDialog : Boolean,
    onDismiss : () -> Unit,
    viewModel : QRScanViewModel = hiltViewModel()
)  {
    Log.d("QRScan", "COOL")
    val context = LocalContext.current
    val berry = viewModel.convertQRToBerry(message)
    var isTreeToHarvest = remember { mutableStateOf(false) }
    isTreeToHarvest.value = viewModel.isTreeToHarvest

    viewModel.checkIfHarvestAvaliable(tree = berry)
    if (isTreeToHarvest.value){
        AlertDialog(
            onDismissRequest = { onDismiss() },
            confirmButton = {
                TextButton(onClick = {
                    viewModel.harvestBerry(berry = berry)
                    onDismiss()
                    Toast.makeText(
                        context,
                        "The berry has been added to your backpack successfully!",
                        Toast.LENGTH_LONG).show()

                }) {
                    Text(text = "Add this berry")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    onDismiss()

                }) {
                    Text(text = "Dismiss")
                }
            },
            title = {
                Text(text = "You did it!")
            },
            text = {
                Text(text = "Your patience and resilience has been rewarded with this ultra-tasty berry. Great job!")
            }

        )
    }else{
        AlertDialog(
            onDismissRequest = { onDismiss() },
            confirmButton = {
                TextButton(onClick = {
                    onDismiss()

                }) {
                    Text(text = "Dismiss")
                }
            },
            title = {
                Text(text = "Be patient!")
            },
            text = {
                Text(text = "You are doing great! This tree will bring you joy and delicious berries " +
                        "as you care for it by watering this plant and providing it sunshine. " +
                        "This planet will be grateful for your dedication!")
            }

        )    }


}