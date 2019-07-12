package com.crecrew.crecreUI.Fragment

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.crecrew.crecre.Network.ApplicationController
import com.crecrew.crecre.Network.Get.GetRankResponse
import com.crecrew.crecre.Network.Get.RankData
import com.crecrew.crecre.Network.RankNetworkService
import com.crecrew.crecre.R
import com.crecrew.crecre.UI.Adapter.RankChartRecyclerViewAdapter
import com.crecrew.crecre.UI.View.SimpleDividerItemDecoration
import kotlinx.android.synthetic.main.fragment_home_today_rank.*
import kotlinx.android.synthetic.main.fragment_rank.*
import kotlinx.android.synthetic.main.fragment_rank.view.*
import kotlinx.android.synthetic.main.fragment_rank_category_navi.*
import kotlinx.android.synthetic.main.fragment_rank_category_navi.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RankFragment: Fragment(), View.OnClickListener{
    private lateinit var rootView: View
    lateinit var rankChartRecyclerViewAdapter: RankChartRecyclerViewAdapter

    val rankNetworkService: RankNetworkService by lazy{
        ApplicationController.instance.rankNetworkService
    }

    var networkCategoryFlag = 0
    var networkTermFlag = 0
    var networkNumberFlag = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_rank, container, false)

        // 처음 선택 값
        rootView.fragment_rank_rl_hot_navi.isSelected = true
        rootView.fragment_rank_rl_subscriber_navi.isSelected = true
        rootView.fragment_rank_category_navi_all.isSelected = true

        init()

        getRankReponse(rankNetworkService.getAllAllSubscriber(),networkNumberFlag)


        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //getRankReponse(rankNetworkService.getAllHotSubscriber(),1)

    }



    private fun init() {

        // category
        rootView.fragment_rank_category_navi_all.setOnClickListener(this)
        rootView.fragment_rank_category_navi_eatingshow.setOnClickListener(this)
        rootView.fragment_rank_category_navi_beauty.setOnClickListener(this)
        rootView.fragment_rank_category_navi_vlog.setOnClickListener(this)
        rootView.fragment_rank_category_navi_music.setOnClickListener(this)
        rootView.fragment_rank_category_navi_comic.setOnClickListener(this)
        rootView.fragment_rank_category_navi_dance.setOnClickListener(this)
        rootView.fragment_rank_category_navi_cook.setOnClickListener(this)
        rootView.fragment_rank_category_navi_talk.setOnClickListener(this)
        rootView.fragment_rank_category_navi_asmr.setOnClickListener(this)
        rootView.fragment_rank_category_navi_game.setOnClickListener(this)

        // filter
        rootView.fragment_rank_rl_all_navi.setOnClickListener(this)
        rootView.fragment_rank_rl_hot_navi.setOnClickListener(this)
        rootView.fragment_rank_rl_subscriber_navi.setOnClickListener(this)
        rootView.fragment_rank_rl_views_navi.setOnClickListener(this)

        rootView.fragment_rank_fab.setOnClickListener(this)

    }

    override fun onClick(v: View?){
        when(v!!){

            fragment_rank_fab->{
                fragment_rank_sv_container.smoothScrollTo(0,0)
            }

            fragment_rank_category_navi_all->{
                // view 용
                clearSelect()
                fragment_rank_category_navi_all.isSelected = true
                networkCategoryFlag = 0
                networking()
            }

            fragment_rank_category_navi_eatingshow->{
                clearSelect()
                fragment_rank_category_navi_eatingshow.isSelected = true
                networkCategoryFlag = 1
                networking()

            }

            fragment_rank_category_navi_beauty->{
                clearSelect()
                fragment_rank_category_navi_beauty.isSelected = true
                networkCategoryFlag = 2
                networking()

            }
            fragment_rank_category_navi_game->{
                clearSelect()
                fragment_rank_category_navi_game.isSelected = true
                networkCategoryFlag = 3
                networking()
                // 통신
            }
            fragment_rank_category_navi_vlog->{
                clearSelect()
                fragment_rank_category_navi_vlog.isSelected = true
                networkCategoryFlag = 4
                networking()
                // 통신
            }
            fragment_rank_category_navi_music->{
                clearSelect()
                fragment_rank_category_navi_music.isSelected = true
                networkCategoryFlag = 5
                networking()
            }
            fragment_rank_category_navi_comic->{
                clearSelect()
                fragment_rank_category_navi_comic.isSelected = true
                networkCategoryFlag = 6
                networking()
            }
            fragment_rank_category_navi_dance->{
                clearSelect()
                fragment_rank_category_navi_dance.isSelected = true
                networkCategoryFlag = 7
                networking()
            }
            fragment_rank_category_navi_cook->{
                clearSelect()
                fragment_rank_category_navi_cook.isSelected = true
                networkCategoryFlag = 8
                networking()
            }
            fragment_rank_category_navi_talk->{
                clearSelect()
                fragment_rank_category_navi_talk.isSelected = true
                networkCategoryFlag = 9
                networking()
            }
            fragment_rank_category_navi_asmr->{
                clearSelect()
                fragment_rank_category_navi_asmr.isSelected = true
                networkCategoryFlag = 10
                networking()
            }



            /*********************** 기간 ****************************/
            fragment_rank_rl_all_navi->{
                if(!fragment_rank_rl_all_navi.isSelected) {
                    fragment_rank_rl_all_navi.isSelected = true
                    fragment_rank_rl_hot_navi.isSelected = false

                    fragment_rank_txt_filter_term.text = "전체"

                    networkTermFlag = 0
                    networking()
                }
            }
            fragment_rank_rl_hot_navi->{
                if(!fragment_rank_rl_hot_navi.isSelected){
                    fragment_rank_rl_all_navi.isSelected = false
                    fragment_rank_rl_hot_navi.isSelected = true

                    fragment_rank_txt_filter_term.text = "급상승"
                    networkTermFlag = 1
                    networking()
                }
            }

            /*********************** 수 ****************************/

            fragment_rank_rl_subscriber_navi->{
                if(!fragment_rank_rl_subscriber_navi.isSelected) {
                    fragment_rank_rl_subscriber_navi.isSelected = true
                    fragment_rank_rl_views_navi.isSelected = false

                    fragment_rank_txt_filter_number.text ="구독자수"
                    networkNumberFlag = 1
                    networking()
                }
            }
            fragment_rank_rl_views_navi->{
                if(!fragment_rank_rl_views_navi.isSelected) {
                    fragment_rank_rl_views_navi.isSelected = true
                    fragment_rank_rl_subscriber_navi.isSelected = false

                    fragment_rank_txt_filter_number.text ="조회수"
                    networkNumberFlag = 2
                    networking()
                }
            }
        }


    }

    private fun networking(){
        // (전체, -, -)
        if(networkCategoryFlag == 0){
            if(networkTermFlag == 0) {
                if(networkNumberFlag == 1) {
                    getRankReponse(rankNetworkService.getAllAllSubscriber(), networkNumberFlag)
                }
                else if(networkNumberFlag ==2){
                    getRankReponse(rankNetworkService.getAllAllView(), networkNumberFlag)
                }
            }else if(networkTermFlag == 1){
                if(networkNumberFlag == 1) {
                    getRankReponse(rankNetworkService.getAllHotSubscriber(), networkNumberFlag)
                }
                else if(networkNumberFlag ==2){
                    getRankReponse(rankNetworkService.getAllHotView(), networkNumberFlag)
                }
            }
        }else{
            if(networkNumberFlag == 1) {
                getRankReponse(rankNetworkService.getCateAllSubscriber(networkCategoryFlag), networkNumberFlag)
            }
            else
                getRankReponse(rankNetworkService.getCateAllView(networkCategoryFlag), networkNumberFlag)
        }
    }

    fun clearSelect() {
        fragment_rank_category_navi_all.isSelected = false
        fragment_rank_category_navi_eatingshow.isSelected = false
        fragment_rank_category_navi_beauty.isSelected = false
        fragment_rank_category_navi_vlog.isSelected = false
        fragment_rank_category_navi_music.isSelected = false
        fragment_rank_category_navi_comic.isSelected = false
        fragment_rank_category_navi_dance.isSelected = false
        fragment_rank_category_navi_cook.isSelected = false
        fragment_rank_category_navi_talk.isSelected = false
        fragment_rank_category_navi_asmr.isSelected = false
        fragment_rank_category_navi_game.isSelected = false
    }

    private fun getRankReponse(call : Call<GetRankResponse>, flag : Int) {

        call.enqueue(object : Callback<GetRankResponse> {

            override fun onFailure(call: Call<GetRankResponse>, t: Throwable) {
                Log.e("load rank fail", t.toString())
            }

            override fun onResponse(call: Call<GetRankResponse>, response: Response<GetRankResponse>) {
                if (response.isSuccessful) {
                    if(response.body()!!.status == 200) {
                        val tmp: ArrayList<RankData> = response.body()!!.data
                        if(tmp.size >0){
                        val dataList: ArrayList<RankData> = tmp

                        rankChartRecyclerViewAdapter = RankChartRecyclerViewAdapter(activity!!, dataList,flag)
                        fragment_rank_rv_chart.adapter = rankChartRecyclerViewAdapter
                        fragment_rank_rv_chart.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                        fragment_rank_rv_chart.addItemDecoration(SimpleDividerItemDecoration(Color.parseColor("#eaeaea"), 1))

                            animateRV()
                        }

                        // 없는 경우는 없음ㅋㅜㅠ
                    }
                }

            }

        })

    }

    private fun animateRV(){
        val controller = AnimationUtils.loadLayoutAnimation(activity!!, R.anim.fade_in_anim)

        fragment_rank_rv_chart.setLayoutAnimation(controller)
        // rankChartRecyclerViewAdapter.notifyDataSetChanged()
        fragment_rank_rv_chart.scheduleLayoutAnimation()
    }
}