package com.example.examine_ai.data.converters

import androidx.room.TypeConverter
import com.example.examine_ai.data.model.Consulta
import com.example.examine_ai.data.model.HistoricoMedico
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ConsultaTypeConverter {
    @TypeConverter
    fun fromConsultaList(consultas: MutableList<Consulta>): String {
        val type = object : TypeToken<MutableList<Consulta>>() {}.type
        return Gson().toJson(consultas, type)
    }

    @TypeConverter
    fun toConsultaList(consultasString: String): MutableList<Consulta> {
        val type = object : TypeToken<MutableList<Consulta>>() {}.type
        return Gson().fromJson(consultasString, type)
    }
}