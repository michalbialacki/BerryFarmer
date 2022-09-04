package com.kkp.berryfarmer.presentation.poffin_machine.components

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kkp.berryfarmer.domain.model.Berry
import com.kkp.berryfarmer.presentation.poffin_machine.PoffinMachineViewModel

@Composable
fun AddBerryIcon(
    viewModel: PoffinMachineViewModel = hiltViewModel(),
    index : Int,
    iconSize : Dp = 60.dp
) {

    Column {
        Row(modifier = Modifier
            .width(iconSize)
            .offset(x = 20.dp)
        ) {

            Spacer(modifier = Modifier
                .width(iconSize / 2)
                .height(iconSize / 2))
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.White)
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete berry",
                    modifier = Modifier
                        .height(iconSize / 2)
                        .width(iconSize / 2)
                        .clickable {
                            viewModel.berryRemovedFromMixer(index)
                        }
                )
            }

        }
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(Color.White)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add berry to mixer",
                modifier = Modifier
                    .height(iconSize)
                    .width(iconSize)
                    .clickable {
                        viewModel.openDialog()
                        viewModel.chooseBerry(index)
                    }
            )
        }
    }

}
