package com.android.articlesdashboard.utils

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by Abhishek.s on 26,October,2020
 */
class SpUtils {

    val LOG_TAG = "SpUtils"
    private val FILE_NAME = "user_sh"
    private var instance: SpUtils? = null
    private var sharedpreferences: SharedPreferences? = null
    private val SHOW_BANNER = "showBanner"

    fun getInstance(context: Context): SpUtils? {
        if (instance == null) {
            instance = SpUtils()
            sharedpreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        }
            return instance
    }

    fun setShowBanner(isToShowBanner: Boolean) {
        sharedpreferences?.edit()?.putBoolean(SHOW_BANNER, isToShowBanner)?.apply()
    }

    fun getShowBanner(): Boolean? {
        return sharedpreferences?.getBoolean(SHOW_BANNER, false)
    }
}