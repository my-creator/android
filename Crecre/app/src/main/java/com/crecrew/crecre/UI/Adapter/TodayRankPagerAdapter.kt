package com.crecrew.crecre.UI.Adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.crecrew.crecre.UI.Fragment.HomeTodayRankBottomFragment
import com.crecrew.crecre.UI.Fragment.HomeTodayRankTopFragment

class TodayRankPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm){
    override fun getItem(p0: Int): Fragment? {
        when (p0) {
            0 -> return HomeTodayRankTopFragment()
            1 -> return HomeTodayRankBottomFragment()
        }
        return null
    }

    override fun getCount() = 2

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0->"1~5위"
            else->"6~10위"
        }
    }
}