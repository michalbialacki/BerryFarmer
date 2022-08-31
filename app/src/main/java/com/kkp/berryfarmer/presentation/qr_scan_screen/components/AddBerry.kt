package com.kkp.berryfarmer.presentation.qr_scan_screen.components

import android.widget.Toast
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kkp.berryfarmer.domain.model.Berry
import com.kkp.berryfarmer.presentation.berry_bag.BerryBagViewModel

@Composable
fun AddBerry(
    navController: NavController,
    message : String,
    showDialog : Boolean,
    onDismiss : () -> Unit,
    viewModel : BerryBagViewModel = hiltViewModel()
)  {
    val context = LocalContext.current
    val messageToBerry = message.removePrefix("BerryFarmerApplication:").split(";")
    val berry = Berry(messageToBerry[2].toLong(),messageToBerry[0],"Spicy")
    if (showDialog){
        AlertDialog(
            onDismissRequest = { onDismiss() },
            confirmButton = {
                TextButton(onClick = {
                    viewModel.addBerry(berry)
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