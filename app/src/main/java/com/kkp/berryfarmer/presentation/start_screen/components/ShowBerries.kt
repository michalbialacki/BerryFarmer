package com.kkp.berryfarmer.presentation.start_screen.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kkp.berryfarmer.domain.model.Berry
import com.kkp.berryfarmer.presentation.start_screen.BerryBagViewModel

@Composable
fun ShowBerries(
    berriesList : List<Berry>,
    viewModel: BerryBagViewModel = hiltViewModel()
) {
    Surface(modifier = Modifier.fillMaxSize(0.5f)) {
        LazyColumn(contentPadding = PaddingValues(16.dp)){
            items(berriesList){ berry ->
                BerryCard(berry = berry)
            }
        }
    }

}