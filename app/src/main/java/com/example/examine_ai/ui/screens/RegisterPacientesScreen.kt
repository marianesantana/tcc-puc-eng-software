package com.example.examine_ai.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.examine_ai.data.model.Paciente
import com.example.examine_ai.ui.presentation.pacientes.PacienteViewModel

@Composable
fun RegisterPacientesScreen(
    myViewModel: PacienteViewModel = viewModel(),
    navController: NavController
) {
    Log.d("DEBUG", "ViewModel em RegisterPacientesScreen:, $myViewModel")

    var nome by remember { mutableStateOf("") }
    var dataNascimento by remember { mutableStateOf("") }
    var sexo by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Cadastro de Paciente", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = nome,
            onValueChange = {
                nome = it
            },
            label = { Text("Nome") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = dataNascimento,
            onValueChange = { dataNascimento = it },
            label = { Text("Data de Nascimento (DD/MM/AAAA)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = sexo,
            onValueChange = { sexo = it },
            label = { Text("Sexo (M/F)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            val paciente = Paciente(
                nome = nome,
                dataNascimento = dataNascimento,
                sexo = sexo,
                historicoMedico = mutableListOf(),
                medicamentos = mutableListOf(),
                consultas = mutableListOf())
            myViewModel.insert(paciente)
            navController.navigate("home")
        }) {
            Text("Cadastrar")
        }
    }
}
