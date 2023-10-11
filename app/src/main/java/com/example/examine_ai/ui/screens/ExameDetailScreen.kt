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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.examine_ai.data.model.Exame
import com.example.examine_ai.domain.services.exames.ExamesViewModel
import com.example.examine_ai.ui.themes.CustomColors

@Composable
fun ExameDetailScreen(
    exameId: Int
) {
    val exameViewModel: ExamesViewModel = viewModel()

    val exame = exameViewModel.getExameById(exameId)

    exame?.let {
        Text(text = it.toString())
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
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .clip(CircleShape)
                        .background(CustomColors.secondary)
                ) {
                    GlideImage(
                        model = exame.imagem,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
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
                    color = CustomColors.onSecondary
                )
                Text(
                    text = exame.tipo.first().nome,
                    style = MaterialTheme.typography.body2,
                    color = CustomColors.onPrimary
                )
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                Text(
                    text = "Data do Exame:",
                    style = MaterialTheme.typography.body1,
                    color = CustomColors.onSecondary
                )
                Text(
                    text = exame.data.toString(),
                    style = MaterialTheme.typography.body2,
                    color = CustomColors.onPrimary
                )
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                Text(
                    text = "Médico Responsável:",
                    style = MaterialTheme.typography.body1,
                    color = CustomColors.onSecondary
                )
                Text(
                    text = exame.medicoResponsavel.first().nome,
                    style = MaterialTheme.typography.body2,
                    color = CustomColors.onPrimary
                )
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                Text(
                    text = "Diagnóstico:",
                    style = MaterialTheme.typography.body1,
                    color = CustomColors.onSecondary
                )
                exame.diagnostico.forEach { diagnostico ->
                    Text(
                        text = diagnostico?.descricao ?: "",
                        style = MaterialTheme.typography.body2,
                        color = CustomColors.onPrimary
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}
