package com.kkp.berryfarmer.presentation.start_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kkp.berryfarmer.domain.model.Berry
import com.kkp.berryfarmer.domain.model.Tree
import com.kkp.berryfarmer.domain.repository.BerryRepository
import com.kkp.berryfarmer.domain.repository.TreeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class StartScreenViewModel @Inject constructor(
    private val berryRepo : BerryRepository,
    private val treeRepo : TreeRepository
) : ViewModel() {
    var trees = mutableStateOf(emptyList<Tree>())
    var berries = mutableStateOf(emptyList<Berry>())


    init {
        getRepos()
    }

    fun getRepos(){
        viewModelScope.launch {
            treeRepo.getTreesFromRoom().collect(){ dbTrees ->
                trees.value += dbTrees
            }
            berryRepo.getBerriesFromRoom().collect(){ dbBerries ->
                berries.value += dbBerries
            }
        }
    }
}