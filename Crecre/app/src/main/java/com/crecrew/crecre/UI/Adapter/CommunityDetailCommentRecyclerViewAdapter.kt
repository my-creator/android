package com.crecrew.crecre.UI.Adapter

import android.content.Context
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.crecrew.crecre.Data.CommentData
import com.crecrew.crecre.Network.Get.CommunityDetailData
import com.crecrew.crecre.R
import de.hdodenhof.circleimageview.CircleImageView

class CommunityDetailCommentRecyclerViewAdapter(val ctx: Context, val dataList: ArrayList<CommunityDetailData>) :
    RecyclerView.Adapter<CommunityDetailCommentRecyclerViewAdapter.Holder>() {


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_comment_commu_detail_act, viewGroup, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {

        // profile 이미지
        if(dataList[position].profile_url == "")
            Glide.with(ctx).load(R.drawable.img_profile).into(holder.profile)
        else
        {
            Glide.with(ctx).load(dataList[position].profile_url).into(holder.profile)
            holder.profile.setBackground(ShapeDrawable(OvalShape()))
            holder.profile.setClipToOutline(true)
        }

        //user name
        holder.user_name.text = dataList[position].id

        //time
        holder.time.text = dataList[position].create_time

        //comment
        holder.content.text = dataList[position].contents

    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var profile = itemView.findViewById(R.id.img_profile_detail_act_rv) as ImageView
        var user_name = itemView.findViewById(R.id.tv_user_name_detail_act_rv) as TextView
        var time = itemView.findViewById(R.id.tv_write_time_detail_act_rv) as TextView
        var content = itemView.findViewById(R.id.tv_comment_detail_act_rv) as TextView

    }
}