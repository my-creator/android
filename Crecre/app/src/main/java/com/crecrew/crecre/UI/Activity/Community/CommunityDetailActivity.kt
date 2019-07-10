package com.crecrew.crecre.UI.Activity.Community

import android.content.Context
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.PopupMenu
import com.crecrew.crecre.Data.CommentData
import com.crecrew.crecre.UI.Adapter.CommunityDetailCommentRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_community_detail.*
import android.widget.Toast
import com.bumptech.glide.Glide
import com.crecrew.crecre.Data.CommunitySmallNewGetData
import com.crecrew.crecre.Network.ApplicationController
import com.crecrew.crecre.Network.CommunityNetworkService
import com.crecrew.crecre.Network.Get.CommunityDetailData
import com.crecrew.crecre.Network.Get.CommunityRequestCntData
import com.crecrew.crecre.Network.Get.GetCommunitySmallNewPostResponse
import com.crecrew.crecre.Network.Get.GetPostDetailResponse
import com.crecrew.crecre.R
import com.crecrew.crecre.utils.ContentsDeleteDialog
import com.crecrew.crecre.utils.CustomRequestCompleteDialog
import kotlinx.android.synthetic.main.activity_community_detail.view.*
import org.jetbrains.anko.ctx
import org.jetbrains.anko.networkStatsManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CommunityDetailActivity : AppCompatActivity(), View.OnClickListener {

    var btn_like = 0
    var btn_unlike = 0
    var btn_anonymous = 0

    var title: String? = ""
    var postidx = -1
    var category_title: String? = ""

    lateinit var communityDetailCommentRecyclerViewAdapter: CommunityDetailCommentRecyclerViewAdapter

    val requestDialog: ContentsDeleteDialog by lazy {
        ContentsDeleteDialog(
            this@CommunityDetailActivity, "알림", "게시물을 정말 삭제하시겠어요?", "네"
            , "아니요", completeConfirmListener, completeNoListener
        )
    }

    val communityNetworkService: CommunityNetworkService by lazy {
        ApplicationController.instance.communityNetworkService
    }

    //click
    override fun onClick(v: View?) {
        when (v!!) {

            //추천 누르기
            btn_like_community_detail_act -> {

                if (btn_unlike == 1) {
                    btn_unlike_community_detail_act.isSelected = false
                    tv_unlike_community_detail_act.setTextColor(Color.parseColor("#a4a4a4"))
                    btn_unlike = 0

                    if (btn_like == 0) {
                        btn_like_community_detail_act.isSelected = true
                        tv_recommend_number_detail_act.setTextColor(Color.parseColor("#ff4343"))
                        btn_like = 1
                    } else {
                        btn_like_community_detail_act.isSelected = false
                        tv_recommend_number_detail_act.setTextColor(Color.parseColor("#a4a4a4"))
                        btn_like = 0
                    }

                } else {
                    if (btn_like == 0) {
                        btn_like_community_detail_act.isSelected = true
                        tv_recommend_number_detail_act.setTextColor(Color.parseColor("#ff4343"))
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

                if (btn_like == 1) {
                    btn_like_community_detail_act.isSelected = false
                    tv_recommend_number_detail_act.setTextColor(Color.parseColor("#a4a4a4"))
                    btn_like = 0

                    if (btn_unlike == 0) {
                        btn_unlike_community_detail_act.isSelected = true
                        tv_unlike_community_detail_act.setTextColor(Color.parseColor("#367fff"))
                        btn_unlike = 1
                    } else {
                        btn_unlike_community_detail_act.isSelected = false
                        tv_unlike_community_detail_act.setTextColor(Color.parseColor("#a4a4a4"))
                        btn_unlike = 0
                    }
                } else {
                    if (btn_unlike == 0) {
                        btn_unlike_community_detail_act.isSelected = true
                        tv_unlike_community_detail_act.setTextColor(Color.parseColor("#367fff"))
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

            //익명버튼
            btn_anonymous_detail_com_act -> {
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

            //글 삭제버튼(본인이 작성한 경우에만 해당됨)
            btn_delete_detail_community_act -> {
                showPopup(btn_delete_detail_community_act)
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community_detail)

        init()

        //configureRecyclerView()

        configurebasicSetting()
        getCommunityRecentAllResponse()

    }

    //팝업 클릭리스너
    val clickListener = View.OnClickListener { view ->
        when (view.id) {
            R.id.btn_delete_detail_community_act -> {
                showPopup(view)
            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val myMenuInflater = menuInflater
        myMenuInflater.inflate(R.menu.menu_main, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        return true
    }

    private fun init() {
        btn_like_community_detail_act.setOnClickListener(this)
        btn_unlike_community_detail_act.setOnClickListener(this)
        btn_back_community_detail_act.setOnClickListener(this)
        btn_anonymous_detail_com_act.setOnClickListener(this)
        rl_commu_detail_act.setOnClickListener(this)
        btn_delete_detail_community_act.setOnClickListener(this)
    }

/*
    //댓글 recyclerView
    private fun configureRecyclerView() {
        var dataList: ArrayList<CommunityDetailData> = ArrayList()

        communityDetailCommentRecyclerViewAdapter = CommunityDetailCommentRecyclerViewAdapter(this, dataList)
        rv_comment_commu_detail_act.adapter = communityDetailCommentRecyclerViewAdapter
        rv_comment_commu_detail_act.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    }
    */

    private fun configurebasicSetting() {
        category_title = intent.getStringExtra("category_title")
        title = intent.getStringExtra("title")
        postidx = intent.getIntExtra("postidx", -1)
    }

    //키보드 다운
    private fun downKeyboard(view: View) {
        val imm: InputMethodManager =
            applicationContext!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    //팝업창
    private fun showPopup(view: View) {
        var popup = PopupMenu(this, view)
        popup.inflate(R.menu.menu_main)

        popup.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item: MenuItem? ->

            when (item!!.itemId) {
                R.id.menu_modified -> {
                    Toast.makeText(this@CommunityDetailActivity, item.title, Toast.LENGTH_SHORT).show();
                }
                R.id.menu_delete -> {
                    requestDialog.show()
                }
            }

            true
        })

        popup.show()
    }

    //다이얼로그 -> 네
    private val completeConfirmListener = View.OnClickListener {
        requestDialog!!.dismiss()
        finish()
        //##글 삭제하는 서버통신
    }

    //다이얼로그 -> 아니요
    private val completeNoListener = View.OnClickListener {
        requestDialog!!.dismiss()
    }

    //통신 전체 보여주기
    private fun getCommunityRecentAllResponse() {

        val getCommunityDetail: Call<GetPostDetailResponse> = communityNetworkService.getPostDetailPostIdx(
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkeCI6MTIsImdyYWRlIjoiQURNSU4iLCJuYW1lIjoi66qF64uk7JewIiwiaWF0IjoxNTYyNDIzOTUyLCJleHAiOjE1NjM2MzM1NTIsImlzcyI6InlhbmcifQ.DbGROLSRyAm_NN1qcQ5sLmjxKpUACyMsFQRiDd2z3Lw",
            postidx
        )
        getCommunityDetail.enqueue(object : Callback<GetPostDetailResponse> {
            override fun onFailure(call: Call<GetPostDetailResponse>, t: Throwable) {
                Log.e("Community detail fail", t.toString())
            }
            override fun onResponse(call: Call<GetPostDetailResponse>, response: Response<GetPostDetailResponse>) {
                Log.e("is Successful",response.isSuccessful.toString())
                if(response.isSuccessful) {
                   // response.message()
                    //if(response.status == 200) {
                    val temp: ArrayList<CommunityDetailData> = response.body()!!.data
                    if(temp.size >= 0)
                        makeView(temp)
                    //}
                }
            }
        })
    }

    private fun makeView(dataList : ArrayList<CommunityDetailData>){
        //toolbar title
        tv_category_community_detail_act.text = dataList[0].board_name
        //contents title
        tv_title_community_detail_act.text = dataList[0].title

        //user_porfile
        if (dataList[0].profile_url == "")
            Glide.with(ctx).load(R.drawable.icn_profile).into(img_user_profile_circleImageView)
        else
            Glide.with(ctx).load(dataList[0].profile_url).into(img_user_profile_circleImageView)

        //nickname
        tv_user_nickname_detail_act.setText(dataList[0].nickname)

        //create_time
        tv_createtime_commu_detail_act.setText(dataList[0].create_time)

        //조회수
        tv_viewcnt_commu_detail_act.setText("조회수 " + dataList[0].view_cnt)

        //사진
        if (dataList[0].media_url == "")
            img_main_community_detail.visibility = View.INVISIBLE
        else
            Glide.with(ctx).load(dataList[0].media_url).into(img_main_community_detail)

        //글 내용
        tv_main_context_community_detail.text = "조회수 " + dataList[0].contents
    }

}