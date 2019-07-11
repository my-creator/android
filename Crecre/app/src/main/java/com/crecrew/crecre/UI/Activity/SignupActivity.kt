package com.crecrew.crecre.UI.Activity

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.crecrew.crecre.Network.ApplicationController
import com.crecrew.crecre.Network.Get.GetIdDuplicateResponse
import com.crecrew.crecre.Network.UserNetworkService
import com.crecrew.crecre.R
import com.crecrew.crecre.utils.SignupIdCheckDialog
import kotlinx.android.synthetic.main.activity_signup.*
import org.jetbrains.anko.startActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupActivity :AppCompatActivity(){

    lateinit var idCheckContext:String

    val userNetworkService: UserNetworkService by lazy{
        ApplicationController.instance.userNetworkService
    }

    var isChecked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        configureSelector()



        activity_signup_double_check.setOnClickListener {

            
            getIdDuplicateResponse(activity_signup_double_check_txt.text.toString())
            // Todo

            // 2-1. 중복확인을 안한 경우
            idCheckContext = "아이디 중복확인을 해주세요."

            requestDialog.show()

        }

        // 위에 내용 다 입력하면 select 가능하게
        activity_signup_btn_submit.setOnClickListener {

            if(isChecked == true) {
               // activity_signup_btn_submit.isSelected
                // 통신
                startActivity<SignupCompleteActivity>()
            }
        }

    }

    val requestDialog : SignupIdCheckDialog by lazy {
        SignupIdCheckDialog(
            this, "알림",
            idCheckContext, "확인", completeConfirmListener)
    }

    private val completeConfirmListener = View.OnClickListener {
        requestDialog!!.dismiss()
        finish()
    }

    private fun getIdDuplicateResponse(id :String){

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
                    } else if(response.body()!!.status == 404)
                        idCheckContext = "이미 사용중인 아이디입니다."
                }
            }
        }
        )
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
        }
        activity_signup_txt_male.setOnClickListener {
            activity_signup_txt_male.isSelected = true
            activity_signup_txt_female.isSelected = false
        }
    }
}