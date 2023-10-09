package com.example.examine_ai.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Paciente(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val nome: String,
    val dataNascimento: String,
    val sexo: String,
    val historicoMedico: MutableList<HistoricoMedico>,
    val medicamentos: MutableList<Medicamento>,
    val consultas: MutableList<Consulta>
)