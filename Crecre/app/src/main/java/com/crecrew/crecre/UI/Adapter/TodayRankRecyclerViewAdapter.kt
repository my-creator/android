package com.crecrew.crecre.UI.Adapter

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.crecrew.crecre.Data.TodayRankData
import com.crecrew.crecre.R
import com.crecrew.crecre.UI.Fragment.HomeTodayRankTopFragment


class TodayRankRecyclerViewAdapter(private val ctx : Context, private val dataList : ArrayList<TodayRankData>, private val tab : Int) : RecyclerView.Adapter<TodayRankRecyclerViewAdapter.Holder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx)!!.inflate(R.layout.rv_item_today_rank, viewGroup, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {

        // 범위내로 안뜸
        if(tab == 1) {
            if(dataList[position].number >= 1 && dataList[position].number <=5) {
                if(dataList[position].number >= 1 && dataList[position].number <=3){
                    holder.number.setTextColor(Color.parseColor("#ff57f7"))
                }
                holder.number.text = dataList[position].number.toString()
                holder.creator.text = dataList[position].creator
                if (dataList[position].gap > 0)
                    Glide.with(ctx).load(R.drawable.icn_up).into(holder.arrow)
                else
                    Glide.with(ctx).load(R.drawable.icn_down).into(holder.arrow)
                holder.gap.text = Math.abs(dataList[position].gap).toString()
            }

        }else{
            if (dataList[position].number >= 6 && dataList[position].number <= 10) {
                holder.number.text = dataList[position].number.toString()
                holder.creator.text = dataList[position].creator
                if (dataList[position].gap > 0)
                    Glide.with(ctx).load(R.drawable.icn_up).into(holder.arrow)
                else
                    Glide.with(ctx).load(R.drawable.icn_down).into(holder.arrow)
                holder.gap.text = Math.abs(dataList[position].gap).toString()
            }
        }

            holder.container.setOnClickListener {

            }
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // nullable 처리 다 해야하나?
        var container = itemView.findViewById(R.id.rv_item_today_rank_container) as RelativeLayout
        var number = itemView.findViewById(R.id.rv_item_today_rank_number) as TextView
        var creator = itemView.findViewById(R.id.rv_item_today_rank_creator) as TextView
        var gap = itemView.findViewById(R.id.rv_item_today_rank_gap) as TextView
        var arrow = itemView.findViewById(R.id.rv_item_today_rank_arrow) as ImageView

    }
}