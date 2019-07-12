package com.crecrew.crecre.UI.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.crecrew.crecre.Data.VoteData
import com.crecrew.crecre.Network.ApplicationController
import com.crecrew.crecre.Network.Get.GetRankResponse
import com.crecrew.crecre.Network.Get.GetVoteHomeResponse
import com.crecrew.crecre.Network.Get.GetVoteResponse
import com.crecrew.crecre.Network.VoteNetworkService
import com.crecrew.crecre.R
import com.crecrew.crecre.UI.Adapter.VoteListRecyclerviewAdapter
import kotlinx.android.synthetic.main.fragment_vote_current.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VoteCurrentFragment : Fragment() {

    lateinit var rootView:View
    var flag = 0

    val voteNetworkService: VoteNetworkService by lazy{
        ApplicationController.instance.voteNetworkService
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_vote_current, container, false)

        if (flag == 0) {
            getVoteResponse()
        }
        else if (flag == 1)
            getVoteHomeResponse()

        return rootView
    }


    // TODO: 두개 합칠 수 있음! 합치기!!
    fun getVoteResponse(){

        val getVoteResponse = voteNetworkService.getCurrentVote()
        getVoteResponse.enqueue(object : Callback<GetVoteResponse>{
            override fun onFailure(call: Call<GetVoteResponse>, t: Throwable) {
                Log.e("vote response fail: ",t.toString())
            }

            override fun onResponse(call: Call<GetVoteResponse>, response: Response<GetVoteResponse>) {
                if(response.isSuccessful){
                    if(response.body()!!.status == 200){
                        var tmp: ArrayList<VoteData> = response.body()!!.data

                        var voteListRecyclerviewAdapter = VoteListRecyclerviewAdapter(context!!, tmp)
                        rootView.rv_fragment_vote_current.adapter = voteListRecyclerviewAdapter
                    }
                }
            }
        })
    }

    fun getVoteHomeResponse(){
        val getVoteHomeResponse = voteNetworkService.getCurrentVoteHome()
        getVoteHomeResponse.enqueue(object : Callback<GetVoteHomeResponse>{
            override fun onFailure(call: Call<GetVoteHomeResponse>, t: Throwable) {
                Log.e("vote response fail: ",t.toString())
            }

            override fun onResponse(call: Call<GetVoteHomeResponse>, response: Response<GetVoteHomeResponse>) {
                if(response.isSuccessful){
                    if(response.body()!!.status == 200){
                        var tmp: VoteData = response.body()!!.data

                        var arr: ArrayList<VoteData> = ArrayList()
                        arr.add(tmp)

                        var voteListRecyclerviewAdapter = VoteListRecyclerviewAdapter(context!!, arr)
                        rootView.rv_fragment_vote_current.adapter = voteListRecyclerviewAdapter
                    }
                }
            }
        })
    }
}