package com.crecrew.crecreUI.Fragment

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.Toast
import com.crecrew.crecre.Base.BasePagerAdapter
import com.crecrew.crecre.Data.RankData
import com.crecrew.crecre.R
import com.crecrew.crecre.UI.Adapter.RankChartRecyclerViewAdapter
import com.crecrew.crecre.UI.View.SimpleDividerItemDecoration
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.fragment_rank.*
import kotlinx.android.synthetic.main.fragment_rank.view.*
import kotlinx.android.synthetic.main.fragment_rank_category_navi.*
import kotlinx.android.synthetic.main.fragment_rank_category_navi.view.*
import kotlinx.android.synthetic.main.rv_item_rank_creator.*

class RankFragment: Fragment(), View.OnClickListener{
    private lateinit var rootView: View
    lateinit var rankChartRecyclerViewAdapter: RankChartRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_rank, container, false)


        rootView.fragment_rank_rl_hot_navi.isSelected = true
        rootView.fragment_rank_rl_subscriber_navi.isSelected = true

        init()
        rootView.fragment_rank_category_navi_all.isSelected = true

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureRecyclerView()

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




    }

    private fun configureRecyclerView(){

        // rank data
        var rankData: ArrayList<RankData> = ArrayList()

        rankData.add(RankData(1,3, "https://news.imaeil.com/inc/photos/2019/04/29/2019042900310562698_l.jpg","먹방","시연조교",R.drawable.icn_rank1.toString(),13575321548))
        rankData.add(RankData(1,10, "https://mblogthumb-phinf.pstatic.net/MjAxODA1MTlfOSAg/MDAxNTI2NzQwNjY5OTUx.VcucGKX52noaAETS5acZgeovzLRSCWs8AkzGJVJUuasg.PIDUYkcbI_IaBRJ25-Lgu4-pnrDdVuP8uWK4ZRQbxl8g.JPEG.okyunju0309/PicsArt_05-19-01.19.40.jpg?type=w800","브이로그","가희바위보슬보슬개미똥꼬멍멍이가노래를한다",R.drawable.icn_rank1.toString(),453215))
        rankData.add(RankData(1,-1, "http://image.chosun.com/sitedata/image/201709/22/2017092201233_0.jpg","댄스","예오닝",R.drawable.icn_rank1.toString(),32483215))
        rankData.add(RankData(1,0, "https://pbs.twimg.com/profile_images/1057608035760132096/JwBKke8l_400x400.jpg","게임","유성동그리동동",R.drawable.icn_rank1.toString(),1234))
        rankData.add(RankData(1,-5, "","코믹","Door Jung",R.drawable.icn_rank1.toString(),2248))
        rankData.add(RankData(1,99, "http://cfile7.uf.tistory.com/image/241C4A33597796CB224B67","브이로그","토슬토실",R.drawable.icn_rank1.toString(),754))
        rankData.add(RankData(1,3, "https://news.imaeil.com/inc/photos/2019/04/29/2019042900310562698_l.jpg","먹방","시연조교",R.drawable.icn_rank1.toString(),13575321548))
        rankData.add(RankData(1,0, "https://mblogthumb-phinf.pstatic.net/MjAxODA1MTlfOSAg/MDAxNTI2NzQwNjY5OTUx.VcucGKX52noaAETS5acZgeovzLRSCWs8AkzGJVJUuasg.PIDUYkcbI_IaBRJ25-Lgu4-pnrDdVuP8uWK4ZRQbxl8g.JPEG.okyunju0309/PicsArt_05-19-01.19.40.jpg?type=w800","브이로그","가희바위보슬보슬개미똥꼬멍멍이가노래를한다",R.drawable.icn_rank1.toString(),453215))
        rankData.add(RankData(1,-1, "http://image.chosun.com/sitedata/image/201709/22/2017092201233_0.jpg","댄스","예오닝",R.drawable.icn_rank1.toString(),32483215))
        rankData.add(RankData(1,2, "https://pbs.twimg.com/profile_images/1057608035760132096/JwBKke8l_400x400.jpg","게임","유성동그리동동",R.drawable.icn_rank1.toString(),1234))
        rankData.add(RankData(1,-5, "","코믹","Door Jung",R.drawable.icn_rank1.toString(),2248))
        rankData.add(RankData(1,99, "http://cfile7.uf.tistory.com/image/241C4A33597796CB224B67","브이로그","토슬토실",R.drawable.icn_rank1.toString(),754))


        //rankChartRecyclerViewAdapter = RankChartRecyclerViewAdapter(activity!!, rankData,0)
        //fragment_rank_rv_chart.adapter = rankChartRecyclerViewAdapter
        //fragment_rank_rv_chart.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        //fragment_rank_rv_chart.addItemDecoration(SimpleDividerItemDecoration(Color.parseColor("#eaeaea"), 1))

    }

    override fun onClick(v: View?){
        when(v!!){

            fragment_rank_category_navi_all->{
                clearSelect()
                fragment_rank_category_navi_all.isSelected = true

                Log.e("location", "eatingshow")
            }

            fragment_rank_category_navi_eatingshow->{
                clearSelect()
                fragment_rank_category_navi_eatingshow.isSelected = true

                Log.e("location", "eatingshow")
                // 통신
            }

            fragment_rank_category_navi_beauty->{
                clearSelect()
                fragment_rank_category_navi_beauty.isSelected = true

                Log.e("location", "beauty")
                // 통신
            }
            fragment_rank_category_navi_vlog->{
                clearSelect()
                fragment_rank_category_navi_vlog.isSelected = true

                // 통신
            }
            fragment_rank_category_navi_music->{
                clearSelect()
                fragment_rank_category_navi_music.isSelected = true

                // 통신
            }
            fragment_rank_category_navi_comic->{
                clearSelect()
                fragment_rank_category_navi_comic.isSelected = true

                // 통신
            }
            fragment_rank_category_navi_dance->{
                clearSelect()
                fragment_rank_category_navi_dance.isSelected = true

                // 통신
            }
            fragment_rank_category_navi_cook->{
                clearSelect()
                fragment_rank_category_navi_cook.isSelected = true

                // 통신
            }
            fragment_rank_category_navi_talk->{
                clearSelect()
                fragment_rank_category_navi_talk.isSelected = true

                // 통신
            }
            fragment_rank_category_navi_asmr->{
                clearSelect()
                fragment_rank_category_navi_asmr.isSelected = true

                // 통신
            }
            fragment_rank_category_navi_game->{
                clearSelect()
                fragment_rank_category_navi_game.isSelected = true

                // 통신
            }

            fragment_rank_rl_all_navi->{
                if(!fragment_rank_rl_all_navi.isSelected) {
                    fragment_rank_rl_all_navi.isSelected = true
                    fragment_rank_rl_hot_navi.isSelected = false

                    fragment_rank_txt_filter_term.text = "전체"

                    // 통신
                }
            }
            fragment_rank_rl_hot_navi->{
                if(!fragment_rank_rl_hot_navi.isSelected){
                    fragment_rank_rl_all_navi.isSelected = false
                    fragment_rank_rl_hot_navi.isSelected = true

                    fragment_rank_txt_filter_term.text = "급상승"
                    // 통신 (flag?)
                }
            }
            fragment_rank_rl_subscriber_navi->{
                if(!fragment_rank_rl_subscriber_navi.isSelected) {
                    fragment_rank_rl_subscriber_navi.isSelected = true
                    fragment_rank_rl_views_navi.isSelected = false

                    fragment_rank_txt_filter_number.text ="구독자수"
                    // 통신 (flag?)
                }
            }
            fragment_rank_rl_views_navi->{
                if(!fragment_rank_rl_views_navi.isSelected) {
                    fragment_rank_rl_views_navi.isSelected = true
                    fragment_rank_rl_subscriber_navi.isSelected = false

                    fragment_rank_txt_filter_number.text ="조회수"
                    // 통신 (flag?)
                }
            }
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
}