package com.kkp.berryfarmer.presentation.poffin_machine.components

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
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
    val context = LocalContext.current
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
            title = {
                if(berryList.isNotEmpty()){
                    Text(text = "Choose the berry")
                }else{
                    Text(text = "Oopsie!")
                }
            },
            text = {
                if (berryList.isNotEmpty()) {
                    LazyColumn(contentPadding = PaddingValues(16.dp)) {
                        items(berryList) { berry ->
                            BerryInList(berry = berry)
                        }
                    }
                }else{
                    Text(text = "You got no berries")
                }
            }

        )
    }

}