package scom.crecrew.crecre.UI.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v7.widget.LinearLayoutManager
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.RelativeLayout
import com.crecrew.crecre.Data.CommunityFavoriteData
import com.crecrew.crecre.R
import com.crecrew.crecre.UI.Adapter.CommunityFavoriteRecyclerViewAdapter
import com.crecrew.crecre.UI.Adapter.TodayRankPagerAdapter
import kotlinx.android.synthetic.main.fragment_community.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment: Fragment() {

    private lateinit var rootView: View
    private var isChartOpen = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_home, container, false)

        /******************************* 정호한테 물어보기 (Base Adapter) **********************************

        // ViewPager
        fragment_home_vp_today_rank.run {
            adapter = BasePagerAdapter(childFragmentManager).apply {
                addFragment(HomeTodayRankTopFragment())
                addFragment(HomeTodayRankBottomFragment())
            }

            //Refresh menu
            addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
                override fun onPageSelected(position: Int) {
                    onResume()

                }

                override fun onPageScrollStateChanged(state: Int) {}
            })

            // offscreenPageLimit = 1
        }

        fragment_home_tl_today_rank.run {
            val navigationLayout: View =
                LayoutInflater.from(activity!!).inflate(R.layout.fragment_home_today_rank_navi, null, false)
            setupWithViewPager(fragment_home_vp_today_rank)
            getTabAt(0)!!.customView =
                navigationLayout.findViewById(R.id.fragment_home_today_rank_navi_top) as RelativeLayout
            getTabAt(1)!!.customView =
                navigationLayout.findViewById(R.id.fragment_home_today_rank_navi_bottom) as RelativeLayout
        }
    **********************************************************************************************/


        rootView.fragment_home_iv_today_hot_btn.setOnClickListener() {

            openTodayHotChart()
        }

        return rootView

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        configureTodayHotTab()
    }

    //ViewPager
    private fun configureTodayHotTab() {

        fragment_home_vp_today_rank.adapter = TodayRankPagerAdapter(fragmentManager!! ,2)
        fragment_home_vp_today_rank.offscreenPageLimit = 1

        fragment_home_tl_today_rank.setupWithViewPager(fragment_home_vp_today_rank)
        val navigationLayout: View = activity!!.layoutInflater.inflate(R.layout.fragment_home_today_rank_navi, null, false)
        fragment_home_tl_today_rank.getTabAt(0)!!.customView = navigationLayout.
            findViewById(R.id.fragment_home_today_rank_navi_top) as RelativeLayout
        fragment_home_tl_today_rank.getTabAt(1)!!.customView = navigationLayout.
            findViewById(R.id.fragment_home_today_rank_navi_bottom) as RelativeLayout
    }


    private fun openTodayHotChart(){
        if(isChartOpen == false) {
            fragment_home_today_rank_container.visibility = VISIBLE
            fragment_home_iv_today_hot_btn.setImageResource(R.drawable.icn_hide_off)
            isChartOpen = true
        }
        else {
            fragment_home_today_rank_container.visibility = GONE
            fragment_home_iv_today_hot_btn.setImageResource(R.drawable.icn_hide_on)
            isChartOpen = false
        }
    }
}

