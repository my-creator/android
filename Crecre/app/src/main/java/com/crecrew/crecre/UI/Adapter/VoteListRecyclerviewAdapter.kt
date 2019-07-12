package com.crecrew.crecre.UI.Adapter

import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.crecrew.crecre.Data.VoteData
import com.crecrew.crecre.R
import com.crecrew.crecre.utils.CalculateLastime
import kotlinx.android.synthetic.main.fragment_rank.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class VoteListRecyclerviewAdapter (val ctx: Context, val dataList: ArrayList<VoteData>) : RecyclerView.Adapter<VoteListRecyclerviewAdapter.Holder>() {

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var img_thumnail = itemView.findViewById(R.id.card_main_image) as ImageView
        var txt_dayleft = itemView.findViewById(R.id.rv_item_current_card_dayleft) as TextView
        var txt_ongoing = itemView.findViewById(R.id.rv_item_current_card_isongoing) as TextView
        var stamp = itemView.findViewById(R.id.rv_item_current_card_stamp) as ImageView
        var title = itemView.findViewById(R.id.rv_item_vote_title) as TextView
        var explain = itemView.findViewById(R.id.rv_item_vote_explain) as TextView
        var letsVote = itemView.findViewById(R.id.lets_vote) as TextView
        var choice_container = itemView.findViewById(R.id.rv_item_currenvote_card_test_choice_rv) as RecyclerView
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): VoteListRecyclerviewAdapter.Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_currentvote_card, p0, false)
        return Holder(view)

    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: Holder, position: Int) {
        Glide.with(ctx)
            .load(dataList[position].thumbnail_url)
            .into(holder.img_thumnail)

        holder.title.text = dataList[position].title
        holder.explain.text = "# " + dataList[position].contents

        holder.txt_ongoing.setVisibility(View.GONE)

        // TODO: 시간 계산
        holder.txt_dayleft.text = "${dataList[position]}일 후 개표"
        //이거 왜안되죠 ㅇ
        //var cal  = CalculateLastime(dataList[position].end_time)
        //holder.txt_dayleft.text = "${cal}일 후 개표"

        var voteChoiceRecyclerviewAdapter = VoteChoiceRecyclerviewAdapter(ctx, dataList[position].choices)
        holder.choice_container.adapter = voteChoiceRecyclerviewAdapter
        holder.choice_container.layoutManager = LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false)

    }
}


