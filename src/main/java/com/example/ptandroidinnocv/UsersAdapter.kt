package com.example.ptandroidinnocv

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.user_list_item.view.*

class UsersAdapter(private val mContext: Context, private var usersList: List<User>): ArrayAdapter<User>(mContext, 0, usersList){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout = LayoutInflater.from(mContext).inflate(R.layout.user_list_item, parent, false)

        val user = usersList[position]
        layout.tv_userId.text = "ID: " + user.id.toString()
        layout.tv_nameUser.text = "NAME: " + user.name
        layout.tv_birthday.text = "BIRTHDATE:" + user.birthday
        layout.iv_user.setImageResource(R.mipmap.ic_user)
        return layout
    }
}