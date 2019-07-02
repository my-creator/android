package com.crecrew.crecre.UI.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.crecrew.crecre.Base.BasePagerAdapter
import com.crecrew.crecre.R
import com.crecrew.crecre.UI.Fragment.CommunityFragment
import com.crecrew.crecre.UI.Fragment.VoteFragment
import com.crecrew.crecreUI.Fragment.MypageFragment
import com.crecrew.crecreUI.Fragment.RankFragment
import kotlinx.android.synthetic.main.activity_main.*
import scom.crecrew.crecre.UI.Fragment.HomeFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ViewPager
        activity_main_vp_container.run {
            adapter = BasePagerAdapter(supportFragmentManager).apply {
                addFragment(HomeFragment())
                addFragment(RankFragment())
                addFragment(VoteFragment())
                addFragment(CommunityFragment())
                addFragment(MypageFragment())
            }

            //Refresh menu
            addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
                override fun onPageSelected(position: Int) {
                    invalidateOptionsMenu()
                }

                override fun onPageScrollStateChanged(state: Int) {}
            })

            offscreenPageLimit = 4
        }

        // TabLayout
        activity_main_tl_navi.run {
            val navigationLayout: View =
                LayoutInflater.from(this@MainActivity).inflate(R.layout.activity_main_navi, null, false)
            setupWithViewPager(activity_main_vp_container)
            getTabAt(0)!!.customView =
                navigationLayout.findViewById(R.id.activity_main_navi_home_container) as LinearLayout
            getTabAt(1)!!.customView =
                navigationLayout.findViewById(R.id.activity_main_navi_rank_container) as LinearLayout
            getTabAt(2)!!.customView =
                navigationLayout.findViewById(R.id.activity_main_navi_vote_container) as LinearLayout
            getTabAt(3)!!.customView =
                navigationLayout.findViewById(R.id.activity_main_navi_community_container) as LinearLayout
            getTabAt(4)!!.customView =
                navigationLayout.findViewById(R.id.activity_main_navi_mypage_container) as LinearLayout
        }
    }
}