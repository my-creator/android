package com.crecrew.crecre.utils

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.crecrew.crecre.R
import kotlinx.android.synthetic.main.custom_dialog_community_logincheck.*
import kotlinx.android.synthetic.main.custom_dialog_contents_delete.*

class CustomLoginCheckDialog(
    context: Context,
    private val completeContext: String,
    private val mContext: String,
    private val eContext: String,
    private val leftRequesttext: String,
    private val rightRequesttext: String,
    private val mRightResponseClickListener: View.OnClickListener?,
    private val mLeftResponseClickListener: View.OnClickListener?

) : Dialog(context, android.R.style.Theme_Translucent_NoTitleBar) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val lpWindow = WindowManager.LayoutParams()
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND
        // 투명도
        lpWindow.dimAmount = 0.55f
        // window에 환경적용
        window!!.attributes = lpWindow

        setContentView(R.layout.custom_dialog_community_logincheck)


        tv_logincheck_alarm_title_dialog.text = completeContext
        tv_des_logincheck_custom_dialog.text = mContext
        tv2_des_logincheck_custom_dialog.text = eContext
        tv_left_custom_dialog_logincheck.text = leftRequesttext
        tv_right_custom_dialog_login_check.text = rightRequesttext

        //##??
        if (mRightResponseClickListener != null) {
            btn_left_custom_dialog_community_logincheck.setOnClickListener(mRightResponseClickListener)

        } else {
            btn_left_custom_dialog_community_logincheck.setOnClickListener(mRightResponseClickListener)
        }

        if(mLeftResponseClickListener != null)
            btn_right_custom_dialog_login_check.setOnClickListener(mLeftResponseClickListener)
        else
            btn_right_custom_dialog_login_check.setOnClickListener(mLeftResponseClickListener)
    }
}

