package com.kkp.berryfarmer.presentation.start_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kkp.berryfarmer.R
import com.kkp.berryfarmer.domain.model.Berry
import com.kkp.berryfarmer.ui.theme.BerryFarmerTheme

@Composable
fun IconSurface(
    navController: NavController?,
) {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(0.85f)
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colors.primaryVariant),
        contentAlignment = Alignment.Center
    ){
        Image(
            painter = painterResource(id = R.drawable.berrytree),
            contentDescription = "Berry tree bckg",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(750.dp)
                .offset(y = -30.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = 32.dp,
                    bottom = 16.dp,
                    start = 8.dp,
                    end = 8.dp
                )
        ) {
            IconRow(
                navController = navController,
                navRoute = listOf("Tree_Check_Screen") ,
                icon = listOf(R.drawable.tree_icon),
                animTime = 100
            )

            IconRow(
                navController = navController,
                navRoute = listOf(
                    "Berry_Bag_Screen",
                    "Poffin_Machine_Screen"
                ),
                icon = listOf(
                    R.drawable.backpack_icon,
                    R.drawable.blender_icon
                ),
                animTime = 300
            )
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
            )
            IconRow(
                navController = navController,
                navRoute = listOf("QR_Scan_Screen") ,
                icon = listOf(R.drawable.qr_code_icon),
                animTime = 500
            )

        }
    }

}
