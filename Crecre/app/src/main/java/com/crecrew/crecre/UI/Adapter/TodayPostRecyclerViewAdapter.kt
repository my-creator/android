package com.crecrew.crecre.UI.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
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
import com.crecrew.crecre.utils.calculatePostTime
import org.jetbrains.anko.startActivity
import kotlin.collections.ArrayList


class TodayPostRecyclerViewAdapter(private val ctx : Context, private val dataList : ArrayList<TodayPost>) : RecyclerView.Adapter<TodayPostRecyclerViewAdapter.Holder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx)!!.inflate(R.layout.rv_item_today_post, viewGroup, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {

            if (dataList[position].thumbnail_url == "")
                Glide.with(ctx).load(R.drawable.icn_img_x).into(holder.thumbnail)
            else
                Glide.with(ctx).load(dataList[position].thumbnail_url).into(holder.thumbnail)
            holder.title.text = dataList[position].title
            holder.category.text = dataList[position].name
            holder.recommend.text = "추천" + dataList[position].like_cnt.toString()
            holder.comment.text = "댓글" + dataList[position].reply_cnt.toString()

            var cpt = calculatePostTime(dataList[position].create_time)
            holder.time.text = cpt

            holder.container.setOnClickListener {
                ctx.startActivity<CommunityDetailActivity>(
                    "category_title" to dataList[position].name,
                    "title" to dataList[position].title,
                    "idx" to dataList[position].user_idx,
                    "postidx" to dataList[position].post_idx,
                    "thumbnail_url" to dataList[position].thumbnail_url
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