package org.nunocky.randomuser.userlist

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import org.nunocky.randomuser.RandomUserRepository
import org.nunocky.randomuser.RandomUserResponse

class MainViewModel : ViewModel(), MainContract.ViewModel {
    private val mutableUserList = MutableLiveData<ArrayList<RandomUserResponse.User>>().apply {
        value = ArrayList()
    }

    val userList: LiveData<ArrayList<RandomUserResponse.User>> = mutableUserList

    var view: MainContract.View? = null

    private val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    override fun refreshData() {
        view?.setRefreshState(true)

        val repository = RandomUserRepository()

        val d = repository.getUserList().subscribe(
            { list ->
                val userList = mutableUserList.value!!
                userList.clear()
                userList.addAll(list)
                mutableUserList.value = userList

                view?.setRefreshState(false)
            },
            { t ->
                t.printStackTrace()
                t.message?.let { view?.toastError(it) }
                view?.setRefreshState(false)
            }
        )

        compositeDisposable.add(d)
    }
}
