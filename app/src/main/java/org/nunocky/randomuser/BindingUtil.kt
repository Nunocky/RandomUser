package org.nunocky.randomuser

import android.databinding.BindingAdapter
import android.widget.ListView
import org.nunocky.randomuser.userlist.UserArrayAdapter

object BindingUtil {
    @BindingAdapter("userlist")
    @JvmStatic
    fun setUserList(listView: ListView, userList: List<RandomUserResponse.User>) {
        if (listView.adapter == null) {
            val adapter = UserArrayAdapter(listView.context, userList)
            listView.adapter = adapter
        }

        val adapter = listView.adapter as UserArrayAdapter
        adapter.notifyDataSetChanged()
    }
}