package com.example.examine_ai.ui.screens

import android.util.Log
import android.widget.Spinner
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.examine_ai.data.model.Paciente
import com.example.examine_ai.data.model.User
import com.example.examine_ai.ui.components.CustomButton
import com.example.examine_ai.ui.components.GraphicCloud
import com.example.examine_ai.ui.components.Spinner
import com.example.examine_ai.ui.presentation.users.AuthenticationState
import com.example.examine_ai.ui.presentation.users.UserViewModel
import com.example.examine_ai.ui.themes.CustomColors
import com.example.examine_ai.ui.themes.customFontFamily

@Composable
fun LoginScreen(
    userViewModel: UserViewModel,
    navController: NavController,
){

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val loginResult by userViewModel.loginResult.collectAsState()


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        GraphicCloud()
        Text(text = "Login",
            fontFamily = customFontFamily,
            style = TextStyle(fontSize = 20.sp),
            color = CustomColors.primaryVariant,)

        Text(text = "Logue na sua conta para continuar",
            fontFamily = customFontFamily,
            style = TextStyle(fontSize = 12.sp),
            color = CustomColors.primaryVariant,)

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 0.dp, 0.dp, 0.dp),
            contentAlignment = Alignment.TopStart
        ) {
            Text(
                text = "Digite aqui seu e-mail",
                fontFamily = customFontFamily,
                style = TextStyle(fontSize = 11.sp),
                color = CustomColors.onSecondary,
            )
        }
        TextField(
            value = username,
            onValueChange = {
                username = it
            }, Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .border(
                    1.dp, CustomColors.background,
                    RoundedCornerShape(20.dp)
                )
                .clip(RoundedCornerShape(20.dp)),
            label = {
                Text(text = "E-mail")

            })
        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 0.dp, 0.dp, 0.dp),
            contentAlignment = Alignment.TopStart
        ) {
            Text(
                text = "Digite aqui sua senha",
                fontFamily = customFontFamily,
                style = TextStyle(fontSize = 11.sp),
                color = CustomColors.onSecondary,
            )
        }

        TextField(
            value = password,
            onValueChange = {
                password = it
            },
            label = { Text("Senha") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .border(
                    1.dp, CustomColors.background,
                    RoundedCornerShape(20.dp)
                )
                .clip(RoundedCornerShape(20.dp)))

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent),
            horizontalArrangement = Arrangement.Center
        ) {
            CustomButton(onClick = {
                userViewModel.login(username,password)
                when (loginResult) {
                    is AuthenticationState.Unauthenticated -> { navController.navigate("login") }
                    is AuthenticationState.Authenticating -> {
                        navController.navigate("spinner")
                    }
                    is AuthenticationState.Authenticated -> {
                        Log.d("Debug3", loginResult.toString())
                        navController.navigate("home") }
                    is AuthenticationState.AuthenticationFailed -> {
                        Log.d("Debug4", loginResult.toString())
                        Log.d("Debug4: details", username)
                        Log.d("Debug4: details2", password)


                        navController.navigate("homeNL") }
                    else -> {
                        Log.d("Debug", loginResult.toString())
                    }
                }
           }, label = "Logar")

        }
    }

}