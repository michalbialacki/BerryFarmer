package com.kkp.berryfarmer.presentation.start_screen

import android.icu.text.SimpleDateFormat
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.kkp.berryfarmer.presentation.start_screen.components.IconSurface
import com.kkp.berryfarmer.ui.theme.BerryFarmerTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@Composable
fun StartScreen(
    navController: NavController?,
    viewModel: StartScreenViewModel = hiltViewModel()
) {
    Surface(modifier = Modifier
        .fillMaxSize()) {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(
                start = 8.dp,
                end = 8.dp,
                top = 16.dp,
                bottom = 48.dp
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .height(180.dp)
                    .width(240.dp)
                    .clip(
                        RoundedCornerShape(10.dp)
                    )
                    .background(Color.LightGray)

            ) // This is a placeholder for logo, change when logo created
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(16.dp))
            IconSurface(navController = navController)


        }
    }
}

@Preview
@Composable
fun StartScreenPreview() {
    BerryFarmerTheme {
        StartScreen(navController = null)
    }
}