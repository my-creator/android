package com.crecrew.crecre.ui.fragment.Community

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.crecrew.crecre.network.ApplicationController
import com.crecrew.crecre.data.CommunitySmallGetData
import com.crecrew.crecre.network.get.GetCommunitySmallPostResponse
import com.crecrew.crecre.network.CommunityNetworkService

import com.crecrew.crecre.R
import com.crecrew.crecre.ui.activity.Community.CommunityHotPostActivity
import com.crecrew.crecre.ui.adapter.CommunityHotPostRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_community_popular.*
import kotlinx.android.synthetic.main.fragment_community_popular.view.*
import org.jetbrains.anko.support.v4.startActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommunityPopularFragment : Fragment() {

    lateinit var communityPopularRecyclerViewAdapter: CommunityHotPostRecyclerViewAdapter
    private lateinit var rootView: View

    val communityNetworkService: CommunityNetworkService by lazy {
        ApplicationController.instance.communityNetworkService
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        rootView =  inflater.inflate(R.layout.fragment_community_popular, container, false)

        //더보기
        rootView.btn_more_community_popular_fg.setOnClickListener {
            startActivity<CommunityHotPostActivity>("flag" to 1, "title" to "인기글")
        }



        return rootView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView()
        getCommunityRecentResponse()
    }

    //인기글 RecyclerView
    private fun setRecyclerView() {

        var dataList: ArrayList<CommunitySmallGetData> = ArrayList()

        communityPopularRecyclerViewAdapter = CommunityHotPostRecyclerViewAdapter(context!!, dataList,1)
        rv_popular_community_fg.adapter = communityPopularRecyclerViewAdapter
        rv_popular_community_fg.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

    }

    //인기글 5개 보여주기
    private fun getCommunityRecentResponse() {
        val getCommunitySmallNewPosts : Call<GetCommunitySmallPostResponse> = communityNetworkService.getCommunitySmallHotPosts()

        getCommunitySmallNewPosts.enqueue(object : Callback<GetCommunitySmallPostResponse> {

            override fun onFailure(call: Call<GetCommunitySmallPostResponse>, t: Throwable) {
                Log.e("최신글 5개 list fail", t.toString())
            }

            override fun onResponse(call: Call<GetCommunitySmallPostResponse>, response: Response<GetCommunitySmallPostResponse>) {

                if (response.isSuccessful) {
                    val temp : ArrayList<CommunitySmallGetData> = response.body()!!.data
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
