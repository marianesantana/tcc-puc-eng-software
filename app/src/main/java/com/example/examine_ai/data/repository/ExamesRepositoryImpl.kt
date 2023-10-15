package com.example.examine_ai.data.repository

import androidx.lifecycle.LiveData
import com.example.examine_ai.data.dao.ExameDao
import com.example.examine_ai.data.model.Exame
import com.example.examine_ai.domain.repository.ExamesRepository

class ExamesRepositoryImpl(
    private val examesDao: ExameDao,
    ): ExamesRepository {
    override suspend fun getExames(exame: Exame): LiveData<MutableList<Exame>> {
        return examesDao.getAllExames()

    }

    override suspend fun insertExames(exame: Exame) {
        return examesDao.insert(exame)
    }

    override suspend fun getExameById(id: Int): Exame? {
        return examesDao.getExameById(id)
    }

}