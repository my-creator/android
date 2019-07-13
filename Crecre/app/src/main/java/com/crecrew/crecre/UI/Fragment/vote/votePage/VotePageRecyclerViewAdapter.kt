package com.crecrew.crecre.UI.Fragment.vote.votePage

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.crecrew.crecre.DB.SharedPreferenceController
import com.crecrew.crecre.Data.GetVoteEndData
import com.crecrew.crecre.Data.VoteData
import com.crecrew.crecre.Network.ApplicationController
import com.crecrew.crecre.Network.Get.GetVoteEndResponse
import com.crecrew.crecre.Network.Post.PostVoteChoiceResponse
import com.crecrew.crecre.Network.VoteNetworkService
import com.crecrew.crecre.R
import com.crecrew.crecre.utils.calculateLastime
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import org.jetbrains.anko.textColor
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList

class VotePageRecyclerViewAdapter(
    val ctx: Context,
    val dataList: ArrayList<VoteData>,
    val endDataList: ArrayList<GetVoteEndData>,
    var mode: Int
) :
    RecyclerView.Adapter<VotePageRecyclerViewAdapter.Holder>()
    , VotePageItemRecyclerViewAdapter.IItemCheckListener {


    lateinit var listener: IVoteListener

    fun setOnItemClickListener(listener: IVoteListener) {
        this.listener = listener
    }

    lateinit var summitBtn: TextView
    var userToken = SharedPreferenceController.getUserToken(ctx)
    var choiceIdx: Int? = null

    private val voteNetworkService: VoteNetworkService by lazy { ApplicationController.instance.voteNetworkService }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_currentvote_card, p0, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        if (dataList.isNullOrEmpty())
            return endDataList.size
        else
            return dataList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        summitBtn = holder.letsVote

        if(!dataList.isEmpty()) {
            Glide.with(ctx)
                .load(dataList[position].thumbnail_url)
                .into(holder.img_thumnail)

            dataList[position].my_choice?.let {
                holder.stamp.visibility = View.VISIBLE
            } ?: run {
                holder.stamp.visibility = View.GONE
            }

            holder.title.text = dataList[position].title
            holder.explain.text = "#" + dataList[position].contents

            val cal = calculateLastime(dataList[position].end_time)
            holder.txt_dayleft.text = "${cal}일 후 개표"

            val votePageItemRecyclerViewAdapter =
                VotePageItemRecyclerViewAdapter(ctx, dataList[position].choices, dataList[position].my_choice, true)
            votePageItemRecyclerViewAdapter.setOnItemClickListener(this)

            holder.choice_container.adapter = votePageItemRecyclerViewAdapter

            summitBtn.setOnClickListener {
                choiceIdx?.let {
                    postVoteResponse(it, position)
                }
            }
        } else {
            Glide.with(ctx)
                .load(endDataList!![position].thumbnail_url)
                .into(holder.img_thumnail)

            holder.stamp.visibility = View.VISIBLE


            holder.title.text = endDataList!![position].title
            holder.explain.text = "#" + endDataList!![position].contents

            val cal = calculateLastime(endDataList!![position].end_time)
            holder.txt_dayleft.text = "투표 종료"
            holder.txt_ongoing.visibility = View.GONE
            val votePageItemRecyclerViewAdapter =
                VotePageItemRecyclerViewAdapter(ctx, endDataList!![position].choices, endDataList[position].my_choice, false)
            votePageItemRecyclerViewAdapter.setOnItemClickListener(this)

            holder.choice_container.adapter = votePageItemRecyclerViewAdapter
            summitBtn.visibility = View.INVISIBLE
            summitBtn.setOnClickListener {
                choiceIdx?.let {
                    postVoteResponse(it, position)
                }
            }
        }
    }

    override fun onItemCheck(choice: Int) {
        summitBtn.setTextColor(Color.parseColor("#ff57f7"))
        choiceIdx = choice
    }

    private fun postVoteResponse(choice: Int, position: Int) {
        val jsonObject = JSONObject()
        jsonObject.put("choiceIdx", choice)

        val gsonObject = JsonParser().parse(jsonObject.toString()) as JsonObject
        val postVoteResponse: Call<PostVoteChoiceResponse> =
            voteNetworkService.postVoteChoiceResponse(
                userToken,
                "application/json",
                gsonObject,
                dataList!![position].vote_idx
            )
        postVoteResponse.enqueue(object : Callback<PostVoteChoiceResponse> {
            override fun onFailure(call: Call<PostVoteChoiceResponse>, t: Throwable) {
                Log.e("Post vote choice", t.toString())
            }

            override fun onResponse(call: Call<PostVoteChoiceResponse>, response: Response<PostVoteChoiceResponse>) {
                if (response.isSuccessful) {
                    if (response.body()!!.status == 200) {
                        Log.d("Post vote choice", "success!")
                        listener.onVote(mode)

                    } else {
                        Log.d("Post vote choice", "error!")
                    }
                }
            }
        })
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var img_thumnail = itemView.findViewById(R.id.card_main_image) as ImageView
        var txt_dayleft = itemView.findViewById(R.id.rv_item_current_card_dayleft) as TextView
        var txt_ongoing = itemView.findViewById(R.id.rv_item_current_card_isongoing) as TextView
        var stamp = itemView.findViewById(R.id.rv_item_current_card_stamp) as RelativeLayout
        var title = itemView.findViewById(R.id.rv_item_vote_title) as TextView
        var explain = itemView.findViewById(R.id.rv_item_vote_explain) as TextView
        var letsVote = itemView.findViewById(R.id.lets_vote) as TextView
        var whitebox = itemView.findViewById(R.id.whitebox) as View
        var choice_container = itemView.findViewById(R.id.rv_item_invote_choicesList) as RecyclerView
        var mintbox = itemView.findViewById(R.id.rv_item_current_card_isongoing) as Button
    }

    interface IVoteListener {
        fun onVote(mode: Int)
    }
}



