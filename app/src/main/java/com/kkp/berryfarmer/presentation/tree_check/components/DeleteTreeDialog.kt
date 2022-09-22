package com.kkp.berryfarmer.presentation.tree_check.components

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.kkp.berryfarmer.domain.model.Berry
import com.kkp.berryfarmer.domain.model.Tree
import com.kkp.berryfarmer.presentation.berry_bag.BerryBagViewModel
import com.kkp.berryfarmer.presentation.tree_check.TreeCheckViewModel

@Composable
fun DeleteTreeDialog(
    tree : Tree,
    showDialog : Boolean,
    onDismiss : () -> Unit,
    viewModel : TreeCheckViewModel = hiltViewModel()
) {
    if (showDialog){
        AlertDialog(
            onDismissRequest = onDismiss,
            confirmButton = {
                TextButton(onClick = {
                    viewModel.deleteTree(tree = tree)
                    onDismiss()
                }) {
                    Text(text = "Cut the tree down!")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    onDismiss()

                }) {
                    Text(text = "Dismiss")
                }
            },
            title = {
                Text(text = "Cutting down this tree?")
            },
            text = {
                Text(text = "You are about to cut down the tree. Are you sure about that?")
            }

        )
    }
}