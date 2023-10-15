package com.example.examine_ai.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.examine_ai.R
import com.example.examine_ai.data.model.Exame
import com.example.examine_ai.data.model.ExameResult
import com.example.examine_ai.domain.services.exames.ExamesViewModel
import com.example.examine_ai.domain.utils.dateFormatter
import com.example.examine_ai.ui.themes.CustomColors

@Composable
fun ExameDetailScreen(
    exameId: Int,
    examesViewModel: ExamesViewModel
) {
    val exame = examesViewModel.getExameById(exameId)


//    val exames: MutableList<Exame> by examesViewModel.allExames.observeAsState(mutableListOf())


    if (exame != null) {
        DetalhesExameContent(exame = exame)
    }

}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetalhesExameContent(exame: Exame) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            item {
                Box(
                    modifier = Modifier
                        .aspectRatio(1f)
                        .clip(CircleShape)
                        .background(CustomColors.secondary)
                ) {
                    GlideImage(
                        model = R.drawable.frameimage,
                        contentDescription = "Imagem do Exame",
                        contentScale = ContentScale.Crop,
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                Text(
                    text = "Tipo de Exame:",
                    style = MaterialTheme.typography.body1,
                    color = CustomColors.onSecondary,
                    fontWeight = FontWeight.Bold
                )
                exame.tipo?.nome?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.body2,
                        color = CustomColors.primary
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                Text(
                    text = "Data do Exame:",
                    style = MaterialTheme.typography.body1,
                    color = CustomColors.onSecondary,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = dateFormatter(exame.data),
                    style = MaterialTheme.typography.body2,
                    color = CustomColors.primary
                )
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                Text(
                    text = "Diagnóstico:",
                    style = MaterialTheme.typography.body1,
                    color = CustomColors.onSecondary,
                    fontWeight = FontWeight.Bold
                )
                exame.diagnostico?.forEach { diagnostico ->
                    val results = diagnostico?.descricao?.let { extractResultsFromText(it) }

                  Column {

                              results?.forEach { result ->
                                  val resultadoValue = interpretValue(result.resultado.split(" ")[0])
                                  val referenciaMin = interpretValue(result.referencia.split("a")[0].trim())
                                  val referenciaMax = interpretValue(result.referencia.split("a")[1].trim())

                                  val color = when {
                                      resultadoValue < referenciaMin || resultadoValue > referenciaMax -> Color.Red
                                      else -> CustomColors.secondaryVariant
                                  }
                                  Text(text = "${result.nome}: ${result.resultado} (Referência: ${result.referencia})", color = color)
                              }
                          }
                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

fun extractResultsFromText(text: String): List<ExameResult> {
    val pattern = """(.*?)(\d+,\d+.*?)(\d+,\d+\s?a\s?\d+,\d+|\d+\.\d+\s?-\s?\d+\.\d+)""".toRegex()
    val matches = pattern.findAll(text)

    return matches.map { matchResult ->
        ExameResult(
            nome = matchResult.groupValues[1].trim(),
            resultado = matchResult.groupValues[2].trim(),
            referencia = matchResult.groupValues[3].trim()
        )
    }.toList()
}

fun interpretValue(value: String): Double {
    return when {
        value.contains("10^6") -> {
            val numberPart = value.split(" ")[0].replace(',', '.').toDouble()
            numberPart * 1_000_000
        }
        else -> {
            value.replace(',', '.').toDouble()
        }
    }
}


fun checkWithinRange(value: Double, min: Double, max: Double): Boolean {
    return value in min..max
}




