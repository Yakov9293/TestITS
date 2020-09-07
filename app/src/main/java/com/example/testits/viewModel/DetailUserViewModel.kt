package com.example.testits.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testits.data.DetailUser
import com.example.testits.data.GithubService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class DetailUserViewModel(private val login: String) : ViewModel() {

    private val _detailUser = MutableLiveData<DetailUser>()
    val detailMovie: LiveData<DetailUser> = _detailUser
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    init {
        loadDetailMovieRequest()
    }

    private fun loadDetailMovieRequest() {
        GithubService.githubApi.getUserDetail(login).subscribeOn(Schedulers.io()).observeOn(
            AndroidSchedulers.mainThread()
        ).subscribe(object : DisposableSingleObserver<DetailUser>() {

            override fun onError(e: Throwable) {
                _errorMessage.postValue(e.message)
                Log.d(TAG, e.message.toString())
            }

            override fun onSuccess(response: DetailUser) {
                _detailUser.value = response
            }
        })

    }

    companion object {
        private val TAG = DetailUserViewModel::class.java.simpleName
    }

}