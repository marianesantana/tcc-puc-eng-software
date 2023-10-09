package com.example.examine_ai.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Diagnostico(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val descricao: String,
    val data: String,
    val exames: MutableList<Exame>,
    val paciente: MutableList<Paciente>,

)