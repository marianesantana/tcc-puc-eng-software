package com.example.examine_ai.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TipoExame(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val nome: String,        // Nome do tipo de exame (ex.: "Radiografia", "Exame de Sangue")
)