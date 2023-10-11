package com.example.examine_ai.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.examine_ai.R

@Preview
@Composable
fun SuccessComponent(){
    Column(verticalArrangement = Arrangement.SpaceAround,
    horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(304.dp)
            .height(343.dp)
            .background(
                color = Color(0xFFFFFFFF),
                shape = RoundedCornerShape(size = 15.dp)
            ),
    ){

        SvgImage(resId = R.drawable.checkmarkcircle)

        Text(
            text = "Sucesso",
            style = TextStyle(
                fontSize = 28.sp,
                fontFamily = FontFamily(Font(R.font.poppinsregular)),
                fontWeight = FontWeight(600),
                color = Color(0xFF2C774E),
            )
        )
        Text(
            text = "Deu certo!",
            style = TextStyle(
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.poppinsregular)),
                fontWeight = FontWeight(400),
                color = Color(0xFF2C774E),
            )
        )

        Button(onClick = { /*TODO*/ },
            Modifier
                .width(184.dp)
                .height(35.dp)
                .background(
                    color = Color(0xFF2C774E),
                    shape = RoundedCornerShape(size = 20.dp)
                )) {
            Text(
                text = "continue",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.poppinsregular)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFFFFFFFF),
                )
            )

            
        }


        }
}