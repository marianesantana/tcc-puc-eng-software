package com.example.examine_ai.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Exame(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val tipo: TipoExame?,
    val data: Date,
    val diagnostico: MutableList<Diagnostico?>? = null,
    val imagem: String,
    val medicoResponsavel: MutableList<Medico>? = null,
)

data class ExameList(val exames: MutableList<Exame>)
