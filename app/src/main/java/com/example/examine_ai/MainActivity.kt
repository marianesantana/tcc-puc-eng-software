package com.example.examine_ai


import BottomMenu
import HomeScreen
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.examine_ai.domain.services.exames.ExamesViewModel
import com.example.examine_ai.ui.components.Spinner
import com.example.examine_ai.ui.presentation.users.AuthenticationState
import com.example.examine_ai.ui.presentation.users.UserViewModel
import com.example.examine_ai.ui.screens.ExamesScreen
import com.example.examine_ai.ui.screens.LoginScreen
import com.example.examine_ai.ui.screens.NoLoggedHomeScreen

import com.example.examine_ai.ui.themes.AppTheme

fun shouldShowMenu(loginResult: AuthenticationState?): Boolean {
    return when (loginResult) {
        is AuthenticationState.Authenticated -> true
        else -> false
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainNavigation() {

    val navController = rememberNavController()
    val userViewModel: UserViewModel = viewModel()
    val examesViewModel: ExamesViewModel = viewModel()

    val loginResult by userViewModel.loginResult.collectAsState()
    Log.d("loginResult: ", loginResult.toString())


    val shouldShowMenu = shouldShowMenu(loginResult)

    if (shouldShowMenu) {
        Log.d("shouldShow?: ", shouldShowMenu.toString())

        Scaffold(
            bottomBar = {
                BottomMenu(navController = navController)
            }
        ) {
            NavHost(navController = navController, startDestination = "home") {
                composable("login") {
                    LoginScreen(userViewModel = userViewModel, navController = navController)

                }
                composable("home") { HomeScreen() }
                composable("homeNL") { NoLoggedHomeScreen(userViewModel) }
                composable("spinner") { Spinner(false, navController) }
                composable("exames") { ExamesScreen(examesViewModel) }

            }
        }
    } else {
        Log.d("shouldn't show: ", shouldShowMenu.toString())

        NavHost(navController = navController, startDestination = "login") {
            composable("login") {
                LoginScreen(navController = navController, userViewModel = userViewModel)
            }
            composable("homeNL") { NoLoggedHomeScreen(userViewModel) }

        }
    }
}


@Composable
fun App() {
    MainNavigation()
}

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                App()
            }
        }
    }

}