package com.example.examine_ai.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Diagnostico(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val descricao: String ? = null,
    val data: String ? = null,
    val exames: MutableList<Exame>? = null,
    val paciente: MutableList<Paciente>? = null,

)