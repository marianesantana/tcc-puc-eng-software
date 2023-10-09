package com.example.examine_ai.data.converters

import androidx.room.TypeConverter
import com.example.examine_ai.data.model.Medico
import com.example.examine_ai.data.model.TipoExame
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TipoExameTypeConverter {
    @TypeConverter
    fun fromTipoExameList(tipoExame: MutableList<TipoExame>): String {
        val type = object : TypeToken<MutableList<TipoExame>>() {}.type
        return Gson().toJson(tipoExame, type)
    }

    @TypeConverter
    fun toTipoExameList(tipoExameString: String): MutableList<TipoExame> {
        val type = object : TypeToken<MutableList<TipoExame>>() {}.type
        return Gson().fromJson(tipoExameString, type)
    }
}