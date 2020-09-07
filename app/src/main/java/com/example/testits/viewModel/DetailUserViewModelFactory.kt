package com.example.testits.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DetailUserViewModelFactory(private val login: String) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailUserViewModel(login) as T
    }

}