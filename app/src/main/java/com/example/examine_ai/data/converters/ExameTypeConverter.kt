package com.example.examine_ai.data.converters

import androidx.room.TypeConverter
import com.example.examine_ai.data.model.Diagnostico
import com.example.examine_ai.data.model.Exame
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ExameTypeConverter {
    @TypeConverter
    fun fromExameList(exames: MutableList<Exame>): String {
        val type = object : TypeToken<MutableList<Exame>>() {}.type
        return Gson().toJson(exames, type)
    }

    @TypeConverter
    fun toExameList(examesString: String): MutableList<Exame> {
        val type = object : TypeToken<MutableList<Exame>>() {}.type
        return Gson().fromJson(examesString, type)
    }
}