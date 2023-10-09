package com.example.examine_ai.domain.repository

import androidx.lifecycle.LiveData
import com.example.examine_ai.data.model.Exame
import com.example.examine_ai.data.model.Paciente

interface ExamesRepository {
    fun getExames(exame: Exame): LiveData<MutableList<Exame>>

    suspend fun insertExames(exame: Exame)

}