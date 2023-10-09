package com.example.examine_ai.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Consulta(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val paciente: MutableList<Paciente>,
    val medico: MutableList<Medico>,
    val data: Date,

    )