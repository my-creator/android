package com.crecrew.crecre.UI.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.crecrew.crecre.Data.SearchResultData
import com.crecrew.crecre.R

class CoummunitySearchRecyclerViewAdapter (val ctx : Context, val dataList : ArrayList<SearchResultData>) : RecyclerView.Adapter<CoummunitySearchRecyclerViewAdapter.Holder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_search_result_community_act, viewGroup, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {


        var img_like = 0
        holder.category.text = dataList[position].category

        //그냥 받아왔을 때
        if(dataList[position].like_on == 1)
        {
            holder.like_on.setImageResource(R.drawable.icn_look_on)
            /*dataList[position].*/img_like = 1
        }
        else
        {
            holder.like_on.setImageResource(R.drawable.icn_look_off)
            img_like = 0
        }

        //클릭했을 시
        holder.like_on.setOnClickListener {
            if(img_like == 0)
            {
                holder.like_on.isSelected = true
                img_like = 1
            }
            else
            {
                holder.like_on.isSelected = false
                img_like = 0
            }
        }

    }

    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView){
        var category = itemView.findViewById(R.id.tv_community_category_title_rv_item) as TextView
        var like_on = itemView.findViewById(R.id.btn_img_like_on) as ImageView

    }
}


