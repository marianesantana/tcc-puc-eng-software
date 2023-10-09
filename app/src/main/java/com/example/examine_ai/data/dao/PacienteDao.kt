package com.example.examine_ai.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.examine_ai.data.model.Paciente

@Dao
interface PacienteDao {
    @Query("SELECT * FROM paciente")
    fun getAllPacientes(): LiveData<MutableList<Paciente>>

    @Insert
    suspend fun insert(paciente: Paciente)

    @Update
    suspend fun update(paciente: Paciente)

    @Delete
    suspend fun delete(paciente: Paciente)
}