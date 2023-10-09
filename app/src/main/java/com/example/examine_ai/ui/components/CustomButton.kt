package com.example.examine_ai.ui.components

import androidx.compose.foundation.background
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CustomButton(onClick: () -> Unit, label: String) {

    val colorStart = Color(0xFFCDFFD8)
    val colorEnd = Color(0xFF94B9FF)

    val gradient = Brush.linearGradient(
        colors = listOf(colorStart, colorEnd),
        start = Offset(1000f, 0f),
        end = Offset(0f, 1000f)
    )
    Button(onClick = onClick,
        modifier = Modifier.background(gradient)
    ) {
        Text(text = label)
    }
}