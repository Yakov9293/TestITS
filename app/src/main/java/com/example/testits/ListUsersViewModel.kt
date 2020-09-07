package com.example.testits

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.SupervisorJob

class ListUsersViewModel : ViewModel() {

    val listUsers = ArrayList<User>()
    var idLastUser = 0

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun addUsers(callback: () -> Unit) {
        GithubService.githubApi.getUsers(idLastUser).subscribeOn(Schedulers.io()).observeOn(
            AndroidSchedulers.mainThread()
        ).subscribe(object : DisposableSingleObserver<List<User>>() {
            override fun onSuccess(response: List<User>) {
                response.let {
                    idLastUser = it.last().id
                    listUsers.addAll(it)
                    callback.invoke()
                }
            }

            override fun onError(e: Throwable) {
                _errorMessage.postValue(e.message)
                Log.d(TAG, e.message.toString())
            }
        })
    }

    companion object {
        private val TAG = ListUsersViewModel::class.java.simpleName
    }

}