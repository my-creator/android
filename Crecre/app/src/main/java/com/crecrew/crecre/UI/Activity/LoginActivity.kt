package com.crecrew.crecre.UI.Activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import com.crecrew.crecre.DB.SharedPreferenceController
import com.crecrew.crecre.Network.ApplicationController
import com.crecrew.crecre.Network.Post.PostLoginResponse
import com.crecrew.crecre.Network.UserNetworkService
import com.crecrew.crecre.R
import com.crecrew.crecre.utils.ApplicationData
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.ctx
import org.jetbrains.anko.startActivity
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    val userNetworkService: UserNetworkService by lazy{
        ApplicationController.instance.userNetworkService
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        activity_login_btn.isClickable = false
        var isOnId = false
        var isOnPw = false

        var id = findViewById(R.id.activity_login_et_id) as EditText
        var pw = findViewById(R.id.activity_login_et_pw) as EditText

        id.setOnFocusChangeListener { v, hasFocus ->
            if(hasFocus) v.setBackgroundResource(R.drawable.img_id_on)
            else v.setBackgroundResource(R.drawable.img_id_off)
        }
        pw.setOnFocusChangeListener { v, hasFocus ->
            if(hasFocus) v.setBackgroundResource(R.drawable.img_id_on)
            else v.setBackgroundResource(R.drawable.img_id_off)
        }

        id.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) { }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(id.textSize >0){
                    isOnId = true
                }else if(id.textSize <=0)
                    isOnId = false
            }
        })

        pw.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) { }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(pw.textSize >0){
                    isOnPw = true
                }else if(pw.textSize <=0)
                    isOnPw = false

                if(isOnId && isOnPw){
                    activity_login_btn.isSelected = true
                    activity_login_btn.isClickable = true
                }

            }
        })

        activity_login_btn.setOnClickListener {
            postLoginResponse(id.text.toString(),pw.text.toString())
        }


        activity_login_txt_join.setOnClickListener {
            startActivity<SignupActivity>()
        }

        rl_login_act.setOnClickListener {
            downKeyboard(rl_login_act)
        }
    }

    fun postLoginResponse(id :String, pw: String) {

        var jsonObject = JSONObject()
        jsonObject.put("id", id)
        jsonObject.put("passwd", pw)

        val gsonObject = JsonParser().parse(jsonObject.toString()) as JsonObject
        var userNetworkService = userNetworkService.postLogin(gsonObject)
        userNetworkService.enqueue(object : Callback<PostLoginResponse> {
            override fun onFailure(call: Call<PostLoginResponse>, t: Throwable) {
                Log.e("Login error", t.toString())
            }

            override fun onResponse(call: Call<PostLoginResponse>, response: Response<PostLoginResponse>) {
                if (response.isSuccessful) {
                    if (response.body()!!.status == 200) {
                        SharedPreferenceController.setUserToken(applicationContext, response.body()!!.data.token.token)

                        startActivity<MainActivity>()
                        finish()
                    }else if(response.body()!!.status == 400){
                        Log.e("message",response.body()!!.message)
                        Toast.makeText(this@LoginActivity, response.body()!!.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        })
    }

    //키보드 다운
    private fun downKeyboard(view: View) {
        val imm: InputMethodManager =
            applicationContext!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

}
