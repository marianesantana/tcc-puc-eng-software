package com.example.examine_ai.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.examine_ai.data.model.User
import com.example.examine_ai.ui.components.CustomButton
import com.example.examine_ai.ui.components.GraphicCloud
import com.example.examine_ai.ui.presentation.users.UserViewModel
import com.example.examine_ai.ui.themes.CustomColors
import com.example.examine_ai.ui.themes.customFontFamily

@Composable
fun NoLoggedHomeScreen(
    myViewModel: UserViewModel = viewModel()

){
    var email by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
//            .background(Color.White)
    ) {
        GraphicCloud()

        Text(
            text = "Criar Conta",
            fontFamily = customFontFamily,
            style = TextStyle(fontSize = 20.sp),
            color = CustomColors.primaryVariant,
        )
        Text(
            text = "Já tem uma conta? Acesse por aqui",
            fontFamily = customFontFamily,
            style = TextStyle(fontSize = 12.sp),
            color = CustomColors.primaryVariant,
        )
        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 0.dp, 0.dp, 0.dp),
            contentAlignment = Alignment.TopStart
        ) {
            Text(
                text = "Digite aqui seu nome",
                fontFamily = customFontFamily,
                style = TextStyle(fontSize = 11.sp),
                color = CustomColors.onSecondary,
            )
        }


        TextField(
            value = name,
            onValueChange = { name = it },
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .border(
                    1.dp, CustomColors.background,
                    RoundedCornerShape(20.dp)
                )
                .clip(RoundedCornerShape(20.dp)),
            label = {
                Text(text = "Nome")

            })
        Spacer(modifier = Modifier.height(8.dp))

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
            value = email,
            onValueChange = { value ->
                email = value
                emailError = !isValidEmail(value)
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
            onValueChange = { value ->
                password = value
                passwordError = value.length < 8
            },
            label = { Text("Senha") },
            visualTransformation = PasswordVisualTransformation(),
            isError = passwordError,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .border(
                    1.dp, CustomColors.background,
                    RoundedCornerShape(20.dp)
                )
                .clip(RoundedCornerShape(20.dp)),
        )
        

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth().background(Color.Transparent),
            horizontalArrangement = Arrangement.Center
        ) {
            CustomButton(onClick = {
                val user = User(username = email, password = password)
                Log.d("DEBUG", user.username)
                myViewModel.insert(user)

            }, label = "Cadastrar")

        }


    }

}
    fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

