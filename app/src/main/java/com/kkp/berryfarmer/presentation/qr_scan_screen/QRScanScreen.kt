package com.kkp.berryfarmer.presentation.qr_scan_screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kkp.berryfarmer.presentation.qr_scan_screen.components.QRScanner

@Composable
fun QRScanScreen(
    navController: NavController,
    viewModel: QRScanViewModel = hiltViewModel()
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        val dialogOpen = viewModel.isDialogOpen
        val context = LocalContext.current
        Box(modifier = Modifier.fillMaxSize()){
            QRScanner(navController = navController)

        }
    }
    
}