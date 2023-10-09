package com.example.examine_ai.data.converters

import androidx.room.TypeConverter
import com.example.examine_ai.data.model.Medico
import com.example.examine_ai.data.model.Paciente
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MedicoTypeConverter {
    @TypeConverter
    fun fromMedicoList(medicos: MutableList<Medico>): String {
        val type = object : TypeToken<MutableList<Medico>>() {}.type
        return Gson().toJson(medicos, type)
    }

    @TypeConverter
    fun toMedicoList(medicosString: String): MutableList<Medico> {
        val type = object : TypeToken<MutableList<Medico>>() {}.type
        return Gson().fromJson(medicosString, type)
    }
}