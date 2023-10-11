package com.example.examine_ai.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.examine_ai.data.model.Exame

@Composable
fun ExameList(exames: MutableList<Exame>){
    val navController = rememberNavController()
//                navController.navigate("${Destinos.DETALHES_EXAME}/${exame.id}")

    Column {
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(exames.size) { exame ->
                val index = exames[exame]
                ExameItem(index)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }




}
