package com.kkp.berryfarmer.presentation.poffin_machine.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kkp.berryfarmer.R
import com.kkp.berryfarmer.domain.model.Berry
import com.kkp.berryfarmer.presentation.poffin_machine.PoffinMachineViewModel
import com.kkp.berryfarmer.ui.theme.BerryFarmerTheme

@Composable
fun MainSurface(
    berryList : List<Berry>
) {

    Box(
        modifier = Modifier
            .fillMaxSize(0.85f)
            .padding(
                start = 4.dp,
                end = 4.dp,
                bottom = 4.dp
            ),
        contentAlignment = Alignment.Center
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(10.dp))
                .background(
                    MaterialTheme
                        .colors
                        .primaryVariant
                )
            ,
        ) {
            AddToMixer(berryList = berryList)
        }
    }


}
