package com.sliver.leonida.ui.views.shared

import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import kotlin.math.floor

/**
 * Reproduit .stamp-rough SANS shader — un seul Modifier/Shape, portable
 * partout (pas d'except/actual, pas de plancher Android 13).
 *
 * La version précédente jitterait des points au hasard reliés par des
 * lignes droites → un rendu anguleux, "papier froissé". Celle-ci simule
 * un vrai bruit continu (comme feTurbulence) en pur Kotlin : une fonction
 * de bruit lissée (hash + interpolation smoothstep, 2 octaves comme
 * numOctaves=2 dans le filtre d'origine), échantillonnée en continu tout
 * autour du périmètre, puis reliée par des courbes plutôt que des lignes.
 * Le calcul ne tourne qu'à la création du contour (mise en cache par
 * Compose tant que la taille ne change pas) — aucun coût par frame.
 */
class RoughStampShape(
    private val seed: Int = 0,
    private val amplitude: Dp = 3.2.dp,
    private val frequency: Float = 50.96f,
    private val samplesPerEdge: Int = 40 // old valu 40
) : Shape {

    private fun hash(i: Int): Float {
        var h = i * 374761393 + seed * 668265263
        h = (h xor (h shr 13)) * 1274126177
        h = h xor (h shr 16)
        return (h and 0x7fffffff) / Int.MAX_VALUE.toFloat()
    }

    private fun smoothNoise(x: Float): Float {
        val xi = floor(x).toInt()
        val xf = x - xi
        val u = xf * xf * (3f - 2f * xf) // lissage smoothstep — la clé de la continuité
        val a = hash(xi)
        val b = hash(xi + 1)
        return a + (b - a) * u
    }

    // bruit fractal à 2 octaves — même principe que numOctaves="2" en SVG :
    // une fréquence fine par-dessus une fréquence large, pour un aspect
    // organique plutôt qu'une simple vaguelette régulière.
    private fun fractalNoise(x: Float): Float {
        val n1 = smoothNoise(x)
        val n2 = smoothNoise(x * 2.13f + 50f) * 0.5f
        return (n1 + n2) / 1.5f - 0.5f
    }

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val amp = with(density) { amplitude.toPx() }
        val w = size.width
        val h = size.height

        fun jitter(t: Float) = fractalNoise(t * frequency) * amp

        // t parcourt tout le périmètre en continu : 0–1 haut, 1–2 droite,
        // 2–3 bas, 3–4 gauche — un seul bruit continu, pas un par bord.
        fun pointAt(t: Float): Offset {
            val edge = t.toInt().coerceIn(0, 3)
            val local = t - edge
            return when (edge) {
                0 -> Offset(w * local, jitter(t))
                1 -> Offset(w + jitter(t), h * local)
                2 -> Offset(w - w * local, h + jitter(t))
                else -> Offset(jitter(t), h - h * local)
            }
        }

        val totalSamples = samplesPerEdge * 4
        val path = Path()
        var prev = pointAt(0f)
        path.moveTo(prev.x, prev.y)

        // courbe lissée à travers les points échantillonnés (quadratiques
        // via les points milieux) plutôt que des segments droits — c'est
        // ce qui remplace l'aspect "zigzag" par une ondulation organique.
        for (i in 1..totalSamples) {
            val t = 4f * i / totalSamples
            val cur = pointAt(t)
            val mid = Offset((prev.x + cur.x) / 2f, (prev.y + cur.y) / 2f)
            path.quadraticTo(prev.x, prev.y, mid.x, mid.y)
            prev = cur
        }
        path.close()

        return Outline.Generic(path)
    }
}

/** Raccourci : forme + remplissage en un seul modifier. */
fun Modifier.roughStamp(color: Color, seed: Int = 0, samplesPerEdge: Int = 40): Modifier =
    this
        .clip(RoughStampShape(seed = seed, samplesPerEdge = samplesPerEdge ))
        .background(color)

// Usage :
// Box(
//     modifier = Modifier
//         .rotate(-2f)
//         .roughStamp(color = StampPeach, seed = 4)
//         .padding(horizontal = 14.dp, vertical = 10.dp)
// ) { Text("CARTE", fontFamily = bodyFontFamily) }
