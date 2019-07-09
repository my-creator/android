package com.crecrew.crecre.utils

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.View
import android.view.WindowManager
import com.crecrew.crecre.R
import kotlinx.android.synthetic.main.custom_dialog_response_complete.*

class CustomRequestCompleteDialog(context : Context,
                                  private val completeContext : String,
                                  private val mContext : SpannableStringBuilder,
                                  private val eContext : String,
                                  private val mResponseClickListener: View.OnClickListener?,
                                  private val responseText: String) : Dialog(context, android.R.style.Theme_Translucent_NoTitleBar) {

    private val clickedState = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val lpWindow = WindowManager.LayoutParams()
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND
        // 투명도
        lpWindow.dimAmount = 0.55f
        // window에 환경적용
        window!!.attributes = lpWindow

        setContentView(R.layout.custom_dialog_response_complete)

        tv_requestcompelte_title_dialog.text = completeContext
        tv_description_custom_dialog.text = mContext
        tv_description_exp_custom_dialog.text = eContext
        tv_complete_custom_dialog.text = responseText

        if (mResponseClickListener != null) {
            btn_bottom_box_custom_dialog.setOnClickListener(mResponseClickListener)

        } else {
            btn_bottom_box_custom_dialog.setOnClickListener(mResponseClickListener)
        }
    }

}