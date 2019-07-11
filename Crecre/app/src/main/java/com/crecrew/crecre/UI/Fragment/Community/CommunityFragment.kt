package com.crecrew.crecre.UI.Fragment.Community

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.crecrew.crecre.Network.ApplicationController
import com.crecrew.crecre.Network.Get.CommunityBoardData
import com.crecrew.crecre.Network.Get.GetCommunityUnlikeBoardsResponse
import com.crecrew.crecre.Network.CommunityNetworkService
import com.crecrew.crecre.R
import com.crecrew.crecre.UI.Activity.Community.CommunitySearchActivity
import com.crecrew.crecre.UI.Adapter.CommunityFavoriteRecyclerViewAdapter
import com.crecrew.crecre.UI.Adapter.CommunityPostFragmentAdapter
import kotlinx.android.synthetic.main.fragment_community.*
import kotlinx.android.synthetic.main.fragment_community.view.*
import org.jetbrains.anko.support.v4.startActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommunityFragment : Fragment(), CommunityFavoriteRecyclerViewAdapter.OnItemClickListener {

    private lateinit var rootView: View
    //즐겨찾기 Adapter
    lateinit var communityfavoriteRecyclerViewAdapter: CommunityFavoriteRecyclerViewAdapter
    lateinit var communityPostListRecyclerViewAdapter: CommunityFavoriteRecyclerViewAdapter

    var favoriteList: ArrayList<CommunityBoardData> = ArrayList()
    var allList: ArrayList<CommunityBoardData> = ArrayList()

    val communityNetworkService: CommunityNetworkService by lazy {
        ApplicationController.instance.communityNetworkService
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_community, container, false)

        //검색버튼을 눌렀을때
        rootView.btn_search_community_frag.setOnClickListener() {
            //게시판
            startActivity<CommunitySearchActivity>("board_flag" to 0)
        }

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        configureMainTab()


//        tablayoutTextColor()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()

    }

    private fun setRecyclerView() {


      /*  var dataList: ArrayList<CommunityBoardData> = ArrayList()

        //즐겨찾기 rv
        getCommunityRecentResponse(communityNetworkService.getCommunityLikeBoards(
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkeCI6MTIsImdyYWRlIjoiQURNSU4iLCJuYW1lIjoi66qF64uk7JewIiwiaWF0IjoxNTYyNDIzOTUyLCJleHAiOjE1NjM2MzM1NTIsImlzcyI6InlhbmcifQ.DbGROLSRyAm_NN1qcQ5sLmjxKpUACyMsFQRiDd2z3Lw"))
        communityfavoriteRecyclerViewAdapter = CommunityFavoriteRecyclerViewAdapter(activity!!, dataList,0)
        rv_favorite_community_frag.adapter = communityfavoriteRecyclerViewAdapter
        rv_favorite_community_frag.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        //일반 게시글 rv
        getCommunityRecentResponse(communityNetworkService.getCommunityUnlikeBoards(
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkeCI6MTIsImdyYWRlIjoiQURNSU4iLCJuYW1lIjoi66qF64uk7JewIiwiaWF0IjoxNTYyNDIzOTUyLCJleHAiOjE1NjM2MzM1NTIsImlzcyI6InlhbmcifQ.DbGROLSRyAm_NN1qcQ5sLmjxKpUACyMsFQRiDd2z3Lw"))
        communityPostListRecyclerViewAdapter = CommunityFavoriteRecyclerViewAdapter(activity!!, dataList,1)
        rv_postlist_community_fg.adapter = communityPostListRecyclerViewAdapter
        rv_postlist_community_fg.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)*/

        //일반 게시글 rv
        getBoard(false, null)
        //즐겨찾기 rv
        getBoard(true, null)

    }

    //ViewPager
    private fun configureMainTab() {

        vp_community_post_frag.adapter = CommunityPostFragmentAdapter(fragmentManager!!, 2)
        vp_community_post_frag.offscreenPageLimit = 1

        /*     tl_community_post_frag.run {
                 setupWithViewPager(vp_community_post_frag)

                 getTabAt(0)!!.setText("최신글")
                 getTabAt(1)!!.setText("인기글")
             }
             */

        tl_community_post_frag.setupWithViewPager(vp_community_post_frag)
        val topTabLayout: View = activity!!.layoutInflater.inflate(R.layout.fragment_community_navi, null, false)
        tl_community_post_frag.getTabAt(0)!!.customView =
            topTabLayout.findViewById(R.id.rl_recentpost_community_navi_frag) as RelativeLayout
        tl_community_post_frag.getTabAt(1)!!.customView =
            topTabLayout.findViewById(R.id.rl_popularpost_community_navi_frag) as RelativeLayout

    }

    //즐겨찾기 누른, 안누른 게시글 보여주기
 /*   private fun getCommunityRecentResponse(networkFunction : Call<GetCommunityUnlikeBoardsResponse>) {
        val getCommunityUnlikeBoards : Call<GetCommunityUnlikeBoardsResponse> = networkFunction*/

    //좋아요안누른 게시글 보여주기
    private fun getCommunityRecentResponse(networkFunction: Call<GetCommunityUnlikeBoardsResponse>) {
        val getCommunityUnlikeBoards: Call<GetCommunityUnlikeBoardsResponse> = networkFunction


        getCommunityUnlikeBoards.enqueue(object : Callback<GetCommunityUnlikeBoardsResponse> {

            override fun onFailure(call: Call<GetCommunityUnlikeBoardsResponse>, t: Throwable) {
                Log.e("즐겨찾기나 아닌거나 list fail", t.toString())
            }

            override fun onResponse(
                call: Call<GetCommunityUnlikeBoardsResponse>,
                response: Response<GetCommunityUnlikeBoardsResponse>
            ) {

                if (response.isSuccessful) {

                   /* val temp : ArrayList<CommunityBoardData> = response.body()!!.data
                    Log.v("TAGG", temp.size.toString())*/

                   /* for(i in 0..temp.size-1)
                        Log.v("click!",  temp[i].name)*/
                    val temp: ArrayList<CommunityBoardData> = response.body()!!.data

                    if (temp.size > 0) {

                        val position = communityPostListRecyclerViewAdapter.itemCount
                        communityPostListRecyclerViewAdapter.dataList.addAll(temp)
                        communityPostListRecyclerViewAdapter.notifyItemInserted(position)
                    }
                }
            }
        })
    }

    //좋아요안누른 게시글 보여주기
    private fun getBoard(isFavorite: Boolean, postion: Int?) {
        lateinit var getBoardResponse: Call<GetCommunityUnlikeBoardsResponse>

        if (isFavorite) {

            getBoardResponse =
                communityNetworkService.getCommunityLikeBoards("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkeCI6MTIsImdyYWRlIjoiQURNSU4iLCJuYW1lIjoi66qF64uk7JewIiwiaWF0IjoxNTYyNDIzOTUyLCJleHAiOjE1NjM2MzM1NTIsImlzcyI6InlhbmcifQ.DbGROLSRyAm_NN1qcQ5sLmjxKpUACyMsFQRiDd2z3Lw")
            getBoardResponse.enqueue(object : Callback<GetCommunityUnlikeBoardsResponse> {
                override fun onFailure(call: Call<GetCommunityUnlikeBoardsResponse>, t: Throwable) {
                    Log.e("즐겨찾기나 아닌거나 list fail", t.toString())
                }

                override fun onResponse(
                    call: Call<GetCommunityUnlikeBoardsResponse>,
                    response: Response<GetCommunityUnlikeBoardsResponse>
                ) {
                    if (response.isSuccessful) {
                        favoriteList.clear()
                        favoriteList.addAll(response.body()!!.data)
                        communityfavoriteRecyclerViewAdapter =
                            CommunityFavoriteRecyclerViewAdapter(activity!!, favoriteList, 1)
                        communityfavoriteRecyclerViewAdapter.notifyDataSetChanged()
                        communityfavoriteRecyclerViewAdapter.setOnItemClickListener(this@CommunityFragment)
                        rv_favorite_community_frag.adapter = communityfavoriteRecyclerViewAdapter
                    }
                } 
            })

        } else {
            postion?.let {
                if (allList.size > 0) {
                    allList.removeAt(postion)
                    communityPostListRecyclerViewAdapter.notifyDataSetChanged()
                }
            }

            getBoardResponse =
                communityNetworkService.getCommunityUnlikeBoards("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkeCI6MTIsImdyYWRlIjoiQURNSU4iLCJuYW1lIjoi66qF64uk7JewIiwiaWF0IjoxNTYyNDIzOTUyLCJleHAiOjE1NjM2MzM1NTIsImlzcyI6InlhbmcifQ.DbGROLSRyAm_NN1qcQ5sLmjxKpUACyMsFQRiDd2z3Lw")
            getBoardResponse.enqueue(object : Callback<GetCommunityUnlikeBoardsResponse> {
                override fun onFailure(call: Call<GetCommunityUnlikeBoardsResponse>, t: Throwable) {
                    Log.e("즐겨찾기나 아닌거나 list fail", t.toString())
                }

                override fun onResponse(
                    call: Call<GetCommunityUnlikeBoardsResponse>,
                    response: Response<GetCommunityUnlikeBoardsResponse>
                ) {
                    if (response.isSuccessful) {
                        allList.clear()
                        allList.addAll(response.body()!!.data)
                        communityPostListRecyclerViewAdapter =
                            CommunityFavoriteRecyclerViewAdapter(activity!!, allList, 1)
                        communityPostListRecyclerViewAdapter.setOnItemClickListener(this@CommunityFragment)
                        rv_postlist_community_fg.adapter = communityPostListRecyclerViewAdapter
                    }
                }
            })

        }
    }

    override fun onItemClicked(postion: Int) {
        //일반 게시글 rv
        getBoard(false, postion)
        //즐겨찾기 rv
        getBoard(true, postion)
    }
}