package com.example.examine_ai.data.converters

import androidx.room.TypeConverter
import java.util.Date

object DateTypeConverter {
    @TypeConverter
    @JvmStatic
    fun toDate(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    @JvmStatic
    fun toLong(date: Date?): Long? {
        return date?.time
    }
}