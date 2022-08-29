package com.kkp.berryfarmer.presentation.start_screen.components

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kkp.berryfarmer.domain.model.Berry
import com.kkp.berryfarmer.presentation.start_screen.BerryBagViewModel

@Composable
fun BerryCard(
    berry : Berry,
    viewModel: BerryBagViewModel = hiltViewModel()
) {
    Card(shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .pointerInput(Unit) {
                detectTapGestures (
                    onLongPress = {
                    viewModel.deleteBerry(berry)
//                        TODO("Implement delete alert dialog!")
                    }
                )
            }
    ) {
       Column(horizontalAlignment = Alignment.CenterHorizontally , verticalArrangement = Arrangement.SpaceEvenly) {
           Text(text = berry.id.toString())
           Text(text = berry.name)
           Text(text = berry.taste)
       }
    }


}