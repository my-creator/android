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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommunityHotPostActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var communityHotPostRecyclerViewAdapter:CommunityHotPostRecyclerViewAdapter

    val communityNetworkService: CommunityNetworkService by lazy {
        ApplicationController.instance.communityNetworkService
    }

    var post_idx = -1
    var user_idx = -1
    var flag = -1



    override fun onClick(v : View?) {
        when(v!!) {
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
                startActivity<CommunityWriteActivity>()
            }

        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community_hot_post)

        init()
        configureTitleBar()
        configureRecyclerView()



    }

    private fun configureTitleBar(){

        flag = intent.getIntExtra("flag",-1)
        user_idx = intent.getIntExtra("user_idx", -1)

        //첫 타이틀바 이름
        if(flag == 0)
        {
            tv_title_bar_hotpost_commu_act.text = "최신글"

            getCommunityRecentAllResponse(communityNetworkService.getCommunityAllNewPosts())

        }
        else if(flag == 1)
        {
            tv_title_bar_hotpost_commu_act.text = "인기글"

            getCommunityRecentAllResponse(communityNetworkService.getCommunityAllHotPosts())

        }
        else if(flag == -1)
            finish()

    }


   //recyclerView
    private fun configureRecyclerView() {
        var dataList: ArrayList<CommunitySmallNewGetData> = ArrayList()

        communityHotPostRecyclerViewAdapter = CommunityHotPostRecyclerViewAdapter(this, dataList,flag)
        rv_community_hotpost_act_list.adapter = communityHotPostRecyclerViewAdapter
        rv_community_hotpost_act_list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }


    //통신 전체 보여주기
    private fun getCommunityRecentAllResponse(networkfunction: Call<GetCommunitySmallNewPostResponse>) {
        val getCommunitySmallNewPosts : Call<GetCommunitySmallNewPostResponse> =networkfunction

        getCommunitySmallNewPosts.enqueue(object : Callback<GetCommunitySmallNewPostResponse> {

            override fun onFailure(call: Call<GetCommunitySmallNewPostResponse>, t: Throwable) {
                Log.e("최신글 전체 list fail", t.toString())
            }

            override fun onResponse(call: Call<GetCommunitySmallNewPostResponse>, response: Response<GetCommunitySmallNewPostResponse>) {

                if (response.isSuccessful) {
                    val temp : ArrayList<CommunitySmallNewGetData> = response.body()!!.data
                    if (temp.size > 0) {

                        val position = communityHotPostRecyclerViewAdapter.itemCount
                        communityHotPostRecyclerViewAdapter.dataList.addAll(temp)
                        communityHotPostRecyclerViewAdapter.notifyItemInserted(position)
                    }

                }

            }

        })

    }


    private fun init(){
        btn_back_hotpost_community_act.setOnClickListener(this)
        btn_search_community_hotpost_act.setOnClickListener(this)
        writing_btn_hotpost_community_act.setOnClickListener(this)
    }


}
