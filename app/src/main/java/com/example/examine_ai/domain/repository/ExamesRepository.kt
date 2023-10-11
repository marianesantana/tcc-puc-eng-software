package com.example.examine_ai.domain.repository

import androidx.lifecycle.LiveData
import com.example.examine_ai.data.model.Exame

interface ExamesRepository {
    fun getExames(exame: Exame): LiveData<MutableList<Exame>>

    suspend fun insertExames(exame: Exame)
    suspend fun getExameById(id: Int): Exame?



}