package com.kkp.berryfarmer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kkp.berryfarmer.presentation.start_screen.BerryBagScreen
import com.kkp.berryfarmer.ui.theme.BerryFarmerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BerryFarmerTheme {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "Berry_Bag_Screen"){
                        composable("Start_Screen"){
                            TODO("Add Berry Farmer Start Screen")
                        }
                        composable("Berry_Bag_Screen"){
                           BerryBagScreen(navController = navController)
                        }
                        composable("QR_Scan_Screen"){
                            TODO("Add QR Scan Screen")
                        }
                        composable("Poffin_Machine_Screen"){
                            TODO("Add Poffin Machine Screen")
                        }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BerryFarmerTheme {
        Greeting("Android")
    }
}