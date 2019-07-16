package com.crecrew.crecre.UI.Activity.Community

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.crecrew.crecre.Data.CommunitySmallGetData
import com.crecrew.crecre.Network.ApplicationController
import com.crecrew.crecre.Network.Get.GetCommunitySmallPostResponse
import com.crecrew.crecre.Network.CommunityNetworkService
import com.crecrew.crecre.R
import com.crecrew.crecre.UI.Adapter.CommunityHotPostRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_community_hot_post.*
import org.jetbrains.anko.startActivity
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
    var size1 = -1
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
        //configureTitleBar()
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
            getCommunityCommentResponse()
            getCommunityRecentAllResponse(communityNetworkService.getPostListAllBoards(board_idx))

            if(size ==0 && size1 ==0)
                rl_noresult_commu_hot_post_act.visibility = View.VISIBLE


            /*
            getCommunityRecentAllResponse(communityNetworkService.getPostListBoards(board_idx))
            getCommunityRecentAllResponse(communityNetworkService.getPostListAllBoards(board_idx))*/

        }
    }

    //recyclerView
    private fun configureRecyclerView() {
        var dataList: ArrayList<CommunitySmallGetData> = ArrayList()

        communityHotPostRecyclerViewAdapter = CommunityHotPostRecyclerViewAdapter(this, dataList, flag)
        rv_community_hotpost_act_list.adapter = communityHotPostRecyclerViewAdapter
        rv_community_hotpost_act_list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    //최신글, 인기글
    private fun getCommunityRecentAllResponse(networkfunction: Call<GetCommunitySmallPostResponse>) {
        val getCommunitySmallNewPosts: Call<GetCommunitySmallPostResponse> = networkfunction

        getCommunitySmallNewPosts.enqueue(object : Callback<GetCommunitySmallPostResponse> {

            override fun onFailure(call: Call<GetCommunitySmallPostResponse>, t: Throwable) {
                Log.e("최신글 전체 list fail", t.toString())
            }

            override fun onResponse(call: Call<GetCommunitySmallPostResponse>, response: Response<GetCommunitySmallPostResponse>
            ) {

                val temp: ArrayList<CommunitySmallGetData> = response.body()!!.data

                Log.v("TAGG : size1 ", temp.size.toString())

                if (temp.size == 0) {
                    size1 = 0
                }
                if (response.isSuccessful) {

                    Log.v("community", response.message())
                    Log.v("community", temp.size.toString())

                    //##확인필요

                    for (i in 0..temp.size - 1)
                        Log.v("community", temp[i].contents)

                    if (temp.size > 0) {
                        val position = communityHotPostRecyclerViewAdapter.itemCount
                        communityHotPostRecyclerViewAdapter.dataList.addAll(temp)
                        communityHotPostRecyclerViewAdapter.notifyItemChanged(position)

                    }

                   if(size ==0 && size1 ==0)
                    {
                        rl_noresult_commu_hot_post_act.visibility = View.VISIBLE
                    }
                }

            }
        })
    }

    //게시판별 게시글 통신
    private fun getCommunityCommentResponse() {
        val getCommunitySmallNewPosts: Call<GetCommunitySmallPostResponse> =
            communityNetworkService.getPostListBoards(board_idx)

        getCommunitySmallNewPosts.enqueue(object : Callback<GetCommunitySmallPostResponse> {

            override fun onFailure(call: Call<GetCommunitySmallPostResponse>, t: Throwable) {
                Log.e("게시판별 게시글 통신 list fail", t.toString())
            }

            override fun onResponse(
                call: Call<GetCommunitySmallPostResponse>,
                response: Response<GetCommunitySmallPostResponse>
            ) {

                val temp: ArrayList<CommunitySmallGetData> = response.body()!!.data
                Log.v("TAGG : size", temp.size.toString())
                //##확인필요
                if (temp.size == 0) {
                    size =0

                }

                if (response.isSuccessful) {

                    Log.v("community", response.message())
                    Log.v("community", temp.size.toString())


                    for (i in 0..temp.size - 1)
                        Log.v("community", temp[i].contents)

                    if (temp.size > 0) {
                        val position = communityHotPostRecyclerViewAdapter.itemCount
                        communityHotPostRecyclerViewAdapter.dataList.addAll(temp)
                        communityHotPostRecyclerViewAdapter.notifyItemChanged(position)

                    }
                    if(size ==0 && size1 ==0)
                    {
                        rl_noresult_commu_hot_post_act.visibility = View.VISIBLE
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
