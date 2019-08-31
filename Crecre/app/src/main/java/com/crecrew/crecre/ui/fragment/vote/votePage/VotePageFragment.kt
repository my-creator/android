package com.crecrew.crecre.ui.fragment.vote.votePage

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.crecrew.crecre.db.SharedPreferenceController
import com.crecrew.crecre.data.GetVoteEndData
import com.crecrew.crecre.data.VoteData
import com.crecrew.crecre.network.ApplicationController
import com.crecrew.crecre.network.get.GetVoteEndResponse
import com.crecrew.crecre.network.get.GetVoteHomeResponse
import com.crecrew.crecre.network.get.GetVoteResponse

import com.crecrew.crecre.network.VoteNetworkService
import com.crecrew.crecre.R
import kotlinx.android.synthetic.main.fragment_vote_current.*

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VotePageFragment : Fragment(), VotePageRecyclerViewAdapter.IVoteListener {

    var isCurrent = true
    var tabPosition = 2

    private lateinit var rootView: View
    private val voteDataList = ArrayList<VoteData>()
    private val voteEndDataList = ArrayList<GetVoteEndData>()
    private val voteNetworkService: VoteNetworkService by lazy { ApplicationController.instance.voteNetworkService }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_vote_current, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (isCurrent) {
            if (tabPosition == 0)
                getVoteHomeResponse()
            else if (tabPosition == 2)
                getVoteResponse()
        } else
            getVoteEndResponse()
    }

    private fun getVoteResponse() {
        val getVoteResponse = voteNetworkService.getCurrentVote(SharedPreferenceController.getUserToken(activity!!))

        getVoteResponse.enqueue(object : Callback<GetVoteResponse> {
            override fun onFailure(call: Call<GetVoteResponse>, t: Throwable) {
                Log.e(TAG, t.toString())
            }

            override fun onResponse(call: Call<GetVoteResponse>, response: Response<GetVoteResponse>) {
                if (response.isSuccessful) {
                    if (response.body()!!.status == 200) {
                        voteDataList.clear()
                        voteEndDataList.clear()
                        voteDataList.addAll(response.body()!!.data)
                        setRecyclerView(0)
                    }
                }
            }
        })
    }

    private fun getVoteHomeResponse() {
        val getVoteHomeResponse =
            voteNetworkService.getCurrentVoteHome(SharedPreferenceController.getUserToken(activity!!))

        getVoteHomeResponse.enqueue(object : Callback<GetVoteHomeResponse> {
            override fun onFailure(call: Call<GetVoteHomeResponse>, t: Throwable) {
                Log.e(TAG, t.toString())
            }

            override fun onResponse(call: Call<GetVoteHomeResponse>, response: Response<GetVoteHomeResponse>) {
                if (response.isSuccessful) {
                    if (response.body()!!.status == 200) {
                        voteDataList.clear()
                        voteEndDataList.clear()
                        voteDataList.add(response.body()!!.data)
                        setRecyclerView(1)
                    }
                }
            }
        })
    }

    private fun getVoteEndResponse() {
        val getVoteEndResponse = voteNetworkService.getLastVote(SharedPreferenceController.getUserToken(activity!!))

        getVoteEndResponse.enqueue(object : Callback<GetVoteEndResponse> {
            override fun onFailure(call: Call<GetVoteEndResponse>, t: Throwable) {
                Log.e(TAG, t.toString())
            }

            override fun onResponse(call: Call<GetVoteEndResponse>, response: Response<GetVoteEndResponse>) {
                if (response.isSuccessful) {
                    if (response.body()!!.status == 200) {
                        voteDataList.clear()
                        voteEndDataList.clear()
                        voteEndDataList.addAll(response.body()!!.data)
                        setRecyclerView(2)
                    }
                }
            }
        })
    }

    private fun setRecyclerView(mode: Int) {
//        if(isCurrent)
        val votePageRecyclerViewAdapter = VotePageRecyclerViewAdapter(activity!!, voteDataList, voteEndDataList, mode)
        votePageRecyclerViewAdapter.setOnItemClickListener(this@VotePageFragment)
        rv_fragment_vote_current.adapter = votePageRecyclerViewAdapter


//         else if(!isCurrent)
//            VotePageRecyclerViewAdapter(activity!!, voteEndDataList)

    }

    override fun onVote(mode: Int) {
        when (mode) {
            0 -> getVoteResponse()
            1 -> getVoteHomeResponse()
            2 -> getVoteEndResponse()
        }
    }

    companion object {
        private val TAG = "VotePageFragment"

        fun newInstance(isCurrent: Boolean, tabPosition: Int): VotePageFragment {
            val frg = VotePageFragment()
            frg.isCurrent = isCurrent
            frg.tabPosition = tabPosition
            return frg
        }
    }

}
