package com.kkp.berryfarmer.presentation.qr_scan_screen.components

import android.widget.Toast
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
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
    val context = LocalContext.current
    val berry = viewModel.convertQRToBerry(message)
    if (showDialog){
        AlertDialog(
            onDismissRequest = { onDismiss() },
            confirmButton = {
                TextButton(onClick = {
//                    viewModel.addBerryTree(berry)
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
    }


}