package com.crecrew.crecre.UI.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.crecrew.crecre.R
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.view.*
import org.jetbrains.anko.startActivity
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        activity_login_et_id.setOnFocusChangeListener { v, hasFocus ->
            if(hasFocus) v.setBackgroundResource(R.drawable.img_id_on)
            else v.setBackgroundResource(R.drawable.img_id_off)
        }
        activity_login_et_pw.setOnFocusChangeListener { v, hasFocus ->
            if(hasFocus) v.setBackgroundResource(R.drawable.img_id_on)
            else v.setBackgroundResource(R.drawable.img_id_off)
        }

        val login_u_id = activity_login_et_id.text.toString()
        val login_u_pw: String = activity_login_et_pw.text.toString()

        activity_login_btn.setOnClickListener {
            startActivity<MainActivity>()
        }
        if(login_u_id != "" && login_u_pw !="") {
            Log.e("hi","isSelected")
            activity_login_btn.isSelected = true
            activity_login_btn.setBackgroundResource(R.drawable.btn_login_on)
            activity_login_btn.setOnClickListener {


                // 통신
            }
        }


        activity_login_txt_join.setOnClickListener {
            startActivity<SignupActivity>()
        }


    }


}
