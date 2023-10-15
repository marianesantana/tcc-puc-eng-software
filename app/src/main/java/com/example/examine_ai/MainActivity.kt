package com.example.examine_ai


import BottomMenu
import HomeScreen
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.examine_ai.domain.services.exames.ExamesViewModel
import com.example.examine_ai.domain.utils.Destinos
import com.example.examine_ai.ui.components.Spinner
import com.example.examine_ai.ui.presentation.pacientes.PacienteViewModel
import com.example.examine_ai.ui.presentation.users.AuthenticationState
import com.example.examine_ai.ui.presentation.users.PDFViewModel
import com.example.examine_ai.ui.presentation.users.UserViewModel
import com.example.examine_ai.ui.screens.ExameDetailScreen
import com.example.examine_ai.ui.screens.ExamesScreen
import com.example.examine_ai.ui.screens.LoginScreen
import com.example.examine_ai.ui.screens.NoLoggedHomeScreen
import com.example.examine_ai.ui.screens.PDFPreview
import com.example.examine_ai.ui.themes.AppTheme
import com.tom_roush.pdfbox.android.PDFBoxResourceLoader

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
    val pacientesViewModel: PacienteViewModel = viewModel()
    val pdfViewModel: PDFViewModel = viewModel()

    val loginResult by userViewModel.loginResult.collectAsState()
    Log.d("loginResult: ", loginResult.toString())


    val shouldShowMenu = shouldShowMenu(loginResult)

    if (shouldShowMenu) {
        Scaffold(
            bottomBar = {
                BottomMenu(navController = navController)
            }
        ) {
            NavHost(navController = navController, startDestination = "home") {
                composable("login") {
                    LoginScreen(userViewModel = userViewModel, navController = navController)

                }
                composable("home") { HomeScreen(navController) }
                composable("homeNL") { NoLoggedHomeScreen(userViewModel, navController) }
                composable("spinner") { Spinner(false, navController) }
//                composable("registerPaciente") { RegisterPacientesScreen(pacientesViewModel, navController) }
                composable("pdfPreview") { PDFPreview(pdfViewModel) }
                composable(Destinos.LISTA_EXAMES) { ExamesScreen(examesViewModel, navController) }
                composable(Destinos.DETALHES_EXAME, arguments = listOf(navArgument(Destinos.Argumentos.EXAME_ID) {
                    type = NavType.IntType
                })) { backStackEntry ->
                    val exameId = backStackEntry.arguments?.getInt(Destinos.Argumentos.EXAME_ID)
                    Log.d("id", exameId.toString())
                    exameId?.let {
                        ExameDetailScreen(it, examesViewModel)
                    }
                }


            }
        }
    } else {
        NavHost(navController = navController, startDestination = "login") {
            composable("login") {
                LoginScreen(navController = navController, userViewModel = userViewModel)
            }
            composable("homeNL") { NoLoggedHomeScreen(userViewModel, navController) }

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
        PDFBoxResourceLoader.init(applicationContext)
        setContent {
            AppTheme {
                App()
            }
        }
    }

}