package org.nunocky.randomuser.detail

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*
import org.nunocky.randomuser.R
import org.nunocky.randomuser.RandomUserResponse
import org.nunocky.randomuser.databinding.ActivityDetailBinding


class DetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailBinding
    lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        binding.lifecycleOwner = this
        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        binding.viewModel = viewModel

        val user = intent.getSerializableExtra("user") as RandomUserResponse.User
        viewModel.user.value = user

        Glide.with(this).load(user.picture!!.large).into(imageView)
    }
}
