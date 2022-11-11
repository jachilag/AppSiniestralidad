package com.example.appsiniestralidadkotlin.viewModel

import androidx.lifecycle.ViewModel
import com.example.appsiniestralidadkotlin.domain.Repository

class SiniestrosViewModel: ViewModel() {
    val repository= Repository()

//    fun fetchBookData(): LiveData<MutableList<Siniestros>> {
//        val mutableLiveData= MutableLiveData<MutableList<Siniestros>>()
//        repository.getSiniestrosData().observeForever{
//            mutableLiveData.value=it
//        }
//        return mutableLiveData
//    }
}
