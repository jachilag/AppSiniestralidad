package com.example.appsiniestralidadkotlin.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appsiniestralidadkotlin.domain.Repository
import com.example.appsiniestralidadkotlin.model.Ciudad

class CiudadesViewModel: ViewModel() {
    val repository= Repository()

    fun fetchCiudadesData(): LiveData<MutableList<Ciudad>> {
        val mutableLiveData= MutableLiveData<MutableList<Ciudad>>()
        repository.getDataCiudades().observeForever{
            mutableLiveData.value=it
        }
        return mutableLiveData
    }
}