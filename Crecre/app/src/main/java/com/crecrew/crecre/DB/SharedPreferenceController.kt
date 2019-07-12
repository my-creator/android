package com.crecrew.crecre.DB

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.crecrew.crecre.Network.Post.Token

object SharedPreferenceController {

    val MY_ACCOUNT = "unique_string"

    fun setUserToken(ctx: Context, u_token: String?) {

        val preference: SharedPreferences = ctx.getSharedPreferences(MY_ACCOUNT, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = preference.edit()
        editor.putString("token", u_token)
        editor.commit()
    }

    fun getUserToken(ctx: Context): String {
        val preference: SharedPreferences = ctx.getSharedPreferences(MY_ACCOUNT, Context.MODE_PRIVATE)
        return preference.getString("token", "")
    }

    fun clearUserToken(ctx: Context) {
        val preference: SharedPreferences = ctx.getSharedPreferences(MY_ACCOUNT, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = preference.edit()
        editor.clear()
        editor.commit()
    }
}