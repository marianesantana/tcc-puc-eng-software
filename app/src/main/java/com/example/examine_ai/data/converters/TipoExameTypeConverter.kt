package com.example.examine_ai.data.converters

import androidx.room.TypeConverter
import com.example.examine_ai.data.model.TipoExame
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TipoExameTypeConverter {
    @TypeConverter
    fun fromTipoExameList(tipoExame: TipoExame): String {
        val type = object : TypeToken<TipoExame?>() {}.type
        return Gson().toJson(tipoExame, type)
    }

    @TypeConverter
    fun toTipoExameList(tipoExameString: String): TipoExame {
        val type = object : TypeToken<TipoExame?>() {}.type
        return Gson().fromJson(tipoExameString, type)
    }
}