package com.crecrew.crecre.UI.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.crecrew.crecre.Data.CommunitySmallNewGetData
import com.crecrew.crecre.R
import com.crecrew.crecre.UI.Activity.Community.CommunityDetailActivity
import com.crecrew.crecre.UI.Activity.Community.CommunityHotPostActivity
import org.jetbrains.anko.startActivity

class CommunityHotPostRecyclerViewAdapter(val ctx: Context, val dataList: ArrayList<CommunitySmallNewGetData>, val flag: Int
) : RecyclerView.Adapter<CommunityHotPostRecyclerViewAdapter.Holder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_hotpost_community_act, viewGroup, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {

        // thumbnail 이미지
        if (dataList[position].thumbnail_url == "")
            Glide.with(ctx).load(R.drawable.icn_img_x).into(holder.thumbnail)
        else {
            Glide.with(ctx).load(dataList[position].thumbnail_url).into(holder.thumbnail)
            //holder.thumbnail.setBackground(ShapeDrawable())
            holder.thumbnail.setClipToOutline(true)
        }

        //flag=0이면 최신글, flag=1이면 인기글
        if (flag == 0) {
            holder.hot_img.visibility = View.GONE
            //마지막 아이템의 line은 삭제
            if (position == dataList.size - 1)
                holder.line.visibility = View.INVISIBLE


        } else if (flag == 1) {
            //마지막 아이템의 line은 삭제
            if (position == dataList.size - 1)
                holder.line.visibility = View.INVISIBLE
        }

        if (dataList[position].hot_image == 0)
            holder.hot_img.visibility = View.GONE

        //contents_title
        holder.title.text = dataList[position].title

        //추천수
        holder.recommendation.text = "추천 " + dataList[position].like_cnt.toString() + " | "

        //댓글수##수정reply_cnt로 수정
        holder.comment.text = "댓글 " + dataList[position].reply_cnt.toString() + " | "

        //작성시간
        holder.time.text = dataList[position].create_time.toString()

        //게시판카테고리으로 수정
        if (dataList[position].name == null)
            holder.category.text = ""
        else
            holder.category.text = " | " + dataList[position].name

        /*
        //더보기 눌렀을 때
        holder.more_btn.setOnClickListener {
            ctx.startActivity<CommunityHotPostActivity>(
                //flag=0이면 최신글, flag=1이면 인기글
                "flag" to flag,
                "user_idx" to dataList[position].user_idx
            )
        }
        */

        //container 눌렀을 시
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

        var container = itemView.findViewById(R.id.ll_container_community_act) as LinearLayout
        var thumbnail = itemView.findViewById(R.id.img_thumbnail_hotpost_community_act) as ImageView
        var hot_img = itemView.findViewById(R.id.img_hot_icon_community_hot_act) as ImageView
        var title = itemView.findViewById(R.id.tv_title_community_hot_act) as TextView
        var recommendation = itemView!!.findViewById(R.id.tv_recommendation_community_act) as TextView
        var comment = itemView!!.findViewById(R.id.tv_comment_hotpost_com_act) as TextView
        var time = itemView!!.findViewById(R.id.tv_posttime_hotpost_com_act) as TextView
        var category = itemView!!.findViewById(R.id.tv_postcategory_hotpost_com_act) as TextView

        var line = itemView.findViewById(R.id.ll_lastline_rv_hotpost_commu_act) as LinearLayout


        //var more_btn = itemView!!.findViewById(R.id.btn_more_community_popular_fg) as LinearLayout

    }
}