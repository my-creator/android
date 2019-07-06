package com.crecrew.crecre.UI.Activity.Community

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.crecrew.crecre.R
import com.crecrew.crecre.UI.Fragment.Community.CommunityFragment
import com.crecrew.crecre.utils.CustomRequestCompleteDialog
import com.crecrew.crecre.utils.SearchAlarmDialog
import kotlinx.android.synthetic.main.activity_community_request.*
import kotlinx.android.synthetic.main.custom_dialog_response_complete.*

class CommunityRequestActivity : AppCompatActivity() {

    val requestDialog : CustomRequestCompleteDialog by lazy {
        CustomRequestCompleteDialog(this@CommunityRequestActivity, "요청완료","현재 85명이 이 유튜버의\n" +
                "개인 게시판을 요청했어요!", "팬 100명이 모이면 유튜버의\n" +
                "개인 게시판이 만들어져요.", completeConfirmListener,"확인")
    }

    val requestfailDialog : SearchAlarmDialog by lazy {
        SearchAlarmDialog(this@CommunityRequestActivity, "알림","아직 입력하지 않은 항목이 있습니다.\n" +
                "모든 항목을 입력해주세요.",
            completefailConfirmListener,"확인")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community_request)

        requestBtn_community_request_act.setOnClickListener {

            if(et_enter_youtuber_name_request_act.text.length == 0 || et_enter_youtube_channel_request_act.text.length == 0 )
            {
                requestfailDialog.show()
            }
            else
                requestDialog.show()
        }
        btn_back_request_community_act.setOnClickListener {
            finish()
        }

        rl_comm_request_act.setOnClickListener {
            downKeyboard(rl_comm_request_act)
        }


    }

    private val completeConfirmListener = View.OnClickListener {
        requestDialog!!.dismiss()
        finish()
    }

    private val completefailConfirmListener = View.OnClickListener {
        requestfailDialog!!.dismiss()

        //##작성하지 않은 editText focus주기?
    }

    //키보드 다운
    private fun downKeyboard(view: View) {
        val imm: InputMethodManager =
            applicationContext!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

}
