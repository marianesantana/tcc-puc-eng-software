package com.example.examine_ai.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.examine_ai.data.model.Exame

@Dao
interface ExameDao {
    @Query("SELECT * FROM exame")
    fun getAllExames(): LiveData<MutableList<Exame>>

    @Insert
    suspend fun insert(exame: Exame)

}