package com.example.taskauto.model.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters


@Entity(
    tableName = "models"
)
data class ModelEntity(

    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @ColumnInfo(name = "man_name")
    var manName: String?,

    @TypeConverters(ModelsConverter::class)
    @ColumnInfo(name = "model_names")
    var modelNames: List<String> = ArrayList()
)




