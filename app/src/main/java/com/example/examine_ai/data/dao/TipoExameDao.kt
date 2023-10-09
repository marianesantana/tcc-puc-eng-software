package com.example.examine_ai.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.examine_ai.data.model.TipoExame

@Dao
interface TipoExameDao {
    @Query("SELECT * FROM TipoExame")
    fun getAll(): List<TipoExame>

    @Insert
    fun insertAll(vararg tiposExame: TipoExame)

}