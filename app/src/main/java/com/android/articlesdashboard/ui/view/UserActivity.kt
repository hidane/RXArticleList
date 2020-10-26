package com.android.articlesdashboard.ui.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.articlesdashboard.R
import com.android.articlesdashboard.data.model.User
import com.android.articlesdashboard.data.network.NetworkDao
import com.android.articlesdashboard.data.network.NetworkImpl
import com.android.articlesdashboard.ui.adapter.UsersAdapter
import com.android.articlesdashboard.ui.main.viewmodel.UserViewModel
import com.android.articlesdashboard.ui.viewmodelfactory.ViewModelFactory
import com.android.articlesdashboard.utils.SpUtils
import com.android.articlesdashboard.utils.Status
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity() {
    private lateinit var userVievModel: UserViewModel
    private lateinit var userAdapter: UsersAdapter
    private var myusers: List<User>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        setUpRecycler()
        setupViewModel()
        setupObserver()
    }

    private fun setUpRecycler() {
        rv_users.layoutManager = LinearLayoutManager(this)

        userAdapter = UsersAdapter(ArrayList(), true)

        rv_users.addItemDecoration(
            DividerItemDecoration(
                rv_users.context,
                (rv_users.layoutManager as LinearLayoutManager).orientation
            )
        )

        rv_users.adapter = userAdapter

        userAdapter.onItemClick = {
            Toast.makeText(this,"Position clicked" + it , Toast.LENGTH_LONG).show()
        }
    }

    private fun setupObserver() {
        userVievModel.getUsers().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    pb_loader.visibility = View.GONE
                    it.data?.let {
                            myusers = it
                            updateList(it)
                    }
                    rv_users.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    pb_loader.visibility = View.VISIBLE
                    rv_users.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    pb_loader.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun updateList(users: List<User>) {
        SpUtils.getInstance(this)?.getShowBanner()?.let {
            userAdapter.updateData(users, it)
        }
        userAdapter.notifyDataSetChanged()
    }

    private fun setupViewModel() {
        userVievModel = ViewModelProviders.of(
            this,
            ViewModelFactory(NetworkDao(NetworkImpl()))
        ).get(UserViewModel::class.java)
    }
}