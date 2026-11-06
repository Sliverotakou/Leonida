@file:OptIn(ExperimentalMaterial3Api::class)

package com.sliver.leonida.ui.views.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.sliver.leonida.ui.views.shared.roughStamp

/**
 * Le coin de page plié ("navigation pli" dans le fichier source) — pas de
 * bruit, pas de RoughStampShape ici : juste deux triangles plats superposés.
 * Technique différente du tampon rugueux
 */
@Composable
fun OrigamiNavButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // clip-path: polygon(0 0, 100% 0, 100% 100%, 18% 100%, 0 82%)
    val foldShape = remember {
        GenericShape { size, _ ->
            moveTo(0f, 0f)
            lineTo(size.width, 0f)
            lineTo(size.width, size.height)
            lineTo(size.width * 0.18f, size.height)
            lineTo(0f, size.height * 0.82f)
            close()
        }
    }

    // clip-path: polygon(100% 0, 0 100%, 100% 100%)
    val triangleShape = remember {
        GenericShape { size, _ ->
            moveTo(size.width, 0f)
            lineTo(0f, size.height)
            lineTo(size.width, size.height)
            close()
        }
    }



    Box(
        modifier = modifier
            .size(78.dp)
            .clip(foldShape)
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .clickable(onClick = onClick)
    ) {
        // ombre du triangle (dessous)
        Box(
            Modifier
                .align(Alignment.BottomEnd)
                .size(28.dp)
                .clip(triangleShape)
                .background(MaterialTheme.colorScheme.surfaceVariant)
        )
        // triangle clair (dessus), légèrement décalé pour l'effet de relief
        Box(
            Modifier
                .align(Alignment.BottomEnd)
                .offset((-1).dp, (-1).dp)
                .size(26.dp)
                .clip(triangleShape)
                .background(MaterialTheme.colorScheme.background)
        )
        // tampon "NAV"
        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(14.dp)
                .size(width = 34.dp, height = 20.dp)
                .rotate(-8f)
                .roughStamp(MaterialTheme.colorScheme.inverseSurface, seed = 100)
                .background(MaterialTheme.colorScheme.inverseSurface),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "NAV",
                color = MaterialTheme.colorScheme.surface,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold
            )
        }
        // les deux traits guides sous le tampon — décoratifs, pas de forme
        Box(
            Modifier
                .align(Alignment.TopStart)
                .offset(10.dp, 38.dp)
                .size(width = 32.dp, height = 2.dp)
                .rotate(-8f)
                .background(Color(0xFF1A1A16).copy(alpha = 0.2f))
        )
        Box(
            Modifier
                .align(Alignment.TopStart)
                .offset(11.dp, 42.dp)
                .size(width = 28.dp, height = 2.dp)
                .rotate(-8f)
                .background(Color(0xFF1A1A16).copy(alpha = 0.2f))
        )
    }
}
