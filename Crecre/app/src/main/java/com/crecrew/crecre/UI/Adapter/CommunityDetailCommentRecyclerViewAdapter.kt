package com.crecrew.crecre.UI.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.crecrew.crecre.Data.CommentData
import com.crecrew.crecre.R

class CommunityDetailCommentRecyclerViewAdapter(val ctx: Context, val dataList: ArrayList<CommentData>) :
    RecyclerView.Adapter<CommunityDetailCommentRecyclerViewAdapter.Holder>() {


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_comment_commu_detail_act, viewGroup, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {

        // profile 이미지
        if(dataList[position].user_profile_url == "")
            Glide.with(ctx).load(R.drawable.img_profile).into(holder.profile)
        else
            Glide.with(ctx).load(dataList[position].user_profile_url).into(holder.profile)

        //user name
        holder.user_name.text = dataList[position].user_name

        //time
        holder.time.text = dataList[position].create_time

        //comment
        holder.content.text = dataList[position].content


    }




    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var profile = itemView.findViewById(R.id.img_profile_detail_act_rv) as ImageView
        var user_name = itemView.findViewById(R.id.tv_user_name_detail_act_rv) as TextView
        var time = itemView.findViewById(R.id.tv_write_time_detail_act_rv) as TextView
        var content = itemView.findViewById(R.id.tv_comment_detail_act_rv) as TextView

    }
}