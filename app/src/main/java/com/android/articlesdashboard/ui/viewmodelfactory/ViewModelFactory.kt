package com.android.articlesdashboard.ui.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.articlesdashboard.data.Repos.UserRepos
import com.android.articlesdashboard.data.network.NetworkDao
import com.android.articlesdashboard.ui.main.viewmodel.UserViewModel

class ViewModelFactory(private val networkDao: NetworkDao) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(UserRepos(networkDao)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}