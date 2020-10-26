package com.android.articlesdashboard.data.network

/**
 * Created by Abhishek.s on 26,October,2020
 */
class NetworkDao(private val networkService: NetworkService) {

    fun getUsers() = networkService.getUsers()

}