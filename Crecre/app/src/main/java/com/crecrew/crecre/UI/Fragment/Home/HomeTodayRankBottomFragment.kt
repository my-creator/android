package com.crecrew.crecre.UI.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.crecrew.crecre.Data.TodayRankData
import com.crecrew.crecre.R
import com.crecrew.crecre.UI.Adapter.TodayRankRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_home_today_rank_bottom.*
import android.view.animation.AnimationUtils



class HomeTodayRankBottomFragment : Fragment(){

    lateinit var todayRankRecyclerViewAdapter: TodayRankRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_home_today_rank_bottom, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureRecyclerView()

    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            animateRV()
        }
    }

    private fun configureRecyclerView(){

        var dataList: ArrayList<TodayRankData> = ArrayList()
        dataList.clear()
        //dataList.add(TodayRankData(5,"호호",2019))
        dataList.add(TodayRankData(6,"창규니",1254))
        dataList.add(TodayRankData(7,"시연조교",242))
        dataList.add(TodayRankData(8,"크리크리",318))
        dataList.add(TodayRankData(9,"아리스토텔레스",-14))
        dataList.add(TodayRankData(10,"랄라",24))
        //dataList.add(TodayRankData(11,"놀롤로",24))

        todayRankRecyclerViewAdapter = TodayRankRecyclerViewAdapter(activity!!, dataList, 2)
        fragment_home_today_rank_bottom_rv.adapter = todayRankRecyclerViewAdapter
        fragment_home_today_rank_bottom_rv.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        fragment_home_today_rank_bottom_rv.addItemDecoration(DividerItemDecoration(context!!, DividerItemDecoration.VERTICAL))
    }

    private fun animateRV(){
        val controller = AnimationUtils.loadLayoutAnimation(activity, R.anim.fade_in_anim)

        fragment_home_today_rank_bottom_rv.setLayoutAnimation(controller)
        todayRankRecyclerViewAdapter.notifyDataSetChanged()
        fragment_home_today_rank_bottom_rv.scheduleLayoutAnimation()
    }


}