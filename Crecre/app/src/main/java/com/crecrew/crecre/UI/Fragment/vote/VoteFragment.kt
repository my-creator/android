package com.crecrew.crecre.UI.Fragment.vote

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.crecrew.crecre.Base.BasePagerAdapter
import com.crecrew.crecre.R
import com.crecrew.crecre.UI.Activity.VoteSuggestActivity
import com.crecrew.crecre.UI.Fragment.vote.votePage.VotePageFragment
import kotlinx.android.synthetic.main.fragment_vote.*
import kotlinx.android.synthetic.main.fragment_vote.view.*
import org.jetbrains.anko.support.v4.startActivity

class VoteFragment : Fragment() {

    private lateinit var rootView: View
    private var isInit = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        rootView = inflater.inflate(R.layout.fragment_vote, container, false)
        return rootView
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if(isInit) {
            //ui
            setViewPager()
            setTabLayout()
            //event
            setClickListener()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //ui
        setViewPager()
        setTabLayout()
        //event
        setClickListener()

        isInit = true
    }

    private fun setViewPager() {

    }

    private fun setTabLayout() {
        vote_viewpager.adapter = BasePagerAdapter(fragmentManager!!).apply {
            addFragment(VotePageFragment.newInstance(true, 2))
            addFragment(VotePageFragment.newInstance(false, 2))
        }

        val topTabLayout: View = activity!!.layoutInflater.inflate(R.layout.fragment_vote_navigation, null, false)
        vote_tab.setupWithViewPager(vote_viewpager)

        vote_tab.getTabAt(0)?.customView = topTabLayout.findViewById(R.id.VoteBarcontinue) as RelativeLayout
        vote_tab.getTabAt(1)?.customView = topTabLayout.findViewById(R.id.VoteBarPast) as RelativeLayout
    }


    private fun setClickListener() {
        btn_fragment_vote_suggest.setOnClickListener {
            startActivity<VoteSuggestActivity>()
        }
    }

}