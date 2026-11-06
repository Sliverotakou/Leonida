package com.sliver.leonida.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.sliver.leonida.ui.states.HomeScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.core.annotation.KoinViewModel

@KoinViewModel
class HomeScreenViewmodel: ViewModel() {
    private val _state = MutableStateFlow(HomeScreenState())
    val state = _state.asStateFlow()
}