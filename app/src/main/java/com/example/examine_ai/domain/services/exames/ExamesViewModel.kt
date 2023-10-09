package com.example.examine_ai.domain.services.exames

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.examine_ai.data.AppDatabase
import com.example.examine_ai.data.dao.ExameDao
import com.example.examine_ai.data.model.Exame
import com.example.examine_ai.data.model.Paciente
import com.example.examine_ai.data.repository.ExamesRepositoryImpl
import com.example.examine_ai.data.repository.PacienteRepositoryImpl
import com.example.examine_ai.domain.repository.ExamesRepository
import kotlinx.coroutines.launch

class ExamesViewModel(application: Application) : AndroidViewModel(application) {
    private var exameDao = AppDatabase.getDatabase(application).exameDao()
    val allExames: LiveData<MutableList<Exame>>
    private val repository: ExamesRepository = ExamesRepositoryImpl(exameDao)


    init {
        val database = AppDatabase.getDatabase(application)
        exameDao = database.exameDao()
        allExames = exameDao.getAllExames()
    }
    fun insert(exame: Exame) = viewModelScope.launch {
        repository.insertExames(exame)
    }
}

