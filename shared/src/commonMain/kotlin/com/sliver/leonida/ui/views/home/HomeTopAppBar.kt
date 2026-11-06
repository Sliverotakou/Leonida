package com.sliver.leonida.ui.views.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import leonida.shared.generated.resources.Res
import leonida.shared.generated.resources._1986_ED_06
import leonida.shared.generated.resources.app_name
import org.jetbrains.compose.resources.stringResource

@Composable
fun HomeTopAppBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .weight(0.80f)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(Res.string.app_name),
                    style = MaterialTheme.typography.displayMedium
                )

                HorizontalDivider(
                    modifier = Modifier
                        .width(12.dp)
                        .padding(top = 12.dp),
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = stringResource(Res.string._1986_ED_06),
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(0.7f),
                    modifier = Modifier.padding(top = 12.dp)
                )
            }

            Text(
                text = "Carte touristique • Ne pas plier",
                style = MaterialTheme.typography.bodyLargeEmphasized,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(0.85f),
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.25f),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "65%",
                style = MaterialTheme.typography.displayMedium,
                fontWeight = FontWeight.Light
            )
            Text(
                text = "DÉCOUVERT",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.ExtraLight
            )
        }
    }
}