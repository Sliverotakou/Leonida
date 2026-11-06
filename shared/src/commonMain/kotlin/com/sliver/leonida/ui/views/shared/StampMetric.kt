package com.sliver.leonida.ui.views.shared

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

enum class StampMetric(
    val heightSize: Dp,
    val widthSize: Dp
) {
    Small(heightSize = 20.dp, widthSize = 34.dp),
    Medium(heightSize = 32.dp, widthSize = 54.dp),
    Large(heightSize = 58.dp , widthSize = 84.dp)
}