package com.example.examine_ai.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Receita(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val medicamentos: MutableList<Medicamento>
)