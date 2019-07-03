package com.crecrew.crecre.UI.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.crecrew.crecre.R
import kotlinx.android.synthetic.main.activity_community_detail.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class CommunityDetailActivity : AppCompatActivity(), View.OnClickListener {

    var btn_like = 0
    var btn_unlike = 0

    override fun onClick(v: View?) {
        when (v!!) {

            //추천 누르기
            btn_like_community_detail_act -> {
                if (btn_like == 0) {
                    btn_like_community_detail_act.isSelected = true
                    btn_like = 1
                } else {
                    btn_like_community_detail_act.isSelected = false
                    btn_like = 0
                }

            }

            //비추천 누르기
            btn_unlike_community_detail_act -> {
                if (btn_unlike == 0) {
                    btn_unlike_community_detail_act.isSelected = true
                    btn_unlike = 1
                } else {
                    btn_unlike_community_detail_act.isSelected = false
                    btn_unlike = 0
                }
            }

            //뒤로가기 버튼
            btn_back_community_detail_act -> {
                finish()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community_detail)

        init()
    }

    private fun init() {
        btn_like_community_detail_act.setOnClickListener(this)
        btn_unlike_community_detail_act.setOnClickListener(this)
        btn_back_community_detail_act.setOnClickListener(this)
    }
}
