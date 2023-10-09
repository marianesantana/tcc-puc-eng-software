package com.example.examine_ai.ui.components

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.examine_ai.data.model.Diagnostico
import com.example.examine_ai.data.model.Exame
import com.example.examine_ai.data.model.Medico
import com.example.examine_ai.data.model.Paciente
import com.example.examine_ai.data.model.TipoExame
import com.example.examine_ai.ui.themes.CustomColors
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun ExameList(exames: MutableList<Exame>){
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)){
        items(exames) {
               Text(text = "Hello ${it.medicoResponsavel}")
        }
    }

}

@Composable
@Preview
fun ExamePreview(){
    val dateString = "Mon Oct 09 03:32:03 GMT-03:00 2023"
    val inputFormar = SimpleDateFormat("EEE MMM dd HH:mm:ss 'GMT'Z yyyy", Locale.US)
    val date = inputFormar.parse(dateString)
    val novoExame = mutableListOf(
        Exame(
        id = 0, // O ID será gerado automaticamente pelo Room com autoGenerate = true
        tipo = mutableListOf(TipoExame( 0, "Raio-X", "Imagem")),
        data = date,
        diagnostico = mutableListOf(),
        imagem = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fbrasilescola.uol.com.br%2Fo-que-e%2Ffisica%2Fo-que-sao-os-raios-x.htm&psig=AOvVaw34uo8l3U8nYhyoHaiIP6Lu&ust=1696920752459000&source=images&cd=vfe&ved=0CBEQjRxqFwoTCIj42Maw6IEDFQAAAAAdAAAAABAE",
        medicoResponsavel = mutableListOf(Medico(0, "Jose"))
    ), Exame(
    id = 1, // O ID será gerado automaticamente pelo Room com autoGenerate = true
    tipo = mutableListOf(TipoExame( 0, "Raio-X", "Imagem")),
    data = date,
    diagnostico = mutableListOf(),
    imagem = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fbrasilescola.uol.com.br%2Fo-que-e%2Ffisica%2Fo-que-sao-os-raios-x.htm&psig=AOvVaw34uo8l3U8nYhyoHaiIP6Lu&ust=1696920752459000&source=images&cd=vfe&ved=0CBEQjRxqFwoTCIj42Maw6IEDFQAAAAAdAAAAABAE",
    medicoResponsavel = mutableListOf(Medico(0, "Jose Maria"))
    ))
    ExameList(novoExame)


}