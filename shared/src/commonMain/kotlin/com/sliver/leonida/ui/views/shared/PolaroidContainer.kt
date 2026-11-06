package com.sliver.leonida.ui.views.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


//Ne devrais pas avoir de limite de taille et laisser les enfants s exprimer comme il le souhaite
@Composable
fun PolaroidContainer(
    modifier: Modifier = Modifier,
    footer: @Composable () -> Unit = {},
    content: @Composable () -> Unit
) {
    Surface(
        modifier = modifier
    ) {
        Column (
            modifier = Modifier
                .padding(12.dp)
                .background(MaterialTheme.colorScheme.surface)
        ){
            content()
            footer()
        }
    }
}