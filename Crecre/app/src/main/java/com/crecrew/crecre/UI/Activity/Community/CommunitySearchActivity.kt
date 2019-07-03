package com.crecrew.crecre.UI.Activity.Community

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.crecrew.crecre.Data.CommunityHotPostData
import com.crecrew.crecre.Data.SearchResultData
import com.crecrew.crecre.R
import com.crecrew.crecre.UI.Adapter.CommunityHotPostRecyclerViewAdapter
import com.crecrew.crecre.UI.Adapter.CoummunitySearchRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_community_hot_post.*
import kotlinx.android.synthetic.main.activity_community_search.*
import org.jetbrains.anko.startActivity

class CommunitySearchActivity : AppCompatActivity() {

    lateinit var communitysearchRecyclerViewAdapter : CoummunitySearchRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community_search)

/*

        rl_after_search_view.visibility = View.GONE
        ll_first_search_view_com_act.visibility = View.VISIBLE
*/

        btn_back_community_search.setOnClickListener {
            finish()
        }

        btn_find_search_com_act.setOnClickListener {
            rl_after_search_view.visibility = View.VISIBLE
            ll_first_search_view_com_act.visibility = View.GONE
        }

        btn_search_request_community_act.setOnClickListener {
            startActivity<CommunityRequestActivity>()
        }



        configureRecyclerView()
    }

    //recyclerView
    private fun configureRecyclerView() {
        var dataList: ArrayList<SearchResultData> = ArrayList()
        dataList.add(
            SearchResultData("햇님이",0)
        )
        dataList.add(
            SearchResultData("햇님123123",0)
        )
        dataList.add(
            SearchResultData("입짧은 햇님이와 친구엥ㅇ",1)
        )
        dataList.add(
            SearchResultData("햇님",0)
        )
        dataList.add(
            SearchResultData("햇님무이",1)
        )


        communitysearchRecyclerViewAdapter = CoummunitySearchRecyclerViewAdapter(this, dataList)
        rv_search_result_com_sear_act.adapter = communitysearchRecyclerViewAdapter
        rv_search_result_com_sear_act.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    }




}
