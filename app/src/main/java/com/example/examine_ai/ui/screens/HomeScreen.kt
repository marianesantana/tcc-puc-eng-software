

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.examine_ai.R
import com.example.examine_ai.ui.components.UploadExame
import com.example.examine_ai.ui.themes.CustomColors

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController) {
//    val usersViewModel : UserViewModel = viewModel()
//    val userName by usersViewModel.userName.observeAsState("")

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Bem vinda, Mariane!", fontSize = 14.sp, textAlign = TextAlign.Left, color = CustomColors.surface, modifier = Modifier.padding(12.dp))

                },
                backgroundColor = MaterialTheme.colors.primary,
            )
    },
        content = {
            Box(modifier = Modifier
                .fillMaxSize()
                .background(CustomColors.surface),
                contentAlignment = Alignment.CenterStart,

            ){
                GreetingMessage()

                HomeContent(navController)
            }
        }
    )
}

@Composable
fun GreetingMessage() {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(35f)
        ,

    ) {


    }
}

@Composable
fun HomeContent(navController: NavController) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(36.dp)
            .border(1.dp, color = CustomColors.primary, shape = RoundedCornerShape(size = 15.dp))
            .background(Color.White, shape = RoundedCornerShape(size = 15.dp)),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.padding(20.dp))

        Text(
            text = "Deseja ler ou importar um exame?",
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.padding(20.dp))
        val painter = painterResource(id = R.drawable.homescreen)
        Image(
            painter = painter,
            modifier = Modifier.clip(shape = RoundedCornerShape(size = 15.dp)),
            contentDescription = "Mulher sorrindo ao ler um raio x"
        )
        UploadExame()

    }
}
