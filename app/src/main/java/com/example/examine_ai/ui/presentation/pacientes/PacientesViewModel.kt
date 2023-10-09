package com.example.examine_ai.ui.presentation.pacientes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.examine_ai.data.AppDatabase
import com.example.examine_ai.data.model.Paciente
import com.example.examine_ai.data.repository.PacienteRepositoryImpl
import kotlinx.coroutines.launch

class PacienteViewModel(application: Application) : AndroidViewModel(application) {
    private val pacientesDao = AppDatabase.getDatabase(application).pacienteDao()
    private val repository: PacienteRepositoryImpl = PacienteRepositoryImpl(pacientesDao)


    fun insert(paciente: Paciente) = viewModelScope.launch {
        repository.insertPaciente(paciente)
    }


}
