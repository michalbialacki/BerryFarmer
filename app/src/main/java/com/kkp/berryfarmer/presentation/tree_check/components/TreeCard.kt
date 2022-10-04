package com.kkp.berryfarmer.presentation.tree_check.components

import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kkp.berryfarmer.domain.model.Tree
import com.kkp.berryfarmer.presentation.tree_check.TreeCheckViewModel
import java.util.*

@Composable
fun TreeCard(
    tree : Tree,
    viewModel: TreeCheckViewModel = hiltViewModel()
) {

    if (viewModel.dialogOpen){
        DeleteTreeDialog(
            tree = viewModel.passTree(),
            showDialog = viewModel.dialogOpen,
            onDismiss = { viewModel.closeDialog() }
        )
    }

    Card(shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = {
                        viewModel.getTree(tree)
                        viewModel.openDialog()
                    }
                )
            }
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally , verticalArrangement = Arrangement.SpaceEvenly) {
            Text(text = tree.name.lowercase().replaceFirstChar { it.titlecase() })
            Text(text = tree.id.toString())
            val newDate = convertDate(tree.harvestDay.toString())
           Text(text = newDate, textAlign = TextAlign.Center)
        }
    }


}

fun convertDate (date : String) : String {
    val format = SimpleDateFormat("yyyyMMddHHmmss", Locale.GERMAN)
    var dateParsed = format.parse(date)
    var date = format.format(dateParsed)
    dateParsed = format.parse(date)
    date = if(dateParsed.compareTo(Calendar.getInstance().time) >= 0){
        "Berries not ready to be picked!"
    } else{
        "Berries ready to be picked..."
    }
    return date.toString()
}