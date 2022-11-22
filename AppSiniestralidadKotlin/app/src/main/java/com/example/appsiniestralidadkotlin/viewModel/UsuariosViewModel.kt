package com.example.appsiniestralidadkotlin.viewModel

import androidx.lifecycle.ViewModel
import com.example.appsiniestralidadkotlin.domain.Repository

class UsuariosViewModel: ViewModel() {
    val repository= Repository()

//    fun fetchUsersData(): LiveData<MutableList<users>> {
//        val mutableLiveData= MutableLiveData<MutableList<users>>()
//        repository.getUsersData().observeForever{
//            mutableLiveData.value=it
//        }
//        return mutableLiveData
//    }
}