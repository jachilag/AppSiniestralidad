package com.example.appsiniestralidadkotlin.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appsiniestralidadkotlin.domain.Repository
import com.example.appsiniestralidadkotlin.model.siniestros

class SiniestrosViewModel: ViewModel() {
    val repository= Repository()

    fun fetchSiniestroData(): LiveData<MutableList<siniestros>> {
        val mutableLiveData= MutableLiveData<MutableList<siniestros>>()
        repository.getSiniestrosData().observeForever{
            mutableLiveData.value=it
        }
        return mutableLiveData
    }
}
