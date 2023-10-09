package com.example.examine_ai.data.converters

import androidx.room.TypeConverter
import com.example.examine_ai.data.model.HistoricoMedico
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class HistoricoMedicoTypeConverter {

    @TypeConverter
    fun fromHistoricoMedicoList(historicos: MutableList<HistoricoMedico>): String {
        val type = object : TypeToken<MutableList<HistoricoMedico>>() {}.type
        return Gson().toJson(historicos, type)
    }

    @TypeConverter
    fun toHistoricoMedicoList(historicosString: String): MutableList<HistoricoMedico> {
        val type = object : TypeToken<MutableList<HistoricoMedico>>() {}.type
        return Gson().fromJson(historicosString, type)
    }
}