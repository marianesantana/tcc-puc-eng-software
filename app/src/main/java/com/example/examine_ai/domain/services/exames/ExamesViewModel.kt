package com.example.examine_ai.domain.services.exames

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.examine_ai.data.AppDatabase
import com.example.examine_ai.data.model.Exame
import com.example.examine_ai.data.repository.ExamesRepositoryImpl
import com.example.examine_ai.domain.repository.ExamesRepository
import kotlinx.coroutines.launch

class ExamesViewModel(application: Application) : AndroidViewModel(application) {
    private var exameDao = AppDatabase.getDatabase(application).exameDao()
    val allExames: LiveData<MutableList<Exame>>
    private val repository: ExamesRepository = ExamesRepositoryImpl(exameDao)
    private val _exameId = MutableLiveData<Int>()
    private val _exame = MutableLiveData<Exame>()
//    val exame: LiveData<Exame>



    val exameId: MutableLiveData<Int>
        get() = _exameId


    init {
        val database = AppDatabase.getDatabase(application)
        exameDao = database.exameDao()
        allExames = exameDao.getAllExames()

    }
    fun insert(exame: Exame) = viewModelScope.launch {
        repository.insertExames(exame)
    }

//    fun getExameById(id: Int) {
//        viewModelScope.launch {
//            val detalhesExame = exameDao.getExameById(id)
//            _exameId.value = id
//        }
//    }

    fun getExameById(id: Int): Exame? {
        return allExames.value?.find { it.id == id }
    }

}

