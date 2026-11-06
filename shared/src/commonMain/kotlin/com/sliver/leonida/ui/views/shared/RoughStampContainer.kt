package com.sliver.leonida.ui.views.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color

@Composable
fun RoughStampContainer(
    modifier: Modifier = Modifier,
    seed: Int = 0,
    containerColor: Color = MaterialTheme.colorScheme.inverseSurface,
    containerOrientation: Float = 0f,
    content: @Composable () -> Unit

){
    Box(
        modifier = modifier
            .rotate(degrees = containerOrientation)
            .roughStamp(color = containerColor, seed = seed, samplesPerEdge = 80)
            .background(color = containerColor),
        contentAlignment = Alignment.Center
    ){
        content()
    }
}