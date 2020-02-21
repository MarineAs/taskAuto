package com.example.taskauto.model

import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.taskauto.model.room.*
import java.util.concurrent.Callable
import java.util.concurrent.Executors

class CarRepository(
    context: Context,
    db: CarDatabase = CarDatabase.getInstance(context)
) {
    private val carDao: CarDao?
    private val carList: LiveData<List<CarEntity>>?
    private val modelDao: ModelDao?
    private val modelList: LiveData<List<ModelEntity>>?
    private var executors = Executors.newSingleThreadExecutor()

    init {
        carDao = db.carDao()
        carList = carDao?.select()
        modelDao = db.modelDao()
        modelList = modelDao?.selectAll()
    }

    fun insert(data: CarEntity) {
        executors.submit(InsertCar(carDao, data))
        executors.shutdown()
    }

    fun update(data: CarEntity) {
        executors.submit(UpdateCar(carDao, data))
        executors.shutdown()
    }

    fun delete(id: Int) {
        executors.submit(DeleteCar(carDao, id))
        executors.shutdown()
    }

    fun getAllCarList(): LiveData<List<CarEntity>>? {
        return this.carList
    }

    fun getAllModelList(): LiveData<List<ModelEntity>>? {
        return this.modelList
    }

    fun getModelsList(manName: String?): ModelEntity? {
       val data= executors.submit(GetModelsList(modelDao,manName)).get()
        executors.shutdown()
        return data
    }

    companion object {
        fun getInstance(context: Context): CarRepository {
            return CarRepository(context)
        }
    }

    class InsertCar(private var carDao: CarDao?, private var data: CarEntity) : Runnable {
        override fun run() {
            carDao?.insert(data)
        }
    }

    class UpdateCar(private var carDao: CarDao?, private var data: CarEntity) : Runnable {
        override fun run() {
            carDao?.update(data)
        }
    }

    class DeleteCar(private var carDao: CarDao?, private var data: Int) : Runnable {
        override fun run() {
            carDao?.deleteById(data)
        }
    }

    class GetModelsList(private var modelDao: ModelDao?, private var manName: String?) :
        Callable<ModelEntity> {
        override fun call(): ModelEntity? {
            return modelDao?.getStringManData(manName)
        }
    }
}
