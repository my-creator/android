package com.crecrew.crecre.UI.Fragment

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.crecrew.crecre.Data.TodayRankData
import com.crecrew.crecre.R
import com.crecrew.crecre.UI.Adapter.TodayRankRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_home_today_rank.*
import android.view.animation.AnimationUtils
import com.crecrew.crecre.Data.CreatorData
import com.crecrew.crecre.Network.ApplicationController
import com.crecrew.crecre.Network.CreatorNetworkService
import com.crecrew.crecre.Network.Get.GetCreatorTodayHotRank
import com.crecrew.crecre.UI.View.SimpleDividerItemDecoration
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeTodayRankFragment() : Fragment(){

    lateinit var todayRankRecyclerViewAdapter: TodayRankRecyclerViewAdapter

    var dataList: ArrayList<CreatorData> = ArrayList()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home_today_rank, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureRecyclerview()
    }


    public fun setList(list: ArrayList<CreatorData>){
        dataList = list
    }

    private fun configureRecyclerview(){

        todayRankRecyclerViewAdapter = TodayRankRecyclerViewAdapter(activity!!,dataList)
        fragment_home_today_rank_rv.adapter = todayRankRecyclerViewAdapter
        fragment_home_today_rank_rv.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        fragment_home_today_rank_rv.addItemDecoration(SimpleDividerItemDecoration(Color.parseColor("#eaeaea"), 1))
    }


    private fun animateRV(){
        val controller = AnimationUtils.loadLayoutAnimation(activity!!, R.anim.fade_in_anim)

        fragment_home_today_rank_rv.setLayoutAnimation(controller)
        todayRankRecyclerViewAdapter.notifyDataSetChanged()
        fragment_home_today_rank_rv.scheduleLayoutAnimation()
    }




}