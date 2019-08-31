package com.crecrew.crecre.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.crecrew.crecre.R
import kotlinx.android.synthetic.main.activity_signup_complete.*
import org.jetbrains.anko.startActivity

class SignupCompleteActivity :AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_complete)

        var user_name = intent.getStringExtra("user_name")

        var str = user_name + "님,"
        activity_signup_complete_name.setText(str)

        activity_signup_complete_btn.setOnClickListener {
            startActivity<MainActivity>()
        }
    }
}