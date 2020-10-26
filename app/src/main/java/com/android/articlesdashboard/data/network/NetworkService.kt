package com.android.articlesdashboard.data.network

import com.android.articlesdashboard.data.model.User
import io.reactivex.Single

/**
 * Created by Abhishek.s on 26,October,2020
 */
interface NetworkService {
    fun getUsers(): Single<List<User>>
}