package com.avazpar.calendar.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


object CalendarColors {
    val FocusText = Color(0xFF4A4E69)
    val UnavailableBorder = Color(0xFFD4AF37)
    val FocusDoubleBorder = Color(0xFF1C1B33)
    val AvailableText = Color(0xFFD4AF37)
    val ActiveText = Color(0xFFF2F1EB)
    val UnavailableText = Color(0xFF4A4E69)
    val ActiveBackground = Color(0xFF1C1B33)
    val AvailableBackground = Color(0xFFD4AF37)
    val ActiveDot = Color(0xFFB6A1C2)
    val AvailableDot = Color(0xFFF2F1EB)
    val AvailableBorder = Color(0xFF1C1B33)
    val ui900 = Color(0xFFB6A1C2)
}

val Moonlight = Color(0xFFF2F1EB)
val StarDust = Color(0xFFD8CFC4)
val CosmicLilac = Color(0xFFB6A1C2)
val DeepSky = Color(0xFF4A4E69)
val GalaxyBlue = Color(0xFF1C1B33)
val SoftSand = Color(0xFFFFF8F2)
val CelestialGold = Color(0xFFD4AF37)
val MistyRose = Color(0xFFF5E1DC)
val NebulaPink = Color(0xFFE7B5C8)


private val LightColorScheme = lightColorScheme(
    primary = DeepSky,
    onPrimary = Color.White,
    secondary = CosmicLilac,
    onSecondary = Color.White,
    background = SoftSand,
    onBackground = GalaxyBlue,
    surface = Moonlight,
    onSurface = GalaxyBlue,
    tertiary = NebulaPink,
    onTertiary = Color.Black
)

private val DarkColorScheme = darkColorScheme(
    primary = StarDust,
    onPrimary = GalaxyBlue,
    secondary = MistyRose,
    onSecondary = GalaxyBlue,
    background = GalaxyBlue,
    onBackground = SoftSand,
    surface = DeepSky,
    onSurface = SoftSand,
    tertiary = CelestialGold,
    onTertiary = Color.Black
)

object Spacing {
    val normal = 16.dp
    val intermediate = 12.dp
    val small = 8.dp
    val xSmall = 4.dp
    val xxSmall = 2.dp
}

@Composable
fun CalendarTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}