package com.example.appsiniestralidadkotlin.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appsiniestralidadkotlin.domain.Repository
import com.example.appsiniestralidadkotlin.model.users

class UsuariosViewModel: ViewModel() {
    val repository= Repository()

    fun fetchUsersData(): LiveData<MutableList<users>> {
        val mutableLiveData= MutableLiveData<MutableList<users>>()
        repository.getUsersData().observeForever{
            mutableLiveData.value=it
        }
        return mutableLiveData
    }
}