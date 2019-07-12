package com.crecrew.crecre.UI.Adapter

import android.content.Context
import android.graphics.Color
import android.os.Handler
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.crecrew.crecre.Data.CreatorData
import com.crecrew.crecre.Data.TodayRankData
import com.crecrew.crecre.R


class TodayRankRecyclerViewAdapter(private val ctx : Context, private val dataList : ArrayList<CreatorData>) : RecyclerView.Adapter<TodayRankRecyclerViewAdapter.Holder>() {

    var lastPosition = -1
    val innerHandler = Handler()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx)!!.inflate(R.layout.rv_item_today_rank, viewGroup, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {

        if(dataList[position].ranking >= 1 && dataList[position].ranking <=3){
            holder.number.setTextColor(Color.parseColor("#ff57f7"))
        }
        holder.number.text = dataList[position].ranking.toString()
        holder.creator.text = dataList[position].creatorName
        if (dataList[position].searchCnt > 0)
            Glide.with(ctx).load(R.drawable.icn_up).into(holder.arrow)
        else
            Glide.with(ctx).load(R.drawable.icn_down).into(holder.arrow)
        holder.gap.text = Math.abs(dataList[position].searchCnt).toString()
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var container = itemView.findViewById(R.id.rv_item_today_rank_container) as RelativeLayout
        var number = itemView.findViewById(R.id.rv_item_today_rank_number) as TextView
        var creator = itemView.findViewById(R.id.rv_item_today_rank_creator) as TextView
        var gap = itemView.findViewById(R.id.rv_item_today_rank_gap) as TextView
        var arrow = itemView.findViewById(R.id.rv_item_today_rank_arrow) as ImageView

    }

    private fun setAnimation(viewToAnimate: View, position: Int){
        if(position > lastPosition){
            var animation : Animation = AnimationUtils.loadAnimation(ctx, R.anim.dropdown)
            viewToAnimate.startAnimation(animation)
            lastPosition = position
        }
    }
}