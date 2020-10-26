package com.android.articlesdashboard.data.network

import com.android.articlesdashboard.data.model.User
import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Single

class NetworkImpl : NetworkService {

    override fun getUsers(): Single<List<User>> {
        return Rx2AndroidNetworking.get("https://5f96478511ab98001603a759.mockapi.io/api/v1/User")
            .addQueryParameter("page","1")
            .addQueryParameter("limit","40")
            .build()
            .getObjectListSingle(User::class.java)
    }

}