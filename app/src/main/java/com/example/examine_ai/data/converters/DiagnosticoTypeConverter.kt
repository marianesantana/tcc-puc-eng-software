package com.example.examine_ai.data.converters

import androidx.room.TypeConverter
import com.example.examine_ai.data.model.Diagnostico
import com.example.examine_ai.data.model.HistoricoMedico
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DiagnosticoTypeConverter {
    @TypeConverter
    fun fromDiagnosticoList(diagnosticos: MutableList<Diagnostico?>): String {
        val type = object : TypeToken<MutableList<Diagnostico?>>() {}.type
        return Gson().toJson(diagnosticos, type)
    }

    @TypeConverter
    fun toDiagnosticoList(diagnosticosString: String): MutableList<Diagnostico?> {
        val type = object : TypeToken<MutableList<Diagnostico?>>() {}.type
        return Gson().fromJson(diagnosticosString, type)
    }
}