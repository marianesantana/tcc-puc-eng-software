package com.example.examine_ai.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Exame(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val tipo: MutableList<TipoExame>,
    val data: Date,
    val diagnostico: MutableList<Diagnostico?>,
    val imagem: String,
    val medicoResponsavel: MutableList<Medico>,
)