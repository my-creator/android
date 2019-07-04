package com.crecrew.crecre.UI.Activity.Community

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.crecrew.crecre.Data.CommunityHotPostData
import com.crecrew.crecre.R
import com.crecrew.crecre.UI.Adapter.CommunityHotPostRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_community_detail.*
import kotlinx.android.synthetic.main.activity_community_hot_post.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class CommunityDetailActivity : AppCompatActivity(), View.OnClickListener {

    var btn_like = 0
    var btn_unlike = 0
    lateinit var communityHotPostRecyclerViewAdapter : CommunityHotPostRecyclerViewAdapter

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
        //configureRecyclerView()
    }

    private fun init() {
        btn_like_community_detail_act.setOnClickListener(this)
        btn_unlike_community_detail_act.setOnClickListener(this)
        btn_back_community_detail_act.setOnClickListener(this)
    }
/*
    //recyclerView
    private fun configureRecyclerView() {
        var dataList: ArrayList<CommunityHotPostData> = ArrayList()
        dataList.add(
            CommunityHotPostData("http://sopt.org/wp/wp-content/uploads/2014/01/24_SOPT-LOGO_COLOR-1.png",1,
                "햇님이 먹었던 과자 브랜드 이게 맞나?", 10, 10, "18:47","",0,0)
        )
        dataList.add(
            CommunityHotPostData("", 1,
                "안녀안알ㄴ여ㅏㄴ여낭", 1, 8, "14:27","",0,0)
        )
        dataList.add(
            CommunityHotPostData("",1,
                "입짧은햇님보세여", 19, 19, "12:35","",0,0)
        )
        dataList.add(
            CommunityHotPostData("http://sopt.org/wp/wp-content/uploads/2014/01/24_SOPT-LOGO_COLOR-1.png",0,
                "먹방요정", 4, 8, "11:20","",0,0)
        )
        dataList.add(
            CommunityHotPostData("http://sopt.org/wp/wp-content/uploads/2014/01/24_SOPT-LOGO_COLOR-1.png",0,
                "바부야", 2, 7, "04:30","",0,0)
        )

        communityHotPostRecyclerViewAdapter = CommunityHotPostRecyclerViewAdapter(this, dataList)
        rv_community_hotpost_act_list.adapter = communityHotPostRecyclerViewAdapter
        rv_community_hotpost_act_list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    }*/
}
