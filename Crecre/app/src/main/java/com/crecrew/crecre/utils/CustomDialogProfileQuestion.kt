package com.crecrew.crecre.utils

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.WindowManager
import com.crecrew.crecre.R
import kotlinx.android.synthetic.main.activity_profile_join_stat.*
import kotlinx.android.synthetic.main.custom_dialog_contents_delete.*
import kotlinx.android.synthetic.main.custom_dialog_profile_class_question.*
import kotlinx.android.synthetic.main.custom_dialog_profile_rank_question.*

class CustomDialogProfileQuestion(
    context: Context,
    private val closeClickListener: View.OnClickListener?
) : Dialog(context, android.R.style.Theme_Translucent_NoTitleBar) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val lpWindow = WindowManager.LayoutParams()
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND
        // 투명도
        lpWindow.dimAmount = 0.55f
        // window에 환경적용
        window!!.attributes = lpWindow


        setContentView(R.layout.custom_dialog_profile_rank_question)

        //##??
        if (closeClickListener != null) {
            custom_dialog_profile_rank_question_btn_back.setOnClickListener(closeClickListener)

        } else {
            custom_dialog_profile_rank_question_btn_back.setOnClickListener(closeClickListener)
        }

    }


}

