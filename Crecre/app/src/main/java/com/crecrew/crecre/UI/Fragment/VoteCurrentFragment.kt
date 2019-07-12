package com.crecrew.crecre.UI.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.crecrew.crecre.Network.ApplicationController
import com.crecrew.crecre.Network.Get.GetVoteCurrentResponse
import com.crecrew.crecre.Network.Get.VoteTestData
import com.crecrew.crecre.Network.VoteNetworkService
import com.crecrew.crecre.R
import com.crecrew.crecre.UI.Adapter.VoteTestAdapter
import kotlinx.android.synthetic.main.fragment_vote_current.*
import kotlinx.android.synthetic.main.fragment_vote_current.view.*
import kotlinx.android.synthetic.main.fragment_vote_current.view.rv_fragment_vote_current
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VoteCurrentFragment : Fragment() {
    lateinit var rootView: View
    lateinit var voteTestAdapter: VoteTestAdapter
    val voteNetworkService: VoteNetworkService by lazy {
        ApplicationController.instance.voteNetworkService
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getVoteCurrentResponse()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_vote_current, container, false)
        return rootView
    }

    private fun getVoteCurrentResponse() {

        val getVoteCurrentResponse: Call<GetVoteCurrentResponse> = voteNetworkService.getCurrentVote()
        getVoteCurrentResponse.enqueue(object : Callback<GetVoteCurrentResponse> {

            override fun onResponse(call: Call<GetVoteCurrentResponse>, response: Response<GetVoteCurrentResponse>) {

                Log.e("isSuccessful", response.isSuccessful.toString())
                if (response.isSuccessful) {
                    if (response.body()!!.status == 200) {
                        val tmp: ArrayList<VoteTestData> = response.body()!!.data!!
                        voteTestAdapter = VoteTestAdapter(activity!!, tmp)
                        rv_fragment_vote_current.adapter = voteTestAdapter
                        rv_fragment_vote_current.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                    }
                }

            }

            override fun onFailure(call: Call<GetVoteCurrentResponse>, t: Throwable) {
                Log.e("투표 실패", t.toString())
            }

        })

    }
}