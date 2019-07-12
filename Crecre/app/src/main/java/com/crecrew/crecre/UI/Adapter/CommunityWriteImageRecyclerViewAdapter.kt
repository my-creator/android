package com.crecrew.crecre.UI.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.crecrew.crecre.Data.CommunityWriteImageData
import com.crecrew.crecre.R

class CommunityWriteImageRecyclerViewAdapter(val ctx: Context, val dataList: ArrayList<CommunityWriteImageData>
) : RecyclerView.Adapter<CommunityWriteImageRecyclerViewAdapter.Holder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): CommunityWriteImageRecyclerViewAdapter.Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_insert_image_commu_detail_act, viewGroup, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        Glide.with(ctx).load(dataList[position].image).into(holder.image)

    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image = itemView.findViewById(R.id.img_camera_insert_rv) as ImageView

    }

}