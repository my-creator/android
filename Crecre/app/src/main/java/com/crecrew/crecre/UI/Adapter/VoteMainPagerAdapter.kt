package com.crecrew.crecre.UI.Adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.crecrew.crecre.UI.Fragment.Community.CommunityPopularFragment
import com.crecrew.crecre.UI.Fragment.Community.CommunityRecentFragment
import com.crecrew.crecre.UI.Fragment.VoteCurrentFragment
import com.crecrew.crecre.UI.Fragment.VoteEndFragment

class VoteMainPagerAdapter (fm:FragmentManager, private val num_fragment: Int): FragmentStatePagerAdapter(fm){

    companion object {
        private var voteCurrentFragment: VoteCurrentFragment? = null
        private var voteEndFragment: VoteEndFragment? = null

        @Synchronized
        fun getvoteCurrentFragment(): VoteCurrentFragment {
            if(voteCurrentFragment == null) voteCurrentFragment =
                VoteCurrentFragment()
            return voteCurrentFragment!!
        }
        @Synchronized
        fun getvoteEndFragment(): VoteEndFragment {
            if(voteEndFragment == null) voteEndFragment =
                VoteEndFragment()
            return voteEndFragment!!
        }
    }


        override fun getItem(p0: Int): Fragment? {
        return when(p0){
            0 -> getvoteCurrentFragment()
            1 -> getvoteEndFragment()
            else -> return null
        }
    }

    override fun getCount(): Int {
        return num_fragment
    }

}