package com.android.articlesdashboard.data.Repos

import com.android.articlesdashboard.data.model.User
import com.android.articlesdashboard.data.network.NetworkDao
import io.reactivex.Single

/**
 * Created by Abhishek.s on 26,October,2020
 */
class UserRepos (private val networkDao: NetworkDao) {

    fun getUsers() : Single<List<User>> {
        return networkDao.getUsers()
    }
}