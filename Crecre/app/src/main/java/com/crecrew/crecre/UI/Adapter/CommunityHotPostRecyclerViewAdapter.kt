package com.crecrew.crecre.UI.Adapter

import android.content.Context
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.crecrew.crecre.Data.CommunityHotPostData
import com.crecrew.crecre.Network.Get.CommunitySmallNewGetData
import com.crecrew.crecre.R
import com.crecrew.crecre.UI.Activity.Community.CommunityDetailActivity
import org.jetbrains.anko.startActivity

class CommunityHotPostRecyclerViewAdapter(val ctx : Context, val dataList : ArrayList<CommunitySmallNewGetData>, val flag : Int) : RecyclerView.Adapter<CommunityHotPostRecyclerViewAdapter.Holder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_hotpost_community_act, viewGroup, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {

        // thumbnail 이미지
        if(dataList[position].thumbnail_url == "")
            Glide.with(ctx).load(R.drawable.icn_img_x).into(holder.thumbnail)
        else
        {
            Glide.with(ctx).load(dataList[position].thumbnail_url).into(holder.thumbnail)
            //holder.thumbnail.setBackground(ShapeDrawable())
            holder.thumbnail.setClipToOutline(true)
        }

        if(flag == 0){
            holder.hot_img.visibility = View.GONE
            Log.v("asdf","사진 없음")
        }

        //title
        holder.title.text = dataList[position].title

        //추천수
        holder.recommendation.text = "추천 " + dataList[position].like_cnt.toString() + " | "

        //댓글수 ##고쳐야함
        holder.comment.text = "댓글 " + dataList[position].like_cnt.toString() + " | "

        //작성시간
        holder.time.text = dataList[position].update_time.toString()

        //게시판카테고리 ##
        if(dataList[position].contents.toString() == "")
            holder.category.text = ""
        else
            holder.category.text = " | " + dataList[position].contents.toString()

     /*   //더보기 눌렀을 때
        holder.more_btn.setOnClickListener {
            ctx.startActivity<CommunityHotPostActivity>(
                "post_idx" to dataList[position].post_id,
                "idx" to dataList[position].user_id
            )
        }*/

        //container 눌렀을 시
        holder.container.setOnClickListener {
            ctx.startActivity<CommunityDetailActivity>(
                "title" to dataList[position].title,
                "idx" to dataList[position].user_idx,
                "post_idx" to dataList[position].board_idx
            )
        }
    }

    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView){

        var container = itemView.findViewById(R.id.ll_container_community_act) as LinearLayout
        var thumbnail = itemView.findViewById(R.id.img_thumbnail_hotpost_community_act) as ImageView
        var hot_img = itemView.findViewById(R.id.img_hot_icon_community_hot_act) as ImageView
        var title = itemView.findViewById(R.id.tv_title_community_hot_act) as TextView
        var recommendation = itemView!!.findViewById(R.id.tv_recommendation_community_act) as TextView
        var comment = itemView!!.findViewById(R.id.tv_comment_hotpost_com_act) as TextView
        var time = itemView!!.findViewById(R.id.tv_posttime_hotpost_com_act) as TextView
        var category = itemView!!.findViewById(R.id.tv_postcategory_hotpost_com_act) as TextView
        //var more_btn = itemView.findViewById(R.id.btn_more_community_popular_fg) as LinearLayout

    }
}