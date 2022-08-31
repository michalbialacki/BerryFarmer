package com.kkp.berryfarmer.presentation.poffin_machine.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kkp.berryfarmer.domain.model.Berry
import com.kkp.berryfarmer.presentation.berry_bag.BerryBagViewModel
import com.kkp.berryfarmer.presentation.poffin_machine.PoffinMachineViewModel

@Composable
fun BerryDialog(
    berryList : List<Berry>,
    onDismiss : () -> Unit,
    viewModel : PoffinMachineViewModel = hiltViewModel()
) {
    val showDialog = remember { viewModel.dialogOpen }
    if (showDialog){
        AlertDialog(
            onDismissRequest = onDismiss,
            confirmButton = {
                TextButton(onClick = {
                    onDismiss()
                }) {
                    Text(text = "Dismiss")
                }
            },
            /*dismissButton = {
                TextButton(onClick = {
                    onDismiss()

                }) {
                    Text(text = "Dismiss")
                }
            },*/
            title = {
                Text(text = "Choose the berry")
            },
            text = {
                LazyColumn(contentPadding = PaddingValues(16.dp)){
                    if (berryList.isNotEmpty()){
                        items(berryList){ berry ->
                            BerryInList(berry = berry)
                        }
                    }
                }
            }

        )
    }

}