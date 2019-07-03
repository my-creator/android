package scom.crecrew.crecre.UI.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v7.widget.LinearLayoutManager
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.RelativeLayout
import com.crecrew.crecre.R
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import java.util.*
import com.crecrew.crecre.Base.BasePagerAdapter
import com.crecrew.crecre.Data.TodayPost
import com.crecrew.crecre.UI.Adapter.TodayPostRecyclerViewAdapter
import com.crecrew.crecre.UI.Fragment.HomeTodayRankBottomFragment
import com.crecrew.crecre.UI.Fragment.HomeTodayRankTopFragment


class HomeFragment: Fragment() {

    private lateinit var rootView: View
    private var isChartOpen = false

    lateinit var todayPostRecyclerViewAdapter: TodayPostRecyclerViewAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_home, container, false)

        // ViewPager
        rootView.let {
            it.fragment_home_vp_today_rank.run {
                adapter = BasePagerAdapter(fragmentManager!!).apply {
                    addFragment(HomeTodayRankTopFragment())
                    addFragment(HomeTodayRankBottomFragment())
                }
            }
            it.fragment_home_tl_today_rank.run {
                val navigationLayout: View =
                    LayoutInflater.from(activity!!).inflate(R.layout.fragment_home_today_rank_navi, null, false)
                setupWithViewPager(it.fragment_home_vp_today_rank)
                getTabAt(0)!!.customView =
                    navigationLayout.findViewById(R.id.fragment_home_today_rank_navi_top) as RelativeLayout
                getTabAt(1)!!.customView =
                    navigationLayout.findViewById(R.id.fragment_home_today_rank_navi_bottom) as RelativeLayout
            }
            it.fragment_home_iv_today_hot_btn.setOnClickListener() {
                openTodayHotChart()
            }
        }

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureRecyclerView()
    }


    // 실시간 크리에이터 차트 열고 닫기
    private fun openTodayHotChart(){
        if(isChartOpen == false) {
            fragment_home_today_rank_container.visibility = VISIBLE
            fragment_home_iv_today_hot_btn.setImageResource(R.drawable.icn_hide_off)
            fragment_home_today_hot_creator_container.visibility = GONE
            isChartOpen = true
        }
        else {
            fragment_home_today_rank_container.visibility = GONE
            fragment_home_iv_today_hot_btn.setImageResource(R.drawable.icn_hide_on)
            fragment_home_today_hot_creator_container.visibility = VISIBLE
            isChartOpen = false
        }
    }

    private fun configureRecyclerView(){

        // hot post
        var todayHotDataList: ArrayList<TodayPost> = ArrayList()

        todayHotDataList.add(TodayPost("http://sopt.org/wp/wp-content/uploads/2014/01/24_SOPT-LOGO_COLOR-1.png","딕헌터와 영알남 커플, 현실에서의 만남은 더 심쿵!", "먹방",89,32,30))
        todayHotDataList.add(TodayPost("http://sopt.org/wp/wp-content/uploads/2014/01/24_SOPT-LOGO_COLOR-1.png","2019년 7월 3일 현재 나는 배가 고프다. 어깨도 아픔", "개발",1004,52,50))
        todayHotDataList.add(TodayPost("http://sopt.org/wp/wp-content/uploads/2014/01/24_SOPT-LOGO_COLOR-1.png","지금 정호,예원,다연,민정,가희,신우,혁표,현희랑 같이 있음 크리크리짱", "솝트",999,14,80))

        todayPostRecyclerViewAdapter = TodayPostRecyclerViewAdapter(activity!!, todayHotDataList)
        fragment_home_rv_today_hot_post.adapter = todayPostRecyclerViewAdapter
        fragment_home_rv_today_hot_post.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        fragment_home_rv_today_hot_post.addItemDecoration(DividerItemDecoration(context!!, DividerItemDecoration.VERTICAL))

        // new post
        var todayNewDataList: ArrayList<TodayPost> = ArrayList()

        todayNewDataList.add(TodayPost("http://sopt.org/wp/wp-content/uploads/2014/01/24_SOPT-LOGO_COLOR-1.png","왼쪽 어깨 너무 아프당. 내일 알바가기 시르다", "건강",89,32,30))
        todayNewDataList.add(TodayPost("http://sopt.org/wp/wp-content/uploads/2014/01/24_SOPT-LOGO_COLOR-1.png","와 좀있으면 홈화면 끝난다", "개발",1004,52,50))
        todayNewDataList.add(TodayPost("http://sopt.org/wp/wp-content/uploads/2014/01/24_SOPT-LOGO_COLOR-1.png","헐 1일 1커밋해야하는데, 이제 커밋해야지", "개발",999,14,80))

        todayPostRecyclerViewAdapter = TodayPostRecyclerViewAdapter(activity!!, todayNewDataList)
        fragment_home_rv_today_new_post.adapter = todayPostRecyclerViewAdapter
        fragment_home_rv_today_new_post.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        fragment_home_rv_today_new_post.addItemDecoration(DividerItemDecoration(context!!, DividerItemDecoration.VERTICAL))

    }
}

