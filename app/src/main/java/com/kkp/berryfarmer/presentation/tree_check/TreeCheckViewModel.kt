package com.kkp.berryfarmer.presentation.tree_check

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kkp.berryfarmer.domain.model.Berry
import com.kkp.berryfarmer.domain.model.Tree
import com.kkp.berryfarmer.domain.repository.TreeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TreeCheckViewModel @Inject constructor(
    private val repo : TreeRepository
) : ViewModel() {

    var trees = mutableStateOf(emptyList<Tree>())
    var tree = mutableStateOf(Tree(0,"",202202042137))
    var dialogOpen by mutableStateOf(false)

    init {
        getTrees()
    }
    fun getTrees () = viewModelScope.launch {
        repo.getTreesFromRoom().collect(){ dbTrees ->
            trees.value = dbTrees
        }
    }


    fun getTree(workingTree : Tree) = viewModelScope.launch {
        tree.value = workingTree
    }

    fun passTree() : Tree {
        return tree.value
    }

    fun deleteTree(tree : Tree) = viewModelScope.launch(Dispatchers.IO) {
        repo.deleteTreeFromRoom(tree)
    }

    fun closeDialog () {
        dialogOpen = false
    }
    fun openDialog (){
        dialogOpen = true
    }
}