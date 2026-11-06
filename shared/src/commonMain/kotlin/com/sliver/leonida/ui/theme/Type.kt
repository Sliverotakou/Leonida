package com.sliver.leonida.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import leonida.shared.generated.resources.InstrumentSans_VariableFont_wdth_wght
import leonida.shared.generated.resources.InstrumentSerif_Italic
import leonida.shared.generated.resources.InstrumentSerif_Regular
import leonida.shared.generated.resources.JetBrainsMono_VariableFont_wght
import leonida.shared.generated.resources.Res
import org.jetbrains.compose.resources.Font

@Composable
fun getLeonidaFontFamilies(): Triple<FontFamily, FontFamily, FontFamily> {
    // 1. Instrument Sans Variable - 1 fichier = tous les weights
    val bodyFontFamily = FontFamily(
        Font(Res.font.InstrumentSans_VariableFont_wdth_wght, FontWeight.Normal),
        Font(Res.font.InstrumentSans_VariableFont_wdth_wght, FontWeight.Medium),
        Font(Res.font.InstrumentSans_VariableFont_wdth_wght, FontWeight.SemiBold),
        Font(Res.font.InstrumentSans_VariableFont_wdth_wght, FontWeight.Bold)
    )

    // 2. Instrument Serif - 2 fichiers statiques
    val displayFontFamily = FontFamily(
        Font(Res.font.InstrumentSerif_Regular, FontWeight.Normal),
        Font(Res.font.InstrumentSerif_Italic, FontWeight.Normal)
    )

    // 3. JetBrains Mono Variable - pour la data
    val monoFontFamily = FontFamily(
        Font(Res.font.JetBrainsMono_VariableFont_wght, FontWeight.Normal),
        Font(Res.font.JetBrainsMono_VariableFont_wght, FontWeight.Medium),
        Font(Res.font.JetBrainsMono_VariableFont_wght, FontWeight.Bold)
    )

    return Triple(bodyFontFamily, displayFontFamily, monoFontFamily)
}

// On expose les 3 pour les utiliser hors Typography
lateinit var LeonidaBody: FontFamily
lateinit var LeonidaDisplay: FontFamily
lateinit var LeonidaMono: FontFamily

@Composable
fun getAppTypography(): Typography {
    val (body, display, mono) = getLeonidaFontFamilies()
    LeonidaBody = body
    LeonidaDisplay = display
    LeonidaMono = mono

    val baseline = Typography()
    // Version complète fidèle à ton export builder - 15 slots
    return Typography(
        displayLarge = baseline.displayLarge.copy(fontFamily = display),
        displayMedium = baseline.displayMedium.copy(fontFamily = display),
        displaySmall = baseline.displaySmall.copy(fontFamily = display),
        headlineLarge = baseline.headlineLarge.copy(fontFamily = display),
        headlineMedium = baseline.headlineMedium.copy(fontFamily = display),
        headlineSmall = baseline.headlineSmall.copy(fontFamily = display),
        titleLarge = baseline.titleLarge.copy(fontFamily = display),
        titleMedium = baseline.titleMedium.copy(fontFamily = display),
        titleSmall = baseline.titleSmall.copy(fontFamily = display),
        bodyLarge = baseline.bodyLarge.copy(fontFamily = body),
        bodyMedium = baseline.bodyMedium.copy(fontFamily = body),
        bodySmall = baseline.bodySmall.copy(fontFamily = body),
        labelLarge = baseline.labelLarge.copy(fontFamily = body),
        labelMedium = baseline.labelMedium.copy(fontFamily = body),
        // On force le mono uniquement sur labelSmall qui sert pour la data type "25.76° N"
        labelSmall = baseline.labelSmall.copy(fontFamily = mono)
    )
}

