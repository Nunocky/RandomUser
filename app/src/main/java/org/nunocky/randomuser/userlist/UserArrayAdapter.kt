package org.nunocky.randomuser.userlist

import android.content.Context
import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import org.nunocky.randomuser.R
import org.nunocky.randomuser.RandomUserResponse
import org.nunocky.randomuser.databinding.UserListItemBinding

class UserArrayAdapter(context: Context, list: List<RandomUserResponse.User>) :
    ArrayAdapter<RandomUserResponse.User>(context, 0, list) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: createView(parent)

        val binding = view.tag as UserListItemBinding

        val user = getItem(position)
        if (user != null) {
            binding.user = user
            user.picture?.thumbnail?.let { url ->
                Glide.with(context).load(url).into(binding.imageView)
            }
        }

        return view
    }

    private fun createView(parent: ViewGroup): View {
        val layoutInflater = LayoutInflater.from(context)
        val binding: UserListItemBinding =
            DataBindingUtil.inflate(
                layoutInflater,
                R.layout.user_list_item,
                parent,
                false
            )

        val view = binding.root
        view.tag = binding

        return binding.root
    }
}