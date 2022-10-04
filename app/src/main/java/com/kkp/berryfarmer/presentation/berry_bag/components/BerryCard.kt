package com.kkp.berryfarmer.presentation.berry_bag.components

import android.graphics.drawable.shapes.ArcShape
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kkp.berryfarmer.core.TestBerry
import com.kkp.berryfarmer.domain.model.Berry
import com.kkp.berryfarmer.presentation.berry_bag.BerryBagViewModel
import com.kkp.berryfarmer.ui.theme.BerryFarmerTheme

@Composable
fun BerryCard(
    berry : Berry,
    viewModel: BerryBagViewModel = hiltViewModel()
) {
    if (viewModel.dialogOpen){
        AddAlertDialog(
            berry = viewModel.passBerry(),
            showDialog = viewModel.dialogOpen,
            onDismiss = { viewModel.closeDialog() }
        )
    }
    Column(modifier = Modifier.fillMaxSize()) {
        Card(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
                .height(80.dp)
                .clip(
                    RoundedCornerShape(
                        topEnd = 10.dp,
                        topStart = 10.dp,
                        bottomEnd = 10.dp
                    )
                )
                .background(MaterialTheme.colors.secondary)
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxHeight()
            ) {
                Text(text = berry.name)
                Text(text = berry.id.toString())
                Spacer(modifier = Modifier
                    .height(80.dp)
                    .fillMaxWidth(0.66f))
            }
        }
    }
}

@Preview
@Composable
fun BerryCardPreview() {
    BerryFarmerTheme {
    }
}