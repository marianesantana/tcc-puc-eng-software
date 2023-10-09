import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable

import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.examine_ai.domain.services.exames.ExamesViewModel
import com.example.examine_ai.ui.screens.ExamesScreen
import com.example.examine_ai.ui.themes.CustomColors

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview
@Composable
fun HomeScreen() {
    val examesViewModel: ExamesViewModel = viewModel()

    Scaffold(
        content = {

            ExamesScreen(examesViewModel = examesViewModel)
    },
        topBar = {
            TopAppBar(title = { Text(text = "Exames")},
                backgroundColor = CustomColors.secondary)
        })

}