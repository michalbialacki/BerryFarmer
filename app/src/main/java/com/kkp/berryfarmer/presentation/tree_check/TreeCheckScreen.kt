package com.kkp.berryfarmer.presentation.tree_check

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kkp.berryfarmer.presentation.berry_bag.BerryBagViewModel
import com.kkp.berryfarmer.presentation.berry_bag.components.AddBerryButton
import com.kkp.berryfarmer.presentation.berry_bag.components.ShowBerries
import com.kkp.berryfarmer.presentation.tree_check.components.ShowTrees

@Composable
fun TreeCheckScreen(
    navController: NavController?,
    viewModel : TreeCheckViewModel = hiltViewModel()
) {
    val testTasteList = listOf(
        listOf("spicy","0"),
        listOf("dry","0"),
        listOf("sweet","0"),
        listOf("bitter","5"),
        listOf("sour","0")

    )
    val gradient = Brush.verticalGradient(
        listOf(
            Color(0xff47cba3),
            Color(0xffb5ffff),
            Color(0xff47cba3)
        )
    )
    val treesList by remember {viewModel.trees}



    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.primary)
            ,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Your Berry trees",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .offset(y= - 50.dp)
            )
            ShowTrees(treeList = treesList)
        }

    }
}