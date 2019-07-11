package com.crecrew.crecre.utils

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.crecrew.crecre.R
import kotlinx.android.synthetic.main.custom_dialog_contents_delete.*
import kotlinx.android.synthetic.main.custom_dialog_signup_id_check.*
import kotlinx.android.synthetic.main.custom_dialog_signup_id_check.tv_contents_title_dialog
import kotlinx.android.synthetic.main.custom_dialog_signup_id_check.tv_left_custom_dialog_community_detail

class SignupIdCheckDialog (
    context: Context,
    private val completeContext: String,
    private val mContext: String,
    private val mRequesttext: String,
    private val mResponseClickListener: View.OnClickListener?

) : Dialog(context, android.R.style.Theme_Translucent_NoTitleBar) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val lpWindow = WindowManager.LayoutParams()
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND
        // 투명도
        lpWindow.dimAmount = 0.55f
        // window에 환경적용
        window!!.attributes = lpWindow

        setContentView(R.layout.custom_dialog_signup_id_check)


        tv_contents_title_dialog.text = completeContext
        custom_dialog_signup_id_check_txt_result.text = mContext
        tv_left_custom_dialog_community_detail.text = mRequesttext

        if (mResponseClickListener != null) {
            btn_custom_dialog_confirm.setOnClickListener(mResponseClickListener)

        } else {
            btn_custom_dialog_confirm.setOnClickListener(mResponseClickListener)
        }
    }
}

