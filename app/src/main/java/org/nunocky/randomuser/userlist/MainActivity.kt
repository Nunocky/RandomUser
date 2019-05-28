package org.nunocky.randomuser.userlist

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.nunocky.randomuser.R
import org.nunocky.randomuser.RandomUserResponse
import org.nunocky.randomuser.databinding.ActivityMainBinding
import org.nunocky.randomuser.detail.DetailActivity

interface MainContract {
    interface View {
        fun setRefreshState(state: Boolean)
        fun toastError(msg: String)
    }

    interface ViewModel {
        fun refreshData()
    }
}

class MainActivity : AppCompatActivity(), MainContract.View {

    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding.viewModel = viewModel

        refresh.setOnRefreshListener {
            viewModel.refreshData()
        }

        listView.setOnItemClickListener { parent, view, position, id ->
            val user = listView.getItemAtPosition(position) as RandomUserResponse.User
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("user", user)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.view = this

        if (viewModel.userList.value?.size == 0) {
            viewModel.refreshData()
        }
    }

    override fun onPause() {
        super.onPause()
        viewModel.view = null
    }

    override fun setRefreshState(state: Boolean) {
        refresh.isRefreshing = state
    }

    override fun toastError(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

}

