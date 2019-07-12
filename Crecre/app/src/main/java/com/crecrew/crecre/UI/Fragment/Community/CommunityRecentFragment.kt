package com.crecrew.crecre.UI.Fragment.Community

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.crecrew.crecre.Network.ApplicationController
import com.crecrew.crecre.Data.CommunitySmallNewGetData
import com.crecrew.crecre.Network.Get.GetCommunitySmallNewPostResponse
import com.crecrew.crecre.Network.CommunityNetworkService

import com.crecrew.crecre.R
import com.crecrew.crecre.UI.Activity.Community.CommunityHotPostActivity
import com.crecrew.crecre.UI.Adapter.CommunityHotPostRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_community_popular.*
import kotlinx.android.synthetic.main.fragment_community_popular.view.*
import org.jetbrains.anko.support.v4.startActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommunityRecentFragment : Fragment() {

    lateinit var communityPopularRecyclerViewAdapter: CommunityHotPostRecyclerViewAdapter

    val communityNetworkService: CommunityNetworkService by lazy {
        ApplicationController.instance.communityNetworkService
    }

    private lateinit var rootView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        rootView = inflater.inflate(R.layout.fragment_community_popular, container, false)

        //더보기 버튼
        rootView.btn_more_community_popular_fg.setOnClickListener {
            startActivity<CommunityHotPostActivity>("flag" to 0, "title" to "최신글" )

        }

        return rootView

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView()
        getCommunityRecentResponse()
    }


    //최신글 RecyclerView
    private fun setRecyclerView() {

        var dataList: ArrayList<CommunitySmallNewGetData> = ArrayList()

        communityPopularRecyclerViewAdapter = CommunityHotPostRecyclerViewAdapter(context!!, dataList,0)
        rv_popular_community_fg.adapter = communityPopularRecyclerViewAdapter
        rv_popular_community_fg.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

    }

    //최신글 5개 보여주기
    private fun getCommunityRecentResponse() {
        val getCommunitySmallNewPosts : Call<GetCommunitySmallNewPostResponse> = communityNetworkService.getCommunitySmallNewPosts()

        getCommunitySmallNewPosts.enqueue(object : Callback<GetCommunitySmallNewPostResponse> {

            override fun onFailure(call: Call<GetCommunitySmallNewPostResponse>, t: Throwable) {
                Log.e("최신글 5개 list fail", t.toString())
            }

            override fun onResponse(call: Call<GetCommunitySmallNewPostResponse>, response: Response<GetCommunitySmallNewPostResponse>) {

                if (response.isSuccessful) {
                    val temp : ArrayList<CommunitySmallNewGetData> = response.body()!!.data
                    if (temp.size > 0) {

                        val position = communityPopularRecyclerViewAdapter.itemCount
                        communityPopularRecyclerViewAdapter.dataList.addAll(temp)
                        communityPopularRecyclerViewAdapter.notifyItemInserted(position)
                    }

                }

            }

        })

    }
}
