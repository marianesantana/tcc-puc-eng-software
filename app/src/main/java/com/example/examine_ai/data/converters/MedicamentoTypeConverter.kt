package com.example.examine_ai.data.converters

import androidx.room.TypeConverter
import com.example.examine_ai.data.model.HistoricoMedico
import com.example.examine_ai.data.model.Medicamento
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MedicamentoTypeConverter {
    @TypeConverter
    fun fromMedicamentoList(medicamentos: MutableList<Medicamento>): String {
        val type = object : TypeToken<MutableList<Medicamento>>() {}.type
        return Gson().toJson(medicamentos, type)
    }

    @TypeConverter
    fun toMedicamentoList(medicamentosString: String): MutableList<Medicamento> {
        val type = object : TypeToken<MutableList<Medicamento>>() {}.type
        return Gson().fromJson(medicamentosString, type)
    }
}