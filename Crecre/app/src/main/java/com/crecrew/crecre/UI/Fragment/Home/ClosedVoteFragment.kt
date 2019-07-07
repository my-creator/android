package com.crecrew.crecre.UI.Fragment.Home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.crecrew.crecre.Data.LastVoteData
import com.crecrew.crecre.R
import kotlinx.android.synthetic.main.fragment_clsd_vote.view.*

class ClosedVoteFragment : Fragment() {
    lateinit var rootView: View
    lateinit var lastVoteData:LastVoteData
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_clsd_vote, container, false)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rootView.run{
            Glide.with(this@ClosedVoteFragment)
                .load(lastVoteData.image)
                .into(frag_clsd_vote_iv_img)
            Glide.with(this@ClosedVoteFragment)
                .load(lastVoteData.profile)
                //.error(R.drawable.img_profile)
                .apply(RequestOptions().circleCrop())
                .into(frag_clsd_vote_iv_profile)
            frag_clsd_vote_tv_name.text = lastVoteData.creator
            frag_clsd_vote_tv_rank.text = "${lastVoteData.ranking}등"
            frag_clsd_vote_tv_title.text = lastVoteData.content
        }

    }

    fun setData(data: LastVoteData) {
        lastVoteData = data
    }

    companion object {
        private val TAG = "ClosedVoteFragment"

        fun newInstance(data: LastVoteData): ClosedVoteFragment {
            val frg = ClosedVoteFragment()
            frg.lastVoteData = data
            return frg
        }
    }

}