package com.kkp.berryfarmer.presentation.berry_bag.components

import android.widget.Toast
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.kkp.berryfarmer.domain.model.Berry
import com.kkp.berryfarmer.presentation.berry_bag.BerryBagViewModel
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

@Composable
fun AddBerryButton (
    viewModel : BerryBagViewModel = hiltViewModel(),
){
    val testFlavMap = mapOf<String,String>(
        "spicy" to "10",
        "bitter" to "10",
        "dry " to "10",
        "sour" to "10",
        "sweet" to "10"
    )
    val context = LocalContext.current
    FloatingActionButton(
        onClick = {
            val timeStamp = DateTimeFormatter
                .ofPattern("yyyyMMddHHmmss")
                .withZone(ZoneOffset.UTC)
                .format(Instant.now())
            val newBerry = Berry(timeStamp.toLong(),"Testo Berry",testFlavMap)
            viewModel.addBerry(newBerry)
            Toast.makeText(context,"${newBerry}",Toast.LENGTH_SHORT).show()

        },
        backgroundColor = MaterialTheme.colors.primary
    ){
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Adds berry")
    }
}