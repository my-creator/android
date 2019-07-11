package com.crecrew.crecre.UI.Activity

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.crecrew.crecre.R
import kotlinx.android.synthetic.main.activity_signup.*
import org.jetbrains.anko.startActivity

class SignupActivity :AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

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

        activity_signup_double_check.setOnClickListener {
            activity_signup_double_check
        }

        // 위에 내용 다 입력하면 select 가능하게
        activity_signup_btn_submit.setOnClickListener {
            activity_signup_btn_submit.isSelected
            startActivity<SignupCompleteActivity>()

        }
    }
}