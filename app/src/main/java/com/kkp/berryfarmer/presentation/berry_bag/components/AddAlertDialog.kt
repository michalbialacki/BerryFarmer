package com.kkp.berryfarmer.presentation.berry_bag.components

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.kkp.berryfarmer.domain.model.Berry
import com.kkp.berryfarmer.presentation.berry_bag.BerryBagViewModel

@Composable
fun AddAlertDialog(
    berry : Berry,
    showDialog : Boolean,
    onDismiss : () -> Unit,
    viewModel : BerryBagViewModel = hiltViewModel()
) {
    if (showDialog){
        AlertDialog(
            onDismissRequest = onDismiss,
            confirmButton = {
                TextButton(onClick = {
                    viewModel.deleteBerry(berry)
                    onDismiss()
                }) {
                    Text(text = "Delete this berry")
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
                Text(text = "Zero-waste?")
            },
            text = {
                Text(text = "You are about to throw away this berry. Are you sure about that?")
            }

        )
    }

}
