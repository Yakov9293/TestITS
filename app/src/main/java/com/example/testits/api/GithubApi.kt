package com.example.testits.api

import com.example.testits.data.DetailUser
import com.example.testits.data.User
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {

    @GET("users")
    fun getUsers(@Query("since") since: Int): Single<List<User>>

    @GET("users/{nickname}")
    fun getUserDetail(@Path("nickname") login: String): Single<DetailUser>

}