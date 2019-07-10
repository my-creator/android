package com.crecrew.crecre.UI.Activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.crecrew.crecre.R
import kotlinx.android.synthetic.main.activity_signup_complete.*
import org.jetbrains.anko.startActivity

class SignupCompleteActivity :AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_complete)

        activity_signup_complete_btn.setOnClickListener {
            startActivity<MainActivity>()
        }
    }
}