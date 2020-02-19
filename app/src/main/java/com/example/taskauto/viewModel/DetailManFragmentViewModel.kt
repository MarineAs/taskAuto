package com.example.taskauto.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.taskauto.model.CarRepository
import com.example.taskauto.model.room.ModelEntity

class DetailManFragmentViewModel(
    application: Application
) : AndroidViewModel(application) {
    private val repository: CarRepository =
        CarRepository.getInstance(application.applicationContext)
    var allData: LiveData<List<ModelEntity>>? = repository.getAllModelList()
}
