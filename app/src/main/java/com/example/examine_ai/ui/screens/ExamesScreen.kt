package com.example.examine_ai.ui.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.examine_ai.data.model.Exame
import com.example.examine_ai.data.model.Medico
import com.example.examine_ai.data.model.TipoExame
import com.example.examine_ai.domain.services.exames.ExamesViewModel
import com.example.examine_ai.ui.components.DatePickerComponent
import com.example.examine_ai.ui.components.ExameList
import com.example.examine_ai.ui.themes.CustomColors
import java.util.Date

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ExamesScreen(examesViewModel: ExamesViewModel){
    val exames: MutableList<Exame> by examesViewModel.allExames.observeAsState(mutableListOf())
    Log.d("Lista de exames", exames.toString())
    var isAddingExame by remember { mutableStateOf(false) }


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Exames") },
                backgroundColor = MaterialTheme.colors.primary,
            )
        },
        content = {
            Column(
                modifier = Modifier.padding(16.dp).background(CustomColors.surface).fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                if (isAddingExame) {
                    ExamesForm(onExameSubmit = {
                        examesViewModel.insert(it)
                        isAddingExame = false

                    })

                }
                ExameList(exames = exames)

                Button(
                    onClick = { isAddingExame = true },
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(text = "Adicionar Exame")
                }
            }


        }

    )
}

@Composable
fun ExamesForm(onExameSubmit: (Exame) -> Unit
){

    var tipoExame by remember { mutableStateOf("") }
    var dataExame by remember { mutableStateOf(Date()) }
    var imagemExame by remember { mutableStateOf("") }
    var medicoResponsavel by remember { mutableStateOf("") }
    var dataSelecionada by remember { mutableStateOf(Date()) }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.White),
        verticalArrangement = Arrangement.spacedBy(16.dp)){

        OutlinedTextField(value = tipoExame, onValueChange = {
            tipoExame = it
        }, label = {
            Text(text = "Tipo de Exame")
        })

        DatePickerComponent(selectedDate = dataSelecionada)

        OutlinedTextField(
            value = imagemExame,
            onValueChange = { imagemExame = it },
            label = { Text("Imagem do Exame") }
        )

        OutlinedTextField(
            value = medicoResponsavel,
            onValueChange = { medicoResponsavel = it },
            label = { Text("Médico Responsável") }
        )

        Button(
            onClick = {
                val newExame = Exame(
                    id = 0,
                    tipo = mutableListOf(TipoExame(nome = tipoExame, descrição = "", id = 1)),
                    data = dataExame,
                    diagnostico = mutableListOf(),
                    imagem = imagemExame,
                    medicoResponsavel = mutableListOf(Medico(id = 0, nome = medicoResponsavel))
                )
                onExameSubmit(newExame)
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Adicionar Exame")
        }




    }


}