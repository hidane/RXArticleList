package com.android.articlesdashboard.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Abhishek.s on 26,October,2020
 */
data class User (
    @SerializedName("id")
    val id: Int = 0,

    @SerializedName("name")
    val name: String = "",

    @SerializedName("avatar")
    val avatar: String = ""
)