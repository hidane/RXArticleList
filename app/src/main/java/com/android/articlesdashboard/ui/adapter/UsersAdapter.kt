package com.android.articlesdashboard.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.articlesdashboard.R
import com.android.articlesdashboard.data.model.User
import com.android.articlesdashboard.utils.SpUtils
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_user.view.*
import kotlinx.android.synthetic.main.item_banner.view.*

/**
 * Created by Abhishek.s on 26,October,2020
 */
class UsersAdapter (
    private val users: ArrayList<User>, private val isToShowBanner: Boolean
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val USER_ITEM = 0
        const val BANNER_ITEM = 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (isToShowBanner && users[position].equals(users.size -1) ) {
            BANNER_ITEM
        } else {
            USER_ITEM
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == BANNER_ITEM) {
            UserViewHolder(LayoutInflater.from(parent.context).inflate(
                R.layout.item_user, parent, false))
        } else {
            BannerViewHolder (LayoutInflater.from(parent.context).inflate(
                R.layout.item_banner, parent, false
            ))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position).equals(BANNER_ITEM)) {
            (holder as BannerViewHolder).bind(users[position])
        } else {
            (holder as UserViewHolder).bind(users[position])
        }
    }

    override fun getItemCount(): Int {
        return users.size
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: User) {
            itemView.atv_user_name.text = user.name
            Glide.with(itemView.iv_user_pic.context)
                .load(user.avatar)
                .into(itemView.iv_user_pic)
        }
    }

    class BannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: User) {
            Glide.with(itemView.iv_banner.context)
                .load(user.avatar)
                .into(itemView.iv_banner)
        }
    }
}