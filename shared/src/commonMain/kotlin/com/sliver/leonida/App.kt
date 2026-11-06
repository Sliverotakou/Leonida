@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)

package com.sliver.leonida

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.sliver.leonida.ui.theme.LeonidaTheme
import com.sliver.leonida.ui.views.navigation.LeonidaNavigation

@Composable
@Preview
fun App() {
    LeonidaTheme {
        LeonidaNavigation()
    }
}

