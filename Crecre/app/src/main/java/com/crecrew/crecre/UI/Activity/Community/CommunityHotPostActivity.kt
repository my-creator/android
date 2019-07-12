package com.crecrew.crecre.UI.Activity.Community

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.crecrew.crecre.Data.CommunitySmallNewGetData
import com.crecrew.crecre.Network.ApplicationController
import com.crecrew.crecre.Network.Get.GetCommunitySmallNewPostResponse
import com.crecrew.crecre.Network.CommunityNetworkService
import com.crecrew.crecre.R
import com.crecrew.crecre.UI.Adapter.CommunityHotPostRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_community_hot_post.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommunityHotPostActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var communityHotPostRecyclerViewAdapter: CommunityHotPostRecyclerViewAdapter

    val communityNetworkService: CommunityNetworkService by lazy {
        ApplicationController.instance.communityNetworkService
    }

    var board_idx = -1
    var user_idx = -1
    var flag = -1
    var title = ""
    var size = -1
    var is_anonymous = -1


    override fun onClick(v: View?) {
        when (v!!) {
            //뒤로가기버튼
            btn_back_hotpost_community_act -> {
                finish()
            }
            //검색버튼
            btn_search_community_hotpost_act -> {
                startActivity<CommunitySearchActivity>()
            }
            //글 작성버튼
            writing_btn_hotpost_community_act -> {
                startActivity<CommunityWriteActivity>("boardIdx" to board_idx)
            }

        }

    }

    override fun onResume() {
        super.onResume()


        configureTitleBar()
        configureRecyclerView()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community_hot_post)

        init()
    }

    private fun configureTitleBar() {

        flag = intent.getIntExtra("flag", -1)
        user_idx = intent.getIntExtra("user_idx", -1)
        board_idx = intent.getIntExtra("idx", -1)
        Log.v("TAGG", board_idx.toString())
        title = intent.getStringExtra("title")

        tv_title_bar_hotpost_commu_act.text = title

        //첫 타이틀바 이름
        if (flag == 0) {
            tv_title_bar_hotpost_commu_act.text = "최신글"
            writing_btn_hotpost_community_act.visibility = View.GONE
            getCommunityRecentAllResponse(communityNetworkService.getCommunityAllNewPosts())

        } else if (flag == 1) {
            tv_title_bar_hotpost_commu_act.text = "인기글"
            writing_btn_hotpost_community_act.visibility = View.GONE
            getCommunityRecentAllResponse(communityNetworkService.getCommunityAllHotPosts())

        } else {
            //##hot인기글 3개가 먼저 나오도록 해야함 --> 통신 속도?때문에 그런가 먼저 나올때도 있고 아닐때도 있음...
            getCommunityRecentAllResponse(communityNetworkService.getPostListBoards(board_idx))
            getCommunityRecentAllResponse(communityNetworkService.getPostListAllBoards(board_idx))

        }

    }


    //recyclerView
    private fun configureRecyclerView() {
        var dataList: ArrayList<CommunitySmallNewGetData> = ArrayList()

        communityHotPostRecyclerViewAdapter = CommunityHotPostRecyclerViewAdapter(this, dataList, flag)
        rv_community_hotpost_act_list.adapter = communityHotPostRecyclerViewAdapter
        rv_community_hotpost_act_list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }


    //통신 전체 보여주기
    private fun getCommunityRecentAllResponse(networkfunction: Call<GetCommunitySmallNewPostResponse>) {
        val getCommunitySmallNewPosts: Call<GetCommunitySmallNewPostResponse> = networkfunction

        getCommunitySmallNewPosts.enqueue(object : Callback<GetCommunitySmallNewPostResponse> {

            override fun onFailure(call: Call<GetCommunitySmallNewPostResponse>, t: Throwable) {
                Log.e("최신글 전체 list fail", t.toString())
            }

            override fun onResponse(
                call: Call<GetCommunitySmallNewPostResponse>,
                response: Response<GetCommunitySmallNewPostResponse>
            ) {

                val temp: ArrayList<CommunitySmallNewGetData> = response.body()!!.data

                if (response.isSuccessful) {

                    Log.v("community", response.message())
                    for (i in 0..temp.size - 1)
                        Log.v("community", temp[i].contents)

                    if (temp.size > 0) {
                        val position = communityHotPostRecyclerViewAdapter.itemCount
                        communityHotPostRecyclerViewAdapter.dataList.addAll(temp)
                        communityHotPostRecyclerViewAdapter.notifyItemInserted(position)

                    }
                }


            }
        })
    }

    private fun init() {
        btn_back_hotpost_community_act.setOnClickListener(this)
        btn_search_community_hotpost_act.setOnClickListener(this)
        writing_btn_hotpost_community_act.setOnClickListener(this)
    }
}
