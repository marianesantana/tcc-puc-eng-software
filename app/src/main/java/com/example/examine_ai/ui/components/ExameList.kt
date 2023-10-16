package com.example.examine_ai.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.examine_ai.data.model.Exame
import com.example.examine_ai.domain.services.exames.ExamesViewModel

@Composable
fun ExameList(examesViewModel: ExamesViewModel, navController: NavController){
    val exames: MutableList<Exame> by examesViewModel.allExames.observeAsState(mutableListOf())

    Column {
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(exames.size) { exame ->
                val index = exames[exame]
                examesViewModel.getExameById(index.id)
                ExameItem(index, navController)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }




}
