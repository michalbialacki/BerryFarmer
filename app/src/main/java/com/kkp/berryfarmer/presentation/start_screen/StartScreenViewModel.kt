package com.kkp.berryfarmer.presentation.start_screen

import androidx.lifecycle.ViewModel
import com.kkp.berryfarmer.domain.repository.BerryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class StartScreenViewModel @Inject constructor(
    private val repo : BerryRepository
) : ViewModel() {
}