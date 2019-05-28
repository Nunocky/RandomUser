package org.nunocky.randomuser

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUserService {
    @GET("api")
    fun getUserList(@Query("results") count: Int): Call<RandomUserResponse>
}