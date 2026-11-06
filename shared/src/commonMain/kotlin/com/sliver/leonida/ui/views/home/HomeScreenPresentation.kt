package com.sliver.leonida.ui.views.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sliver.leonida.ui.views.shared.roughStamp
import leonida.shared.generated.resources.Res
import leonida.shared.generated.resources.attach_money
import leonida.shared.generated.resources.mode_night
import org.jetbrains.compose.resources.painterResource

@Composable
fun HomeScreenPresentation(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.padding(top = 16.dp),
        ) {
            Text(
                text = "VICE BEACH • OBJECTIF IMMÉDIAT",
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(0.6f),
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "Tu veux faire\n" +
                        "quoi à Vice Beach?",
                style = MaterialTheme.typography.displayMedium
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HomeScreenCtaButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {},
                seed = 8,
                color = MaterialTheme.colorScheme.primaryContainer,
                action = "Me faire les 50k rapidement",
                icon = {
                    Icon(
                        painter = painterResource(Res.drawable.attach_money),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                }
            )
            HomeScreenCtaButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {},
                seed = 11,
                color = MaterialTheme.colorScheme.secondaryContainer,
                action = "Finir à 100%",
                icon = {
                    Icon(
                        painter = painterResource(Res.drawable.mode_night),
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp)
                            .rotate(-35f)
                    )
                }
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "PROGRESSION DE ZONE",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(0.5f),
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "9 / 14 SPOTS TROUVES",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(0.5f),
                    fontWeight = FontWeight.Bold
                )

            }

            val circleColor =
                Pair(MaterialTheme.colorScheme.tertiaryContainer, MaterialTheme.colorScheme.surface)

            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(12.dp),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
                progress = { 0.5f },
                gapSize = Int.MIN_VALUE.dp,
                drawStopIndicator = {
                    drawCircle(
                        color = circleColor.first,

                    )
                    drawCircle(
                        color = circleColor.second,
                        style = Stroke(width = 1.5.dp.toPx())
                    )
                }
            )
        }
    }
}

@Composable
private fun HomeScreenCtaButton(
    modifier: Modifier = Modifier,
    seed: Int = 0,
    onClick: () -> Unit,
    color: Color,
    action: String,
    icon: @Composable () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors().copy(containerColor = color)
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 4.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = action,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .roughStamp(
                        color = MaterialTheme.colorScheme.onSurface,
                        seed = seed
                    )
            ) {
                icon()
            }
        }
    }
}