package com.crecrew.crecre.UI.Fragment

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.crecrew.crecre.Data.TodayRankData
import com.crecrew.crecre.R
import com.crecrew.crecre.UI.Adapter.TodayRankRecyclerViewAdapter
import com.crecrew.crecre.UI.View.SimpleDividerItemDecoration
import kotlinx.android.synthetic.main.fragment_home_today_rank_top.*

class HomeTodayRankTopFragment : Fragment(){

    lateinit var todayRankRecyclerViewAdapter: TodayRankRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_home_today_rank_top, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureRecyclerView()
        animateRV()
    }


/*
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            animateRV()
        }
    }
*/
    private fun configureRecyclerView(){

        var dataList: ArrayList<TodayRankData> = ArrayList()
        dataList.clear()
        dataList.add(TodayRankData(1,"몬스타엑스",2019))
        dataList.add(TodayRankData(2,"호호",1254))
        dataList.add(TodayRankData(3,"누누곰",242))
        dataList.add(TodayRankData(4,"창규니",318))
        dataList.add(TodayRankData(5,"채꼬북",-14))
        //dataList.add(TodayRankData(6,"밍곰",24))

        todayRankRecyclerViewAdapter = TodayRankRecyclerViewAdapter(activity!!, dataList, 1)
        fragment_home_today_rank_top_rv.adapter = todayRankRecyclerViewAdapter
        fragment_home_today_rank_top_rv.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        fragment_home_today_rank_top_rv.addItemDecoration(SimpleDividerItemDecoration(Color.parseColor("#eaeaea"), 1))

    }

    private fun animateRV(){
        val controller = AnimationUtils.loadLayoutAnimation(activity, R.anim.fade_in_anim)

        fragment_home_today_rank_top_rv.setLayoutAnimation(controller)
        todayRankRecyclerViewAdapter.notifyDataSetChanged()
        fragment_home_today_rank_top_rv.scheduleLayoutAnimation()
    }
}