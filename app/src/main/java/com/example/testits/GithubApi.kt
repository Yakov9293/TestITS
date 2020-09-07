package com.example.testits

import io.reactivex.Observable
import io.reactivex.Single
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.*

interface GithubApi {

    @GET("users")
    fun getUsers(@Query("since") since: Int): Single<List<User>>

    @GET("users/{nickname}")
    fun getUserDetail(@Path("nickname") nickname: String): Single<DetailUser>

}