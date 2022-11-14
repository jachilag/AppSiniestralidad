package com.example.appsiniestralidadkotlin.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appsiniestralidadkotlin.domain.Repository
import com.example.appsiniestralidadkotlin.model.ciudades

class CiudadesViewModel: ViewModel()  {
    val repository= Repository()

    fun fetchBookData(): LiveData<MutableList<ciudades>> {
        val mutableLiveData= MutableLiveData<MutableList<ciudades>>()
        repository.getCiudadesData().observeForever{
            mutableLiveData.value=it
        }
        return mutableLiveData
    }
}
