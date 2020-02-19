package com.example.taskauto.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.taskauto.model.CarRepository
import com.example.taskauto.model.room.CarEntity


class AddFragmentViewModel(
    application: Application
) :
    AndroidViewModel(application) {
    private var repository: CarRepository = CarRepository.getInstance(application.applicationContext)
    var carId: MutableLiveData<String> = MutableLiveData()
    var manName: MutableLiveData<String> = MutableLiveData()
    var modelName: MutableLiveData<String> = MutableLiveData()
    var year: MutableLiveData<String> = MutableLiveData()
    var price: MutableLiveData<String> = MutableLiveData()
    var modelList: List<String>? = ArrayList()
    var emptyError: MutableLiveData<String>? = MutableLiveData()
    var selectError: MutableLiveData<String>? = MutableLiveData()
    var clickedFragment: MutableLiveData<String> = MutableLiveData()
    var backstack: MutableLiveData<Boolean> = MutableLiveData()

    fun btnSaveClick() {
        if (!manName.value?.trim().isNullOrEmpty() && !modelName.value?.trim().isNullOrEmpty() &&
            !year.value?.trim().isNullOrEmpty() && !price.value?.trim().isNullOrEmpty() &&
            year.value?.toInt() in 1990..2020 && price.value?.toInt()!! in 50..500000
        ) {
            if (carId.value.isNullOrEmpty()) {
                repository.insert(
                    createCarObject(
                        null,
                        manName.value,
                        modelName.value,
                        year.value,
                        price.value
                    )
                )
            } else {
                repository.update(
                    createCarObject(
                        carId.value,
                        manName.value,
                        modelName.value,
                        year.value,
                        price.value
                    )
                )
                carId.value = ""
            }
            backstack.value = true
        } else {
            emptyError?.value = "Fill inputs correct info "
        }
    }

    fun txtManClick() {
        clickedFragment.value = "DetailManFragment"
    }

    fun txtModelClick() {
        if (this.manName.value != null) {
            clickedFragment.value = "DetailModelFragment"
        } else {
            selectError?.value = "Select manufacturer"
        }
    }

    fun setCarData(editCar: CarEntity?) {
        manName.value = editCar?.manName
        modelName.value = editCar?.modelName
        year.value = editCar?.year.toString()
        price.value = editCar?.price.toString()
        carId.value = editCar?.id.toString()
    }

    fun setManName(name: String?) {
        this.manName.value = name
    }

    fun setModelName(name: String?) {
        this.modelName.value = name
    }

    fun setModelsData(data: List<String>?) {
        this.modelList = data
    }

    private fun createCarObject(
        id: String?,
        manName: String?,
        modelName: String?,
        price: String?,
        year: String?
    ): CarEntity {
        return CarEntity(id?.toInt(), manName, modelName, price?.toInt(), year?.toInt())
    }
}

