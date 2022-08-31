package com.kkp.berryfarmer.presentation.poffin_machine.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kkp.berryfarmer.domain.model.Berry

@Composable
fun AddToMixer(
    berryList : List<Berry>
) {

    val iconHeight = 60.dp
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(iconHeight + 40.dp)
            .offset(y = -60.dp)
            .padding(
                top = 2.dp,
                bottom = 2.dp,
                start = 4.dp,
                end = 4.dp
            )
            .background(Color.LightGray),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        AddBerryIcon(index = 0)
        AddBerryIcon(index = 1)
        AddBerryIcon(index = 2)
    }

}

@Preview
@Composable
fun AddToMixerPreview() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Box(
            modifier = Modifier
                .fillMaxSize(0.80f)
                .background(Color.White),
                contentAlignment = Alignment.TopCenter
        ){
            AddToMixer(berryList = emptyList<Berry>())

        }

    }
}