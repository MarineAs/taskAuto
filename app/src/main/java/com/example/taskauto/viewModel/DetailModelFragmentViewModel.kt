package com.example.taskauto.viewModel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetailModelFragmentViewModel : ViewModel() {
    var allData: MutableLiveData<List<String>> = MutableLiveData()
}