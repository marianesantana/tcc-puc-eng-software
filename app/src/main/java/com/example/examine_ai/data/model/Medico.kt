package com.example.examine_ai.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Medico(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
//    val userInfos: User,
    val nome: String,
//    val credenciamento: String,
//    val diagnosticosEmitidos: MutableList<Diagnostico?>,
//    val prescricoes: MutableList<Medicamento>
) {
}