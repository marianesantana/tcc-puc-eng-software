package com.example.examine_ai.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HistoricoMedico(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val exames: MutableList<Exame>,
    val diagnosticos: MutableList<Diagnostico?>,

)
