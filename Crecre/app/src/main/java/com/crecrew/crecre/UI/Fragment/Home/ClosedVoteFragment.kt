package com.crecrew.crecre.UI.Fragment.Home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.crecrew.crecre.Data.LastVoteHomeData
import com.crecrew.crecre.R
import kotlinx.android.synthetic.main.fragment_clsd_vote.*
import kotlinx.android.synthetic.main.fragment_clsd_vote.view.*

class ClosedVoteFragment : Fragment() {
    lateinit var rootView: View
    var lastVoteData: LastVoteHomeData? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_clsd_vote, container, false)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        lastVoteData?.let {
            rootView.run {
                Glide.with(this@ClosedVoteFragment)
                    .load(it.profile_url)
                    .into(frag_clsd_vote_iv_img)

                if (it.thumbnail_url.equals("")) {
                    Glide.with(this@ClosedVoteFragment)
                        .load(R.drawable.icn_profile)
                        .into(frag_clsd_vote_iv_profile)
                } else {
                    Glide.with(this@ClosedVoteFragment)
                        .load(it.thumbnail_url).apply(RequestOptions().circleCrop())
                        .into(frag_clsd_vote_iv_img)
                }


                }
                frag_clsd_vote_tv_name.text = it.choice_name
                frag_clsd_vote_tv_rank.text = "1등"
                frag_clsd_vote_tv_title.text = it.title
            }
        }


    companion object {
        private val TAG = "ClosedVoteFragment"

        fun newInstance(data: LastVoteHomeData): ClosedVoteFragment {
            val frg = ClosedVoteFragment()
            frg.lastVoteData = data
            return frg
        }
    }
}