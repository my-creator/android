package com.crecrew.crecre.UI.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.crecrew.crecre.Data.VoteItemData
import com.crecrew.crecre.Data.VoteTestData
import com.crecrew.crecre.R

class VoteChoiceRecyclerviewAdapter (val ctx: Context, val dataList: ArrayList<VoteItemData>) : RecyclerView.Adapter<VoteChoiceRecyclerviewAdapter.Holder>() {


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): VoteChoiceRecyclerviewAdapter.Holder {
        val view:View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_invote_choice, p0, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        Glide.with(ctx)
            .load(dataList[position].creator_profile_url)
            .into(holder.img_thumnail)

        holder.item_name.text = dataList[position].name;

        /*
        if(dataList[position].)
            holder.img_isCheck.setImageResource(R.drawable.btn_check)
        else*/
            holder.img_isCheck.setImageResource(R.drawable.btn_uncheck)
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var img_thumnail = itemView.findViewById(R.id.item_img) as ImageView
        var item_name = itemView.findViewById(R.id.item_name) as TextView
        var img_isCheck = itemView.findViewById(R.id.item_ischeckimg) as ImageView
    }


}



