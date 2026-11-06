package com.sliver.leonida.ui.views.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * Le bord déchiré qui coiffe le panneau de contenu ("beaucoup de triangles
 * reliés les uns aux autres" — c'était bien ça).
 *
 * Coordonnées reprises exactement du tracé SVG d'origine (viewBox 0 0 390 22,
 * preserveAspectRatio="none"), converties en proportions pour s'étirer sur
 * n'importe quelle largeur. Pas de bruit procédural ici, volontairement :
 * ce tracé a été dessiné à la main, un bruit aléatoire donnerait un rythme
 * différent — et c'est précisément ce qui a raté sur l'origami.
 *
 * Hauteur d'origine : 22dp. La forme remplit le bas ; seul le bord haut est
 * irrégulier.
 */
val DeckleEdgeShape = GenericShape { size, _ ->
    val w = size.width
    val h = size.height

    moveTo(0f, h)
    lineTo(0f, h * 0.63636f)
            lineTo(w * 0.02051f, h * 0.56818f)
            lineTo(w * 0.04103f, h * 0.64545f)
            lineTo(w * 0.06154f, h * 0.53636f)
            lineTo(w * 0.08205f, h * 0.61364f)
            lineTo(w * 0.10256f, h * 0.46364f)
            lineTo(w * 0.12308f, h * 0.62727f)
            lineTo(w * 0.14872f, h * 0.50909f)
            lineTo(w * 0.17436f, h * 0.63636f)
            lineTo(w * 0.19487f, h * 0.47727f)
            lineTo(w * 0.22051f, h * 0.59091f)
            lineTo(w * 0.24103f, h * 0.44545f)
            lineTo(w * 0.26667f, h * 0.57273f)
            lineTo(w * 0.29231f, h * 0.45909f)
            lineTo(w * 0.31795f, h * 0.60455f)
            lineTo(w * 0.34359f, h * 0.49091f)
            lineTo(w * 0.36923f, h * 0.58636f)
            lineTo(w * 0.39487f, h * 0.43182f)
            lineTo(w * 0.42051f, h * 0.60000f)
            lineTo(w * 0.44615f, h * 0.50000f)
            lineTo(w * 0.47179f, h * 0.65909f)
            lineTo(w * 0.49744f, h * 0.46818f)
            lineTo(w * 0.52308f, h * 0.58182f)
            lineTo(w * 0.54872f, h * 0.45000f)
            lineTo(w * 0.57436f, h * 0.61818f)
            lineTo(w * 0.60000f, h * 0.51364f)
            lineTo(w * 0.62564f, h * 0.57727f)
            lineTo(w * 0.65128f, h * 0.45455f)
            lineTo(w * 0.67692f, h * 0.60909f)
            lineTo(w * 0.70256f, h * 0.50455f)
            lineTo(w * 0.72821f, h * 0.55909f)
            lineTo(w * 0.75385f, h * 0.44091f)
            lineTo(w * 0.77949f, h * 0.59091f)
            lineTo(w * 0.80513f, h * 0.48182f)
            lineTo(w * 0.83077f, h * 0.58636f)
            lineTo(w * 0.85641f, h * 0.51818f)
            lineTo(w * 0.88205f, h * 0.59545f)
            lineTo(w * 0.90769f, h * 0.49545f)
            lineTo(w * 0.93333f, h * 0.56818f)
            lineTo(w * 0.95897f, h * 0.50000f)
            lineTo(w * 0.98462f, h * 0.60000f)
            lineTo(w * 1.00000f, h * 0.54545f)
    lineTo(w, h)
    close()
}

/**
 * Bandeau prêt à l'emploi — à placer juste au-dessus du panneau de contenu,
 * avec un décalage de -1dp pour éviter un liseré de fond entre les deux.
 */
@Composable
fun DeckleEdge(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.surface
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(22.dp)
            .clip(DeckleEdgeShape)
            .background(color)
    )
}
