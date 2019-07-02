package com.crecrew.crecre.UI.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.crecrew.crecre.Data.CommunityFavoriteData
import com.crecrew.crecre.R

class CommunityFavoriteRecyclerViewAdapter(val ctx : Context, val dataList : ArrayList<CommunityFavoriteData>) : RecyclerView.Adapter<CommunityFavoriteRecyclerViewAdapter.Holder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_favorite_community_frag, viewGroup, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        //게시판제목
        holder.title.text = dataList[position].title

        //container클릭시
        holder.container.setOnClickListener {
        }

        var img_like = 0

        holder.img_like.setOnClickListener {
            if(img_like == 0)
            {
                holder.img_like.isSelected = true
                img_like = 1
            }
            else{
                holder.img_like.isSelected = false
                img_like = 0
            }
        }

    }

    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView){
        var container = itemView.findViewById(R.id.rl_item_favorite_container) as RelativeLayout
        var title = itemView.findViewById(R.id.tv_rv_title_community_frag) as TextView
        var img_like = itemView.findViewById(R.id.btn_favorite_heart_community_frag) as ImageView
    }

}