package com.crecrew.crecre.UI.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.crecrew.crecre.R
import com.crecrew.crecre.UI.Activity.VoteSuggestActivity
import com.crecrew.crecre.UI.Adapter.CommunityPostFragmentAdapter
import com.crecrew.crecre.UI.Adapter.VoteMainPagerAdapter
import kotlinx.android.synthetic.main.fragment_community.*
import kotlinx.android.synthetic.main.fragment_vote.*
import kotlinx.android.synthetic.main.fragment_vote_navigation.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.startActivity

class VoteFragment: Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_vote, container, false)

        //왜안되죠?????????
        btn_frag_vote_suggest.setOnClickListener{
            startActivity<VoteSuggestActivity>()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureMainTab()
    }



    //ViewPager
    private fun configureMainTab() {


        vote_viewpager.adapter = VoteMainPagerAdapter(fragmentManager!!, 2)
        vote_viewpager.offscreenPageLimit = 1


        vote_tab.setupWithViewPager(vote_viewpager)
        val topTabLayout: View = activity!!.layoutInflater.inflate(R.layout.fragment_vote_navigation, null, false)
        vote_tab.getTabAt(0)!!.customView = topTabLayout.
            findViewById(R.id.VoteBarcontinue) as RelativeLayout
        vote_tab.getTabAt(1)!!.customView = topTabLayout.
            findViewById(R.id.VoteBarPast) as RelativeLayout

    }


}