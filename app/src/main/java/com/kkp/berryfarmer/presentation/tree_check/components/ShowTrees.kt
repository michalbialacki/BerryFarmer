package com.kkp.berryfarmer.presentation.tree_check.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kkp.berryfarmer.domain.model.Berry
import com.kkp.berryfarmer.domain.model.Tree
import com.kkp.berryfarmer.presentation.berry_bag.BerryBagViewModel
import com.kkp.berryfarmer.presentation.berry_bag.components.BerryCard
import com.kkp.berryfarmer.presentation.tree_check.TreeCheckViewModel

@Composable
fun ShowTrees(
    treeList : List<Tree>,
    viewModel: TreeCheckViewModel = hiltViewModel()
) {
    Surface(modifier = Modifier.fillMaxSize(0.5f)) {
        LazyColumn(contentPadding = PaddingValues(16.dp)){
            if(treeList.isNotEmpty()){
                items(treeList){ tree ->
                    TreeCard(tree = tree)
                }
            } else{
                items(1){
                    Text(text = "This bag empty")
                }
            }

        }
    }

}