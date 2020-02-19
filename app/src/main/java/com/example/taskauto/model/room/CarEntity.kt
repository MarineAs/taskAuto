package com.example.taskauto.model.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "cars"
)
data class CarEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,

    @ColumnInfo(name = "man_name")
    var manName: String?,

    @ColumnInfo(name = "model_name")
    var modelName: String?,

    @ColumnInfo(name = "year")
    var year: Int?,

    @ColumnInfo(name = "price")
    var price: Int?
) {
    constructor():this(null,"","",0,0)
}