package com.crecrew.crecre.UI.Activity.Community

import android.content.Context
import android.graphics.Color
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
import kotlinx.android.synthetic.main.activity_community_write.*
import org.jetbrains.anko.textColor

class CommunityDetailActivity : AppCompatActivity(), View.OnClickListener {

    var btn_like = 0
    var btn_unlike = 0
    var btn_anonymous =0
    lateinit var communityDetailCommentRecyclerViewAdapter:CommunityDetailCommentRecyclerViewAdapter

    //click
    override fun onClick(v: View?) {
        when (v!!) {

            //추천 누르기
            btn_like_community_detail_act -> {

                if(btn_unlike == 1){
                    btn_unlike_community_detail_act.isSelected = false
                    tv_unlike_community_detail_act.setTextColor(Color.parseColor("#a4a4a4"))
                    btn_unlike = 0

                    if (btn_like == 0) {
                        btn_like_community_detail_act.isSelected = true
                        tv_recommend_number_detail_act.setTextColor(Color.parseColor("#ff57f7"))
                        btn_like = 1
                    } else {
                        btn_like_community_detail_act.isSelected = false
                        tv_recommend_number_detail_act.setTextColor(Color.parseColor("#a4a4a4"))
                        btn_like = 0
                    }

                }
                else {
                    if (btn_like == 0) {
                        btn_like_community_detail_act.isSelected = true
                        tv_recommend_number_detail_act.setTextColor(Color.parseColor("#ff57f7"))
                        btn_like = 1
                    } else {
                        btn_like_community_detail_act.isSelected = false
                        tv_recommend_number_detail_act.setTextColor(Color.parseColor("#a4a4a4"))
                        btn_like = 0
                    }
                }
            }

            //비추천 누르기
            btn_unlike_community_detail_act -> {

                if(btn_like == 1)
                {
                    btn_like_community_detail_act.isSelected = false
                    tv_recommend_number_detail_act.setTextColor(Color.parseColor("#a4a4a4"))
                    btn_like = 0

                    if (btn_unlike == 0) {
                        btn_unlike_community_detail_act.isSelected = true
                        tv_unlike_community_detail_act.setTextColor(Color.parseColor("#36ebf0"))
                        btn_unlike = 1
                    } else {
                        btn_unlike_community_detail_act.isSelected = false
                        tv_unlike_community_detail_act.setTextColor(Color.parseColor("#a4a4a4"))
                        btn_unlike = 0
                    }
                }
                else
                {
                    if (btn_unlike == 0) {
                        btn_unlike_community_detail_act.isSelected = true
                        tv_unlike_community_detail_act.setTextColor(Color.parseColor("#36ebf0"))
                        btn_unlike = 1
                    } else {
                        btn_unlike_community_detail_act.isSelected = false
                        tv_unlike_community_detail_act.setTextColor(Color.parseColor("#a4a4a4"))
                        btn_unlike = 0
                    }
                }
            }

            //뒤로가기 버튼
            btn_back_community_detail_act -> {
                finish()
            }

            btn_anonymous_detail_com_act -> {
                //익명버튼
                if (btn_anonymous == 0) {
                    btn_anonymous_detail_com_act.isSelected = true
                    btn_anonymous = 1
                } else {
                    btn_anonymous_detail_com_act.isSelected = false
                    btn_anonymous = 0
                }
            }

            //키보드 down
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
        btn_anonymous_detail_com_act.setOnClickListener(this)
        rl_commu_detail_act.setOnClickListener(this)
    }

    //recyclerView
    private fun configureRecyclerView() {
        var dataList: ArrayList<CommentData> = ArrayList()
        dataList.add(
            CommentData("http://www.figurepresso.com/web/product/big/201708/7531_shop1_593433.jpg","양시연영상",
                "18초전",  "시연이는 알린을 좋아해,,")
        )
        dataList.add(
            CommentData("https://duckyworld.co.kr/web/product/big/201710/523_shop1_327628.jpg",
                "안녀안알ㄴ여ㅏㄴ여낭", "14:27","내가 무슨 부귀영화를 누리려고 이 글에 들어왔니?")
        )
        dataList.add(
            CommentData("http://ecx.images-amazon.com/images/I/41oIsVytUOL.jpg","유가희",
                "1분전",  "명망먀여망며아명마염")
        )
        dataList.add(
            CommentData("https://scontent-lga3-1.cdninstagram.com/vp/f899f4cda10d8ac144041dbb4bb1240c/5DB1453F/t51.2885-15/e35/65610653_125382465361781_2677424777804115689_n.jpg?_nc_ht=scontent-lga3-1.cdninstagram.com","양희찬",
                "1시간전",  "김!애!용!!")
        )
        dataList.add(
            CommentData("http://stimg.afreecatv.com/NORMAL_BBS/7/16469567/16505c6f9f85df905.jpeg","김애용",
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