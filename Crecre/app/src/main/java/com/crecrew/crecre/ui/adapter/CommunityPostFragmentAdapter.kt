package com.crecrew.crecre.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.crecrew.crecre.ui.fragment.Community.CommunityPopularFragment
import com.crecrew.crecre.ui.fragment.Community.CommunityRecentFragment

class CommunityPostFragmentAdapter(fm: FragmentManager, private val num_fragment: Int) : FragmentStatePagerAdapter(fm) {

    companion object {
        private var communityRecentFragment: CommunityRecentFragment? = null
        private var communityPopularFragment: CommunityPopularFragment? = null

        @Synchronized
        fun getcommunityRecentFragment(): CommunityRecentFragment {
            if (communityRecentFragment == null) communityRecentFragment =
                CommunityRecentFragment()
            return communityRecentFragment!!
        }

        @Synchronized
        fun getEndProductMainFragment(): CommunityPopularFragment {
            if (communityPopularFragment == null) communityPopularFragment =
                CommunityPopularFragment()
            return communityPopularFragment!!
        }
    }

    override fun getItem(p0: Int): Fragment? {
        return when (p0) {
            0 -> getcommunityRecentFragment()
            1 -> getEndProductMainFragment()
            else -> return null
        }
    }

    override fun getCount(): Int {
        return num_fragment
    }
}