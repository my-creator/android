package com.crecrew.crecre.UI.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.crecrew.crecre.Data.VoteEndData
import com.crecrew.crecre.R

class VoteEndAdapter(val ctx: Context, val dataList: ArrayList<VoteEndData>) :
    RecyclerView.Adapter<VoteEndAdapter.Holder>(){

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): VoteEndAdapter.Holder {
        val view:View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_endvote_card, p0, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        Glide.with(ctx)
            .load(dataList[position].ImageURL)
            .into(holder.img_thumnail)
        Glide.with(ctx)
            .load(dataList[position].itemimage1)
            .apply(RequestOptions().circleCrop()).into(holder.img_item1)
        Glide.with(ctx)
            .load(dataList[position].itemimage2)
            .apply(RequestOptions().circleCrop()).into(holder.img_item2)
        Glide.with(ctx)
            .load(dataList[position].itemimage3)
            .apply(RequestOptions().circleCrop()).into(holder.img_item3)
        Glide.with(ctx)
            .load(dataList[position].itemimage4)
            .apply(RequestOptions().circleCrop()).into(holder.img_item4)
        Glide.with(ctx)
            .load(dataList[position].itemimage5)
            .apply(RequestOptions().circleCrop()).into(holder.img_item5)
        holder.title.text = dataList[position].title
        holder.explain.text = dataList[position].explain
        holder.txt_itemname1.text = dataList[position].itemname1
        holder.txt_itemname2.text = dataList[position].itemname2
        holder.txt_itemname3.text = dataList[position].itemname3
        holder.txt_itemname4.text = dataList[position].itemname4
        holder.txt_itemname5.text = dataList[position].itemname5
        holder.txt_votenum1.text = "${dataList[position].votenum1}표"
        holder.txt_votenum2.text = "${dataList[position].votenum2}표"
        holder.txt_votenum3.text = "${dataList[position].votenum3}표"
        holder.txt_votenum4.text = "${dataList[position].votenum4}표"
        holder.txt_votenum5.text = "${dataList[position].votenum5}표"
        holder.txt_rank1.text = "1등"
        holder.txt_rank2.text = "2등"
        holder.txt_rank3.text = "3등"
        holder.txt_rank4.text = "4등"
        holder.txt_rank5.text = "5등"
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        var img_thumnail = itemView.findViewById(R.id.card_main_image_end) as ImageView
        var title = itemView.findViewById(R.id.rv_item_vote_title_end) as TextView
        var explain = itemView.findViewById(R.id.rv_item_vote_explain_end) as TextView
        var img_item1 = itemView.findViewById(R.id.item_img1_end) as ImageView
        var img_item2 = itemView.findViewById(R.id.item_img2_end) as ImageView
        var img_item3 = itemView.findViewById(R.id.item_img3_end) as ImageView
        var img_item4 = itemView.findViewById(R.id.item_img4_end) as ImageView
        var img_item5 = itemView.findViewById(R.id.item_img5_end) as ImageView
        var txt_itemname1 = itemView.findViewById(R.id.item_name1_end) as TextView
        var txt_itemname2 = itemView.findViewById(R.id.item_name2_end) as TextView
        var txt_itemname3 = itemView.findViewById(R.id.item_name3_end) as TextView
        var txt_itemname4 = itemView.findViewById(R.id.item_name4_end) as TextView
        var txt_itemname5 = itemView.findViewById(R.id.item_name5_end) as TextView
        var txt_votenum1 = itemView.findViewById(R.id.vote_num1_end) as TextView
        var txt_votenum2 = itemView.findViewById(R.id.vote_num2_end) as TextView
        var txt_votenum3 = itemView.findViewById(R.id.vote_num3_end) as TextView
        var txt_votenum4 = itemView.findViewById(R.id.vote_num4_end) as TextView
        var txt_votenum5 = itemView.findViewById(R.id.vote_num5_end) as TextView
        var txt_rank1 = itemView.findViewById(R.id.vote_rank1_end) as TextView
        var txt_rank2 = itemView.findViewById(R.id.vote_rank2_end) as TextView
        var txt_rank3 = itemView.findViewById(R.id.vote_rank3_end) as TextView
        var txt_rank4 = itemView.findViewById(R.id.vote_rank4_end) as TextView
        var txt_rank5 = itemView.findViewById(R.id.vote_rank5_end) as TextView
    }
}