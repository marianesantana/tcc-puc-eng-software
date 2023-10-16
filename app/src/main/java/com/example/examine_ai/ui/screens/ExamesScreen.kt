package com.example.examine_ai.ui.screens

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.OpenableColumns
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.examine_ai.data.model.Diagnostico
import com.example.examine_ai.data.model.Exame
import com.example.examine_ai.data.model.TipoExame
import com.example.examine_ai.domain.services.exames.ExamesViewModel
import com.example.examine_ai.ui.components.DatePickerComponent
import com.example.examine_ai.ui.components.ExameList
import com.example.examine_ai.ui.components.extractTextFromPdf
import java.util.Date

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ExamesScreen(examesViewModel: ExamesViewModel, navController: NavController){
    val exames: MutableList<Exame> by examesViewModel.allExames.observeAsState(mutableListOf())
    Log.d("Lista de exames", exames.toString())
    var isAddingExame by remember { mutableStateOf(false) }

    val tipoExames = mutableListOf(
        TipoExame(1, "Raio-X"),
        TipoExame(2, "Exame de Sangue"),
        TipoExame(3, "Ressonância Magnética"),
        TipoExame(4, "Ultrassom Pélvico"),
        TipoExame(5, "Exame de Urina"),
        TipoExame(6, "Exame Ginecológico"),
        TipoExame(7, "Radiografia Toráxica"),
        TipoExame(8, "Mamografia"),

        )
    Log.d("tipoExame: ", tipoExames.toString())


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Exames") },
                backgroundColor = MaterialTheme.colors.primary,
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                if (isAddingExame) {
                    ExamesForm(onExameSubmit = {
                        examesViewModel.insert(it)
                        isAddingExame = false

                    }, tipoExames)

                }
                else {
                    ExameList(examesViewModel, navController)

                }

                FloatingActionButton(
                    onClick = { isAddingExame = true },
                    contentColor = Color.White,
//                    modifier = Modifier.align(alignment = Alignment.Horizontal)
                ) {
                    Icon(Icons.Filled.Add,
                        contentDescription = "Adicionar Exame")
                }
            }


        }

    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ExamesForm(onExameSubmit: (Exame) -> Unit, tipoExames: MutableList<TipoExame>
) {

    var tipoExameSelecionado by remember { mutableStateOf<TipoExame?>(null) }
    var dataExame by remember { mutableStateOf(Date()) }
    var diagnosticoExame by remember { mutableStateOf("") }

    var imagemExame by remember { mutableStateOf("") }
    var medicoResponsavel by remember { mutableStateOf("") }
    var isDropdownExpanded by remember { mutableStateOf(false) }

    var selectedFileUri by remember { mutableStateOf<Uri?>(null) }
    var fileName by remember { mutableStateOf("") }
    val context = LocalContext.current

    val pickPdfIntent = Intent(Intent.ACTION_GET_CONTENT).apply {
        type = "application/pdf"
    }

    val pickFileLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
    ){ result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val uri: Uri? = result.data?.data
            uri?.let {
                selectedFileUri = it
                fileName = getFileNameFromUri(context, it)
                val inputStream = context.contentResolver.openInputStream(it)
                if (inputStream != null) {
                    val inputStreamText = extractTextFromPdf(inputStream)
                    diagnosticoExame = inputStreamText
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.White),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "Selecione o Tipo de Exame: ")

        Text(
            text = tipoExameSelecionado?.nome?: tipoExames.first().nome,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .background(Color.White, RoundedCornerShape(8.dp))
                .fillMaxWidth(0.8f)
                .fillMaxHeight(0.12f)
                .border(1.dp, Color.LightGray, RoundedCornerShape(4.dp))
                .clickable(onClick = { isDropdownExpanded = true })
        )

        DropdownMenu(
            expanded = isDropdownExpanded,
            onDismissRequest = {
                isDropdownExpanded = false
            }
        ) {
            tipoExames.forEach { tipo ->
                DropdownMenuItem(onClick = {
                    tipoExameSelecionado = tipo
                    isDropdownExpanded = false
                }) {
                    Text(text = tipo.nome)

                }

            }
        }

        DatePickerComponent()


            OutlinedTextField(
                value = fileName,
                onValueChange = {
                    fileName = it
                },
                label = {
                    Text(
                        text = if (fileName.isEmpty()) "Selecione um arquivo" else fileName,
                        modifier = Modifier.weight(1f)
                    )
                },
                trailingIcon = {
                    Icon(Icons.Filled.Add,
                        contentDescription = "Upload de Exame",
                        modifier = Modifier.clickable {
                            pickFileLauncher.launch(pickPdfIntent)
                        })
                }

            )

        OutlinedTextField(
            value = medicoResponsavel,
            onValueChange = { medicoResponsavel = it },
            label = { Text("Médico Responsável") }
        )

        Button(
            onClick = {
                val newExame = Exame(
                    tipo = tipoExameSelecionado,
                    data = dataExame,
                    imagem = imagemExame,
                    diagnostico = mutableListOf(Diagnostico(descricao = diagnosticoExame))
                )
                Log.d("NOVO EXAME: ", newExame.toString())
                onExameSubmit(newExame)
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Adicionar Exame")
        }
    }
}

@SuppressLint("Range")
fun getFileNameFromUri(context: Context, uri: Uri): String {
    val cursor = context.contentResolver.query(uri, null, null, null, null)
    return cursor?.use {
        it.moveToFirst()
        val displayName = it.getString(it.getColumnIndex(OpenableColumns.DISPLAY_NAME))
        displayName ?: "Arquivo"
    } ?: "Arquivo"
}