package com.kkp.berryfarmer.presentation.poffin_machine.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kkp.berryfarmer.domain.model.Berry
import com.kkp.berryfarmer.presentation.poffin_machine.PoffinMachineViewModel
import com.kkp.berryfarmer.ui.theme.BerryFarmerTheme

@Composable
fun MainSurface(
//    navController: NavController?,
    berryList : List<Berry>
) {

    Box(
        modifier = Modifier
            .fillMaxSize(0.85f)
            .background(Color.White)
            .offset(y = 20.dp)
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
                .background(Color.DarkGray)
        ) {
            AddToMixer(berryList = berryList)
        }
    }


}
