package com.kkp.berryfarmer.presentation.start_screen.components

import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kkp.berryfarmer.R

@Composable
fun NavIcon(
    navController: NavController?,
    navRoute : String,
    icon : Int
) {
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .width(80.dp)
            .height(80.dp)
            .background(MaterialTheme.colors.secondary)
            .clickable {
                       navController!!.navigate(navRoute)
            },
        contentAlignment = Alignment.Center,
    ){
        Icon(
            painter = painterResource(id = icon),
            contentDescription = navRoute,
            modifier = Modifier
                .size(60.dp)
        )
    }



}
@Preview
@Composable
fun ButtonPreview() {
    NavIcon(navController = null, navRoute = "TEST", icon = R.drawable.blender_icon )
}