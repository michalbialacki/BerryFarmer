package com.kkp.berryfarmer.presentation.poffin_machine.components

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kkp.berryfarmer.domain.model.Berry
import com.kkp.berryfarmer.presentation.berry_bag.BerryBagViewModel
import com.kkp.berryfarmer.presentation.berry_bag.components.AddAlertDialog
import com.kkp.berryfarmer.presentation.poffin_machine.PoffinMachineViewModel

@Composable
fun BerryInList(
    berry : Berry,
    viewModel: PoffinMachineViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    Card(shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clickable {
                viewModel.cacheBerry(berry)
                viewModel.closeDialog()
                Toast.makeText(context,"Berry added to the mixer!",Toast.LENGTH_SHORT).show()

            }
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally , verticalArrangement = Arrangement.SpaceEvenly) {
            Text(text = berry.id.toString())
            Text(text = berry.name)
            Text(text = berry.taste)
        }
    }


}