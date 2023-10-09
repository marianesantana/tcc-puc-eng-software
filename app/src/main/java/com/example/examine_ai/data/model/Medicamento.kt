package com.example.examine_ai.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Medicamento(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val nome: String,
    val dose: String,
    val instrucoes: String
)
