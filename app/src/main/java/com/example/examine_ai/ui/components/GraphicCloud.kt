package com.example.examine_ai.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.examine_ai.R

@Composable
fun GraphicCloud(){
    val image = painterResource(id = R.drawable.graphiccloud)
    Image(painter = image, contentDescription = "Nuvens coloridas", modifier = Modifier.fillMaxWidth())

}