

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.examine_ai.R
import com.example.examine_ai.ui.components.UploadExame
import com.example.examine_ai.ui.themes.CustomColors

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview
@Composable
fun HomeScreen() {

    Scaffold(
        content = {
            Box(modifier = Modifier
                .fillMaxSize()
                .background(CustomColors.surface)
            ){
                Text(text = "Bem vinda, Mariane!",
                    fontSize = 14.sp, textAlign = TextAlign.Left, color = CustomColors.primary, modifier = Modifier.padding(12.dp))
                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(36.dp)
                        .background(Color.White)
                ){

                    Text(text = "Deseja ler ou importar um exame?",
                        fontSize = 24.sp, textAlign = TextAlign.Center)
                    val painter = painterResource(id = R.drawable.homescreen)
                    Image(
                        painter = painter,
                        modifier = Modifier.fillMaxWidth()
                            .clip(CircleShape),
                        contentDescription = "Mulher sorrindo ao ler um raio x")

                    UploadExame()

                }


            }







    },
        topBar = {
            TopAppBar(title = { Text(text = "Exames")},
                backgroundColor = CustomColors.secondary)
        })

}