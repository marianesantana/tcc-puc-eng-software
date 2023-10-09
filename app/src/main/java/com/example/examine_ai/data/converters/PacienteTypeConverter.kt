package com.example.examine_ai.data.converters

import androidx.room.TypeConverter
import com.example.examine_ai.data.model.Medicamento
import com.example.examine_ai.data.model.Paciente
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PacienteTypeConverter {
    @TypeConverter
    fun fromPacienteList(pacientes: MutableList<Paciente>): String {
        val type = object : TypeToken<MutableList<Paciente>>() {}.type
        return Gson().toJson(pacientes, type)
    }

    @TypeConverter
    fun toPacienteList(pacientesString: String): MutableList<Paciente> {
        val type = object : TypeToken<MutableList<Paciente>>() {}.type
        return Gson().fromJson(pacientesString, type)
    }
}