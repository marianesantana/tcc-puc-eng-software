package com.example.examine_ai.ui.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.examine_ai.R
import com.example.examine_ai.data.model.Exame
import com.example.examine_ai.domain.services.exames.ExamesViewModel
import com.example.examine_ai.domain.utils.dateFormatter

@Composable
fun ExameItem(exame: Exame, navController: NavController){
    val examesViewModel: ExamesViewModel = viewModel()

    Log.d("Exame Data: ", exame.toString())

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                navController.navigate("exame/${exame.id}")
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.frameimage),
            contentDescription = "Imagem do Exame",
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colors.onSurface.copy(alpha = 0.2f))
        )

        Spacer(modifier = Modifier.width(16.dp))


        Column {
            Text(text = "Exame: ", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text(text = "${exame.tipo?.nome}", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(dateFormatter(exame.data), fontWeight = FontWeight.Bold, fontSize = 16.sp)
//            Text(exame.medicoResponsavel, fontWeight = FontWeight.Bold, fontSize = 16.sp)

//            Text("${exame?.diagnostico?.get(0)?.descricao}", fontWeight = FontWeight.Bold, fontSize = 16.sp)

        }
    }
}

