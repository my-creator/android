package com.crecrew.crecre.UI.Activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.EditText
import android.widget.LinearLayout
import com.crecrew.crecre.Network.ApplicationController
import com.crecrew.crecre.Network.Get.GetIdDuplicateResponse
import com.crecrew.crecre.Network.Post.PostSignupResponse
import com.crecrew.crecre.Network.UserNetworkService
import com.crecrew.crecre.R
import com.crecrew.crecre.utils.SignupIdCheckDialog
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.activity_signup.*
import org.jetbrains.anko.startActivity
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupActivity :AppCompatActivity(){

    var idCheckContext = "아이디 중복확인을 해주세요."

    val userNetworkService: UserNetworkService by lazy{
        ApplicationController.instance.userNetworkService
    }

    var isChecked = false
    var gender ="M"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        configureSelector()

        // ID 중복확인
        activity_signup_double_check.setOnClickListener {

            activity_signup_double_check.isSelected = false
            activity_signup_double_check_txt.isSelected = false

            getIdDuplicateResponse(activity_signup_et_id.text.toString())
         }

        btn_back_request_community_act.setOnClickListener {
            finish()
        }

        // TODO: 위에 내용 다 입력하면 select 가능하게 (나중에!)
        // TODO: 화면 자동으로 내려가도록
        // TODO: 생년월일 parsing

        var pw = findViewById(R.id.activity_signup_et_pw) as EditText
        var pw_check = findViewById(R.id.activity_signup_et_pw_check) as EditText

        pw_check.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {

            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(pw.text.toString().equals(pw_check.text.toString())){
                    isChecked = true
                    activity_signup_pw_result.visibility = GONE
                }else{
                    activity_signup_pw_result.visibility = VISIBLE
                    isChecked = false
                }
            }
        })


        activity_signup_btn_submit.setOnClickListener {
            // 아이디 중복 확인, 비밀번호 같은지 확인했으면
            if(isChecked) {
                postSignupResponse(activity_signup_et_id.text.toString(),
                    activity_signup_et_pw.text.toString(), activity_signup_et_name.text.toString(),
                    activity_signup_et_nick_name.text.toString(),
                    gender,activity_signup_et_birth.text.toString())
            }else{
                idCheckContext = "입력하신 정보를 다시 확인해주세요."
                requestDialog.show()
            }
        }
    }

    private fun getIdDuplicateResponse(id :String){

        Log.e("id : ", id)
        var userNetworkingService =  userNetworkService.getIdDuplicate(id)
        userNetworkingService.enqueue(object: Callback<GetIdDuplicateResponse>{
            override fun onFailure(call: Call<GetIdDuplicateResponse>, t: Throwable) {


                Log.e("check duplicate fail", t.toString())
            }

            override fun onResponse(call: Call<GetIdDuplicateResponse>, response: Response<GetIdDuplicateResponse>) {

                 if(response.isSuccessful){
                    if(response.body()!!.status == 200){
                        idCheckContext = "사용 가능한 아이디입니다."
                        activity_signup_double_check.isSelected = true
                        activity_signup_double_check_txt.isSelected = true
                        activity_signup_double_check_txt.text = "사용가능"
                        isChecked = true
                    } else if(response.body()!!.status == 404) {
                        idCheckContext = "이미 사용중인 아이디입니다."
                    }
                     requestDialog.show()
                }
            }
        })
    }

    private fun postSignupResponse(id: String, pw: String, name:String, nick:String, gender: String, birth:String){

        var jsonObject = JSONObject()
        jsonObject.put("id", id)
        jsonObject.put("passwd", pw)
        jsonObject.put("name", name)
        jsonObject.put("nickname", nick)
        jsonObject.put("gender",gender)
        jsonObject.put("birth", birth)

        val gsonObject = JsonParser().parse(jsonObject.toString())as JsonObject
        val postSignupResponse: Call<PostSignupResponse> = userNetworkService.postSignup(gsonObject)
        postSignupResponse.enqueue(object: Callback<PostSignupResponse>{
            override fun onFailure(call: Call<PostSignupResponse>, t: Throwable) {
                Log.e("Signup failed", t.toString())
            }

            override fun onResponse(call: Call<PostSignupResponse>, response: Response<PostSignupResponse>) {
                if(response.isSuccessful){
                    if(response.body()!!.status == 200){
                        Log.e("i'm","here")
                        var intent = Intent(this@SignupActivity, SignupCompleteActivity::class.java)
                        intent.putExtra("user_name",name)
                        startActivity(intent)
                    }
                }
            }
        })
    }

    val requestDialog : SignupIdCheckDialog by lazy {
        SignupIdCheckDialog(
            this@SignupActivity, "알림",
            idCheckContext, "확인", completeConfirmListener)
    }

    private val completeConfirmListener = View.OnClickListener {
        requestDialog!!.dismiss()
    }

    fun configureSelector(){

        // 분명히 좋은 방법이 있을 텐데,,, 귀찮으므로 그냥 한다.
        activity_signup_et_id.setOnFocusChangeListener { v, hasFocus ->
            if(hasFocus) activity_signup_id_line.setBackgroundColor(Color.parseColor("#ff90fc"))
            else activity_signup_id_line.setBackgroundColor(Color.parseColor("#aaaaaa"))
        }

        activity_signup_et_pw.setOnFocusChangeListener { v, hasFocus ->
            if(hasFocus) activity_signup_pw_line.setBackgroundColor(Color.parseColor("#ff90fc"))
            else activity_signup_pw_line.setBackgroundColor(Color.parseColor("#aaaaaa"))
        }

        activity_signup_et_pw_check.setOnFocusChangeListener { v, hasFocus ->
            if(hasFocus) activity_signup_pw_check_line.setBackgroundColor(Color.parseColor("#ff90fc"))
            else activity_signup_pw_check_line.setBackgroundColor(Color.parseColor("#aaaaaa"))
        }

        activity_signup_et_nick_name.setOnFocusChangeListener { v, hasFocus ->
            if(hasFocus) activity_signup_nick_name_line.setBackgroundColor(Color.parseColor("#ff90fc"))
            else activity_signup_nick_name_line.setBackgroundColor(Color.parseColor("#aaaaaa"))
        }
        activity_signup_et_birth.setOnFocusChangeListener { v, hasFocus ->
            if(hasFocus) activity_signup_birth_line.setBackgroundColor(Color.parseColor("#ff90fc"))
            else activity_signup_birth_line.setBackgroundColor(Color.parseColor("#aaaaaa"))
        }

        activity_signup_txt_female.setOnClickListener {
            activity_signup_txt_female.isSelected = true
            activity_signup_txt_male.isSelected = false
            gender="W"
        }
        activity_signup_txt_male.setOnClickListener {
            activity_signup_txt_male.isSelected = true
            activity_signup_txt_female.isSelected = false
            gender="M"
        }
    }
}