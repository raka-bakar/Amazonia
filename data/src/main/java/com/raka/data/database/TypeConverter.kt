package com.raka.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class TypeConverter() {

    @TypeConverter
    fun fromJsonToList(json:String):List<String>?{
        val listType: Type = object : TypeToken<List<String?>?>() {}.type
        return Gson().fromJson(json,listType)
    }

    @TypeConverter
    fun fromListStringToJson(list: List<String>):String{
        return Gson().toJson(list)
    }
}