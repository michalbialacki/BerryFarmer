package com.kkp.berryfarmer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kkp.berryfarmer.presentation.berry_bag.BerryBagScreen
import com.kkp.berryfarmer.presentation.qr_scan_screen.QRScanScreen
import com.kkp.berryfarmer.presentation.start_screen.StartScreen
import com.kkp.berryfarmer.ui.theme.BerryFarmerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BerryFarmerTheme {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "Start_Screen"){
                        composable("Start_Screen"){
                            StartScreen(navController = navController)
                        }
                        composable("Berry_Bag_Screen"){
                           BerryBagScreen(navController = navController)
                        }
                        composable("QR_Scan_Screen"){
                            QRScanScreen(navController = navController)
                        }
                        composable("Poffin_Machine_Screen"){
                            TODO("Add Poffin Machine Screen")
                        }
                        composable("Tree_Check_Screen"){
                            TODO("Add Tree check screen")
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