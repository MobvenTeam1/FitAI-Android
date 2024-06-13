package com.mobven.fitai.util

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mobven.fitai.data.model.response.Program

class Converters {
    @TypeConverter
    fun fromProgramList(value: Program): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toProgramList(value: String): Program {
        val listType = object : TypeToken<Program>() {}.type
        return Gson().fromJson(value, listType)
    }
}