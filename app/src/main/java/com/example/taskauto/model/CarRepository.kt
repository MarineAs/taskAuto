package com.example.taskauto.model

import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.taskauto.model.room.*

class CarRepository(
    context: Context,
    db: CarDatabase = CarDatabase.getInstance(context)
) {
    private val carDao: CarDao?
    private val carList: LiveData<List<CarEntity>>?
    private val modelDao: ModelDao?
    private val modelList: LiveData<List<ModelEntity>>?

    init {
        carDao = db.carDao()
        carList = carDao?.select()
        modelDao = db.modelDao()
        modelList = modelDao?.selectAll()
    }

    fun insert(data: CarEntity) {
        InsertCarAsyncTask(carDao).execute(data)
    }

    fun update(data: CarEntity) {
        UpdateCarAsyncTask(carDao).execute(data)
    }

    fun delete(id: Int) {
        DeleteCarAsyncTask(carDao).execute(id)
    }

    fun getAllCarList(): LiveData<List<CarEntity>>? {
        return this.carList
    }

    fun getAllModelList(): LiveData<List<ModelEntity>>? {
        return this.modelList
    }

    fun getModelManName(manName: String): LiveData<List<String>>? {
        return modelDao?.selectByManName(manName)
    }

    companion object {

        fun getInstance(context: Context): CarRepository {
            return CarRepository(context)
        }

        class InsertCarAsyncTask(dataDao: CarDao?) : AsyncTask<CarEntity, Unit, Unit>() {
            private var carDao: CarDao? = dataDao
            override fun doInBackground(vararg params: CarEntity?) {
                carDao?.insert(params[0])
            }
        }

        class UpdateCarAsyncTask(dataDao: CarDao?) : AsyncTask<CarEntity, Unit, Unit>() {
            private var carDao: CarDao? = dataDao
            override fun doInBackground(vararg params: CarEntity?) {
                carDao?.update(params[0])
            }
        }

        class DeleteCarAsyncTask(private var carDao: CarDao?) : AsyncTask<Int, Unit, Unit>() {
            override fun doInBackground(vararg params: Int?) {
                carDao?.deleteById(params[0])
            }
        }
    }

}
