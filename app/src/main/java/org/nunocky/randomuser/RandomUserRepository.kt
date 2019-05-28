package org.nunocky.randomuser

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RandomUserRepository {

    fun getUserList(): Single<List<RandomUserResponse.User>> {
        return Single.create<List<RandomUserResponse.User>> { emitter ->

            val retrofit = Retrofit.Builder()
                .baseUrl("https://randomuser.me/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service = retrofit.create(RandomUserService::class.java)

            val userCall = service.getUserList(10)

            userCall.enqueue(object : Callback<RandomUserResponse> {
                override fun onFailure(call: Call<RandomUserResponse>, t: Throwable) {
                    emitter.onError(t)
                }

                override fun onResponse(call: Call<RandomUserResponse>, response: Response<RandomUserResponse>) {
                    val results = response.body() ?: throw Throwable("error1")

                    val userList = results.results
                    if (userList != null) {
                        emitter.onSuccess(userList)
                    } else {
                        throw Throwable("data empty")
                    }
                }
            })
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}