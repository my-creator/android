package com.crecrew.crecre.UI.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.crecrew.crecre.Data.ProfileHotVideoData
import com.crecrew.crecre.Data.TodayPost
import com.crecrew.crecre.R
import com.crecrew.crecre.UI.Activity.Community.CommunityDetailActivity
import kotlinx.android.synthetic.main.rv_item_today_post.view.*
import org.jetbrains.anko.startActivity
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ProfileHotVideoRecyclerViewAdapter(private val ctx : Context, private val dataList : ArrayList<ProfileHotVideoData>) : RecyclerView.Adapter<ProfileHotVideoRecyclerViewAdapter.Holder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx)!!.inflate(R.layout.rv_item_today_post, viewGroup, false)

        init(view)

        return Holder(view)
    }

    fun init(v :View){

        Log.e("hi","i'm in")
        var post_time = v.findViewById(R.id.rv_item_today_post_time) as TextView
        var post_comment = v.findViewById(R.id.rv_item_today_post_comment) as TextView

        post_time.visibility = GONE
        post_comment.visibility =GONE
    }


    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {

        if (dataList[position].thumbnail_url == "")
            Glide.with(ctx).load(R.drawable.icn_img_x).into(holder.thumbnail_url)
        else
            Glide.with(ctx).load(dataList[position].thumbnail_url).into(holder.thumbnail_url)
        holder.title.text = dataList[position].title
        holder.view_cnt.text = "조회수" + dataList[position].view_cnt.toString()
        holder.create_time.text = calculatePostTime(dataList[position].create_time.toString())

        holder.container.setOnClickListener {
            ctx.startActivity<CommunityDetailActivity>(
                // TODO: 정보 넣기
            )
        }
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var container = itemView.findViewById(R.id.rv_item_today_post_container) as RelativeLayout
        var thumbnail_url = itemView.findViewById(R.id.rv_item_today_post_thumnail) as ImageView
        var title = itemView.findViewById(R.id.rv_item_today_post_title) as TextView
        var create_time = itemView.findViewById(R.id.rv_item_today_post_recommend) as TextView
        var view_cnt = itemView.findViewById(R.id.rv_item_today_post_category) as TextView

    }

    fun calculatePostTime(time : String) : String{

        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm")
        val write_time : Date =  sdf.parse(time)
        val cur_time = Date()

        val diff : Long = cur_time.time - write_time.time
        var minute = diff / (1000 * 60)

        var result : String
        if(minute >= 0 && minute < 60)
            result = "$minute" + "분 전"
        else if(minute >= 60 && minute < 60*24) {
            minute /= 60
            result = "$minute" + "시간 전"
        }
        else if(minute >=60*24 && minute <60*24*30){
            minute /= (60 * 24)
            result = "$minute" + "일 전"
        }
        else if(minute >=60*24*30 && minute <60*24*365){
            minute /= (60 * 24*30)
            result = "$minute" + "달 전"
        }else{
            minute /= (60*24*365)
            result = "$minute" + "년 전"
        }
        return result
    }
}