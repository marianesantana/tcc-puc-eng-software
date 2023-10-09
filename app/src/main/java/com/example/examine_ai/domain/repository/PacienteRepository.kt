package com.example.examine_ai.domain.repository

import androidx.lifecycle.LiveData
import com.example.examine_ai.data.model.Paciente

interface PacienteRepository {
    fun getPacientes(): LiveData<MutableList<Paciente>>

    suspend fun getPacienteById(id: Int): Paciente?

    suspend fun insertPaciente(paciente: Paciente)

    suspend fun deletePaciente(paciente: Paciente)
}