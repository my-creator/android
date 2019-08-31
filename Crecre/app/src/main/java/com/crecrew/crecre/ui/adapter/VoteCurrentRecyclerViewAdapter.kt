@file:Suppress("UNREACHABLE_CODE")

package com.crecrew.crecre.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.crecrew.crecre.data.VoteCurrentOverViewData
import com.crecrew.crecre.R

class VoteCurrentRecyclerViewAdapter(val ctx: Context, val dataList: ArrayList<VoteCurrentOverViewData>) :
    RecyclerView.Adapter<VoteCurrentRecyclerViewAdapter.Holder>() {

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var img_thumnail = itemView.findViewById(R.id.card_main_image) as ImageView
        var txt_dayleft = itemView.findViewById(R.id.rv_item_current_card_dayleft) as TextView
        var title = itemView.findViewById(R.id.rv_item_vote_title) as TextView
        var explain = itemView.findViewById(R.id.rv_item_vote_explain) as TextView
        var items = itemView.findViewById(R.id.rv_item_currenvote_card_test_choice_rv) as RecyclerView
        var letsVote = itemView.findViewById(R.id.lets_vote) as TextView
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): VoteCurrentRecyclerViewAdapter.Holder {
       val view:View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_currentvote_card, p0, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        Glide.with(ctx)
            .load(dataList[position].ImageURL)
            .into(holder.img_thumnail)
        holder.txt_dayleft.text = "${dataList[position].date} 일 후 개표"
        holder.title.text = dataList[position].title
        holder.explain.text = "#" + dataList[position].explain

    }

   /* private fun recyclerView() {
        var dataList : ArrayList<VoteItemData> = ArrayList()
        dataList.add(VoteItemData("https://m.blog.naver.com/PostView.nhn?blogId=jini2877&logNo=221274305035&proxyReferer=https%3A%2F%2Fwww.google.com%2F&view=img_1","딕헌터", isChecked = false))
        dataList.add(VoteItemData("https://namu.wiki/w/파일:봄나물.jpg","딕딕딕", isChecked = false))
        dataList.add(VoteItemData("https://namu.wiki/w/파일:욕망의%20참치회.jpg","thㅏ시미",isChecked = false))
        dataList.add(VoteItemData("https://namu.wiki/w/파일:욕망의%20참치회.jpg","thㅏ시미",isChecked = false))

        var voteItemRecyclerViewAdapter = VoteItemRecyclerViewAdapter(ctx, dataList)
        rv_item_invote_choicesList.adapter = voteItemRecyclerViewAdapter
    }*/


}

