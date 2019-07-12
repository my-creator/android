package com.crecrew.crecre.UI.Adapter

import android.content.Context
import android.graphics.Color
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
import com.crecrew.crecre.DB.SharedPreferenceController
import com.crecrew.crecre.Data.VoteData
import com.crecrew.crecre.Data.VoteTestData
import com.crecrew.crecre.R
import com.crecrew.crecre.utils.calculateLastime
import kotlinx.android.synthetic.main.fragment_rank.*
import kotlin.collections.ArrayList

class VoteListRecyclerviewAdapter(val ctx: Context, val dataList: ArrayList<VoteData>) :
    RecyclerView.Adapter<VoteListRecyclerviewAdapter.Holder>()
    , VoteChoiceRecyclerviewAdapter.onItemCheckListener {
    var isCheck: Boolean = false
    lateinit var test: TextView

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var img_thumnail = itemView.findViewById(R.id.card_main_image) as ImageView
        var txt_dayleft = itemView.findViewById(R.id.rv_item_current_card_dayleft) as TextView
        var txt_ongoing = itemView.findViewById(R.id.rv_item_current_card_isongoing) as TextView
        var stamp = itemView.findViewById(R.id.rv_item_current_card_stamp) as ImageView
        var title = itemView.findViewById(R.id.rv_item_vote_title) as TextView
        var explain = itemView.findViewById(R.id.rv_item_vote_explain) as TextView
        var letsVote = itemView.findViewById(R.id.lets_vote) as TextView
        var choice_container = itemView.findViewById(R.id.rv_item_invote_choicesList) as RecyclerView
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
        var userToken = SharedPreferenceController.getUserToken(ctx)

        Glide.with(ctx)
            .load(dataList[position].thumbnail_url)
            .into(holder.img_thumnail)

        holder.title.text = dataList[position].title
        holder.explain.text = "# " + dataList[position].contents

        holder.txt_ongoing.setVisibility(View.GONE)

        test = holder.letsVote
//        if(!isCheck) holder.letsVote.)
//        else holder.letsVote.setTextColor(Color.parseColor("#ff57f7"))

        var cal = calculateLastime(dataList[position].end_time)
        holder.txt_dayleft.text = "${cal}일 후 개표"
        //holder.txt_dayleft.text = "일 후 개표"

        var voteChoiceRecyclerviewAdapter = VoteChoiceRecyclerviewAdapter(ctx, dataList[position].choices)
        voteChoiceRecyclerviewAdapter.setOnItemClickListener(this)
        holder.choice_container.adapter = voteChoiceRecyclerviewAdapter
        holder.choice_container.layoutManager = LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false)

        if (userToken != null && dataList[position].my_choice != null) {

        } else if (userToken != null && dataList[position].my_choice == null) {

        }
    }

    override fun onCheck(isCheck: Boolean) {
        if (isCheck) {
            test.setTextColor(Color.parseColor("#ff57f7"))
        }
    }
}


