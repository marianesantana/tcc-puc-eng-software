package com.example.examine_ai.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.examine_ai.R

@Preview
@Composable
fun ErrorScreen(){
    val image = painterResource(id = R.drawable.errorscreen)
    Image(painter = image, contentDescription = "AlgoDeuErrado" )
}