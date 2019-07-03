package com.crecrew.crecre.UI.Activity.Community

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.crecrew.crecre.R
import com.crecrew.crecre.UI.Fragment.Community.CommunityFragment
import com.crecrew.crecre.utils.CustomRequestCompleteDialog
import kotlinx.android.synthetic.main.activity_community_request.*
import kotlinx.android.synthetic.main.custom_dialog_response_complete.*

class CommunityRequestActivity : AppCompatActivity() {

    val requestDialog : CustomRequestCompleteDialog by lazy {
        CustomRequestCompleteDialog(this@CommunityRequestActivity, "요청완료","현재 85명이 이 유튜버의\n" +
                "개인 게시판을 요청했어요!", "팬 100명이 모이면 유튜버의\n" +
                "개인 게시판이 만들어져요.", completeConfirmListener,"확인")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community_request)

        requestBtn_community_request_act.setOnClickListener {
            requestDialog.show()
        }
        btn_back_request_community_act.setOnClickListener {
            finish()
        }
    }

    private val completeConfirmListener = View.OnClickListener {
        requestDialog!!.dismiss()
        //startActivity<CommunityFragment>()
    }
}
