package com.kkp.berryfarmer.presentation.start_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kkp.berryfarmer.R
import com.kkp.berryfarmer.ui.theme.BerryFarmerTheme

@Composable
fun IconRow(
    navController: NavController?,
    navRoute : List<String>,
    icon : List<Int>,
) {
    if(navRoute.isNotEmpty() && icon.isNotEmpty() && navRoute.size == icon.size){
        val listSize = navRoute.size
        val arrangement = if (listSize==1) Arrangement.Center else Arrangement.SpaceBetween
        Row(
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
                .padding(
                    top = 8.dp,
                    bottom =8.dp
                )
                .background(Color.Transparent),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = arrangement
        ){
            for(index in navRoute.indices){
                NavIcon(
                    navController = navController,
                    navRoute = navRoute[index],
                    icon = icon[index]
                )
            }
        }

    }
}

val testStringList = listOf<String>(
    "QR_Scan_Screen",
    "Poffin_Maker"
)
val testIntList = listOf<Int>(
    R.drawable.qr_code_icon,
    R.drawable.blender_icon,
)


@Preview
@Composable
fun IconRowPreview() {
    BerryFarmerTheme {
        IconRow(null, testStringList, testIntList)
    }
}