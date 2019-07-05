package com.crecrew.crecre.UI.Activity.Community

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.crecrew.crecre.Data.CommentData
import com.crecrew.crecre.R
import com.crecrew.crecre.UI.Adapter.CommunityDetailCommentRecyclerViewAdapter
import com.crecrew.crecre.UI.Adapter.CommunityHotPostRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_community_detail.*
import kotlinx.android.synthetic.main.activity_community_hot_post.*

class CommunityDetailActivity : AppCompatActivity(), View.OnClickListener {

    var btn_like = 0
    var btn_unlike = 0
    lateinit var communityDetailCommentRecyclerViewAdapter:CommunityDetailCommentRecyclerViewAdapter

    //click
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

            rl_commu_detail_act -> {
                downKeyboard(rl_commu_detail_act)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community_detail)

        init()

        configureRecyclerView()
    }

    private fun init() {
        btn_like_community_detail_act.setOnClickListener(this)
        btn_unlike_community_detail_act.setOnClickListener(this)
        btn_back_community_detail_act.setOnClickListener(this)
    }

    //recyclerView
    private fun configureRecyclerView() {
        var dataList: ArrayList<CommentData> = ArrayList()
        dataList.add(
            CommentData("http://sopt.org/wp/wp-content/uploads/2014/01/24_SOPT-LOGO_COLOR-1.png","양시연영상",
                "18초전",  "시연이는 시연시연시연")
        )
        dataList.add(
            CommentData("http://sopt.org/wp/wp-content/uploads/2014/01/24_SOPT-LOGO_COLOR-1.png",
                "안녀안알ㄴ여ㅏㄴ여낭", "14:27","내가 무슨 부귀영화를 누리려고 이 글에 들어왔니?")
        )
        dataList.add(
            CommentData("http://sopt.org/wp/wp-content/uploads/2014/01/24_SOPT-LOGO_COLOR-1.png","유가희",
                "1분전",  "명망먀여망며아명마염")
        )
        dataList.add(
            CommentData("http://sopt.org/wp/wp-content/uploads/2014/01/24_SOPT-LOGO_COLOR-1.png","양희찬",
                "1시간전",  "김!애!용!!")
        )
        dataList.add(
            CommentData("http://sopt.org/wp/wp-content/uploads/2014/01/24_SOPT-LOGO_COLOR-1.png","김애용",
                "18초전",  "나는 김!애!용!!")
        )

        communityDetailCommentRecyclerViewAdapter = CommunityDetailCommentRecyclerViewAdapter(this, dataList)
        rv_comment_commu_detail_act.adapter = communityDetailCommentRecyclerViewAdapter
        rv_comment_commu_detail_act.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    }

    //키보드 다운
    private fun downKeyboard(view: View) {
        val imm: InputMethodManager =
            applicationContext!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }


}