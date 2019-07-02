package scom.crecrew.crecre.UI.Fragment

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.crecrew.crecre.R
import com.crecrew.crecre.UI.Adapter.TodayRankPagerAdapter
import android.support.v4.app.FragmentManager
import android.support.v4.content.ContextCompat.getSystemService
import android.widget.RelativeLayout
import com.crecrew.crecre.Data.TodayRankData
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment: Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    private fun configureTabLayout(){
        fragment_home_vp_today_rank.adapter = TodayRankPagerAdapter(getChildFragmentManager())
        fragment_home_tl_today_rank.setupWithViewPager(fragment_home_vp_today_rank)

    }

}

