package com.example.examine_ai.ui.themes

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.examine_ai.R

val customFontFamily = FontFamily(
    Font(R.font.poppinsregular),
)

val customTypography = Typography(
    defaultFontFamily = customFontFamily,
    h1 = TextStyle(
        fontFamily = customFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp
    )
)

val CustomColors = Colors(
    primary = Color(0xFF0097B2),
    primaryVariant = Color(0xFF004AAD),
    secondary = Color(0xFF5B6BF5),
    secondaryVariant = Color(0xFF14AE5C),
    background = Color(0xFFFFFFFF),
    surface = Color(0xFFF4F6FF),
    error = Color(0xFFB00020),
    onPrimary = Color.White,
    onSecondary = Color(0xFF545454),
    onBackground = Color.Black,
    onSurface = Color.Black,
    onError = Color.White,
    isLight = true
)

@Composable
fun AppTheme(content: @Composable () -> Unit) {

    MaterialTheme(
        colors = CustomColors,
        typography = customTypography,
        shapes = Shapes(),
        content = content,
    )
}
