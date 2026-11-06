package com.sliver.leonida.ui.views.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sliver.leonida.ui.views.shared.PolaroidContainer
import com.sliver.leonida.ui.views.shared.RoughStampContainer
import com.sliver.leonida.ui.views.shared.StampMetric
import leonida.shared.generated.resources.Res
import leonida.shared.generated.resources.compose_multiplatform
import leonida.shared.generated.resources.explore_24dp_e3e3e3_fill0_wght400_grad0_opsz24
import leonida.shared.generated.resources.newspaper_24dp_e3e3e3_fill0_wght400_grad0_opsz24
import leonida.shared.generated.resources.note_stack_24dp_e3e3e3_fill0_wght400_grad0_opsz24
import org.jetbrains.compose.resources.painterResource

@Composable
fun NavButtonDialog(
    modifier: Modifier,
    currentRoute: String,
    routes: List<Route>,
    navigateTo: (String) -> Unit
) {
    val unselectedColor =
        Pair(MaterialTheme.colorScheme.inverseSurface, MaterialTheme.colorScheme.surface)
    val selectedColor =
        Pair(MaterialTheme.colorScheme.primaryContainer, MaterialTheme.colorScheme.inverseSurface)

    val iconsRes = listOf(
        Res.drawable.newspaper_24dp_e3e3e3_fill0_wght400_grad0_opsz24,
        Res.drawable.explore_24dp_e3e3e3_fill0_wght400_grad0_opsz24,
        Res.drawable.note_stack_24dp_e3e3e3_fill0_wght400_grad0_opsz24
    )

    PolaroidContainer(
        modifier = modifier
    ) {
        LazyColumn (
            verticalArrangement = Arrangement.spacedBy(14.dp),
            modifier = Modifier.padding(4.dp)
        ) {
            val orientationValue = 2.5f

            itemsIndexed(
                routes
            ){
                index, route ->

                val orientation = if ((index) % 2 == 0) {
                    -orientationValue
                } else {
                    orientationValue
                }

                RoughStampContainer(
                    seed = route.name.count(),
                    containerColor = if (currentRoute == route.name) selectedColor.first else unselectedColor.first,
                    modifier = Modifier
                        .rotate(orientation)
                        .height(height = StampMetric.Large.heightSize)
                        .width(160.dp)
                        .clickable {
                            navigateTo(route.name)
                        }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = route.name,
                            style = MaterialTheme.typography.bodyLargeEmphasized,
                            color = if (currentRoute == route.name) selectedColor.second else unselectedColor.second,
                            fontWeight = FontWeight.ExtraBold
                        )

                        Icon(
                            painter = painterResource(iconsRes.elementAt(index)),
                            tint = if (currentRoute == route.name) selectedColor.second else unselectedColor.second,
                            modifier = Modifier.size(32.dp),
                            contentDescription = null
                        )
                    }
                }

            }
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "ED. 06 - 1986",
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        fontWeight = FontWeight.Light,
                        style = MaterialTheme.typography.labelSmall
                    )
                    Spacer(modifier = Modifier.width(52.dp))
                    Box(
                        modifier = Modifier.background(MaterialTheme.colorScheme.tertiaryContainer.copy(0.3f), shape = CircleShape).size(16.dp)
                    )
                }
            }

        }
    }
}