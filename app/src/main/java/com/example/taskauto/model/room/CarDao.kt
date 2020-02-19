package com.example.taskauto.model.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CarDao {
    @Query("SELECT * from cars ")
    fun select(): LiveData<List<CarEntity>>?

    @Query("DELETE FROM cars  where id=:id")
    fun deleteById(id: Int?)

    @Insert
    fun insert(data: CarEntity?)

    @Update
    fun update(data: CarEntity?)
}

