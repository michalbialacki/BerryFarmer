package com.kkp.berryfarmer.presentation.berry_bag.components

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kkp.berryfarmer.R
import com.kkp.berryfarmer.domain.model.Berry
import com.kkp.berryfarmer.presentation.berry_bag.BerryBagViewModel

@Composable
fun ShowBerries(
    berriesList : List<Berry>,
    viewModel: BerryBagViewModel = hiltViewModel()
) {
    Surface(
        modifier = Modifier
            .width(400.dp)
            .height(600.dp)
            .clip(RoundedCornerShape(10.dp))
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(10.dp)),
            contentAlignment = Alignment.Center

        ){
            Image(
                painter = painterResource(id = R.drawable.backpack),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxSize(2f)
                    .background(MaterialTheme.colors.primaryVariant)
            )
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize(0.5f)
                    .offset(y = 50.dp)

            ){
                if(berriesList.isNotEmpty()){
                    items(berriesList){ berry ->
                        BerryCard(berry = berry)
                    }
                } else{
                    items(1){
                        Text(text = "This bag empty")
                    }
                }
            }
        }
    }

}