package com.example.taskauto.model.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*

@Dao
interface ModelDao {
    @Query("SELECT * FROM models ")
    fun selectAll(): LiveData<List<ModelEntity>>


    @Query("SELECT * FROM models WHERE man_name =:manName ")
    fun getStringManData(manName: String?): ModelEntity

    @Insert
    fun insert(data: ModelEntity?)

    @Insert
    fun insertAll( vararg data: ModelEntity?)
}
