package com.example.taskauto.model.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ModelDao {
    @Query("SELECT * FROM models ")
    fun selectAll(): LiveData<List<ModelEntity>>

    @Query("SELECT model_names FROM models WHERE `man_name`= :manName ")
    fun selectByManName(manName:String): LiveData<List<String>>

    @Insert
    fun insert(data: ModelEntity?)

    @Insert
    fun insertAll(vararg data: ModelEntity?)
}
