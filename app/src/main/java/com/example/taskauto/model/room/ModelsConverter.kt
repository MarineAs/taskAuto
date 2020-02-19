package com.example.taskauto.model.room

import androidx.room.TypeConverter

class ModelsConverter {

    @TypeConverter
    fun toListOfStrings(flatStringList: String): List<String> {
        return flatStringList.split(",")
    }

    @TypeConverter
    fun fromListOfStrings(listOfString: List<String>): String {
        return listOfString.joinToString(",")
    }
}
