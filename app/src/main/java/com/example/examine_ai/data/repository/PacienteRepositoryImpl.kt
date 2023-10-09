package com.example.examine_ai.data.repository

import androidx.lifecycle.LiveData
import com.example.examine_ai.data.dao.PacienteDao
import com.example.examine_ai.data.model.Paciente
import com.example.examine_ai.domain.repository.PacienteRepository

class PacienteRepositoryImpl(
    private val pacienteDao: PacienteDao,

): PacienteRepository {
    override fun getPacientes(): LiveData<MutableList<Paciente>> {
        return pacienteDao.getAllPacientes()
    }

    override suspend fun getPacienteById(id: Int): Paciente? {
        TODO("Not yet implemented")
    }

    override suspend fun insertPaciente(paciente: Paciente) {
        pacienteDao.insert(paciente)
    }

    override suspend fun deletePaciente(paciente: Paciente) {
        pacienteDao.delete(paciente)
    }
}