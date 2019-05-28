package org.nunocky.randomuser.detail

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import org.nunocky.randomuser.RandomUserResponse

class DetailViewModel : ViewModel() {
    val user = MutableLiveData<RandomUserResponse.User>()

}