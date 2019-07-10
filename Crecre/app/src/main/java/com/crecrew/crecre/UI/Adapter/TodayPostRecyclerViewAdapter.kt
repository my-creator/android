package com.crecrew.crecre.UI.Adapter

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.crecrew.crecre.Data.TodayPost
import com.crecrew.crecre.R
import com.crecrew.crecre.UI.Activity.Community.CommunityDetailActivity
import com.crecrew.crecre.utils.CalculatePostTime
import org.jetbrains.anko.startActivity
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList


class TodayPostRecyclerViewAdapter(private val ctx : Context, private val dataList : ArrayList<TodayPost>) : RecyclerView.Adapter<TodayPostRecyclerViewAdapter.Holder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx)!!.inflate(R.layout.rv_item_today_post, viewGroup, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {


        if(dataList[position].thumbnail == "")
            Glide.with(ctx).load(R.drawable.icn_img_x).into(holder.thumbnail)
        else
            Glide.with(ctx).load(dataList[position].thumbnail).into(holder.thumbnail)
        holder.title.text = dataList[position].title
        holder.category.text = dataList[position].category
        holder.recommend.text = "추천" + dataList[position].recommend.toString()
        holder.comment.text = "댓글" + dataList[position].comment.toString()

        var cpt = CalculatePostTime().calculatePostTime(dataList[position].time)
        holder.time.text = cpt

        holder.container.setOnClickListener {
            ctx.startActivity<CommunityDetailActivity>(
               // TODO: 정보 넣기
            )
        }
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var container = itemView.findViewById(R.id.rv_item_today_post_container) as RelativeLayout
        var thumbnail = itemView.findViewById(R.id.rv_item_today_post_thumnail) as ImageView
        var title = itemView.findViewById(R.id.rv_item_today_post_title) as TextView
        var category = itemView.findViewById(R.id.rv_item_today_post_category) as TextView
        var recommend = itemView.findViewById(R.id.rv_item_today_post_recommend) as TextView
        var comment = itemView.findViewById(R.id.rv_item_today_post_comment) as TextView
        var time = itemView.findViewById(R.id.rv_item_today_post_time) as TextView


    }
}