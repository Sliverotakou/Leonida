package com.sliver.leonida.ui.views.navigation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


// I use navigationManagerData as a structure for all
// current and other operation can apply on a navigation
// item like a dialog navigation or a origamiNavButton
data class NavigationManagerData(
    val showNavigationBottomBar: Boolean = true,
    val showNavigationDialog: Boolean = false
)

object NavigationManager {
    private val _state = MutableStateFlow(NavigationManagerData())
    val state = _state.asStateFlow()

    //using to hide or see a navigation dialog. it is use on a nav button origami
    fun updateNavigationDialogVisibility(isVisible: Boolean) {
        _state.update {
            it.copy(showNavigationDialog = isVisible)
        }
    }

    // I think I can use it to hide completely navigation bottom bar for a screen which don't need
    fun updateNavigationBottomBarVisibility(isVisible: Boolean) {
        _state.update {
            it.copy(showNavigationBottomBar = isVisible)
        }
    }
}