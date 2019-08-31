package com.crecrew.crecre.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.crecrew.crecre.db.SharedPreferenceController
import com.crecrew.crecre.data.GetVoteEndData
import com.crecrew.crecre.network.ApplicationController
import com.crecrew.crecre.network.get.GetVoteEndResponse
import com.crecrew.crecre.network.VoteNetworkService
import com.crecrew.crecre.R
import com.crecrew.crecre.ui.adapter.VoteEndAdapter
import kotlinx.android.synthetic.main.fragment_vote_last.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VoteEndFragment : Fragment() {

    lateinit var voteEndAdapter: VoteEndAdapter
    val voteNetworkService: VoteNetworkService by lazy{
        ApplicationController.instance.voteNetworkService
    }

    lateinit var rootView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        rootView = inflater.inflate(R.layout.fragment_vote_last, container, false)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        getVoteEndResponse()
    }

    private fun getVoteEndResponse() {
        val getVoteEndResponse = voteNetworkService.getLastVote(SharedPreferenceController.getUserToken(activity!!))
        getVoteEndResponse.enqueue(object : Callback<GetVoteEndResponse> {

            override fun onResponse(call: Call<GetVoteEndResponse>, response: Response<GetVoteEndResponse>) {
                if (response.isSuccessful){
                    if ( response.body()!!.status == 200 ){
                        val tmp: ArrayList<GetVoteEndData> = response.body()!!.data!!

                        voteEndAdapter = VoteEndAdapter(activity!!, tmp)
                        rv_fragment_vote_last!!.adapter = voteEndAdapter
                        rv_fragment_vote_last!!.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

                    }
                }
            }
            override fun onFailure(call: Call<GetVoteEndResponse>, t: Throwable) {
                Log.e("투표 실패", t.toString())
            }
        })
    }


}