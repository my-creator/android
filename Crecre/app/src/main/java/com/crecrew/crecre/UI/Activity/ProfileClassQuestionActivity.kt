package com.crecrew.crecre.UI.Activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.crecrew.crecre.R
import kotlinx.android.synthetic.main.activity_profile_join_stat.*
import kotlinx.android.synthetic.main.custom_dialog_profile_class_question.*
import kotlinx.android.synthetic.main.custom_dialog_profile_rank_question.*

class ProfileClassQuestionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.custom_dialog_profile_class_question)

        custom_dialog_profile_class_question_btn_back.setOnClickListener{
            finish()
        }

    }

}