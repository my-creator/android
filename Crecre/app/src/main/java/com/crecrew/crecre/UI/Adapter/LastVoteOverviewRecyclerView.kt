package com.crecrew.crecre.UI.Adapter


import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.crecrew.crecre.Data.LastVoteData
import com.crecrew.crecre.R
import android.graphics.drawable.shapes.OvalShape
import android.graphics.drawable.ShapeDrawable
import android.widget.RelativeLayout


class LastVoteOverviewRecyclerView(private val ctx : Context, private val dataList : ArrayList<LastVoteData>) : RecyclerView.Adapter<LastVoteOverviewRecyclerView.Holder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx)!!.inflate(R.layout.rv_item_last_vote, viewGroup, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {

        Glide.with(ctx).load(dataList[position].image).into(holder.image)
        holder.image.scaleType = ImageView.ScaleType.CENTER_CROP

        if(dataList[position].profile == "")
            Glide.with(ctx).load(R.drawable.img_profile).into(holder.profile)
        else {
            Glide.with(ctx).load(dataList[position].profile).into(holder.profile)
            holder.profile.setBackground(ShapeDrawable(OvalShape()))
            holder.profile.setClipToOutline(true)
        }

        holder.creator.text = dataList[position].creator
        holder.content.text = dataList[position].content

        holder.container.setOnClickListener {

        }
    }

    fun View.setWidth(value: Int) {
        val lp = layoutParams
        lp?.let {
            lp.width = value
            layoutParams = lp
        }
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var container = itemView.findViewById(R.id.rv_item_last_vote_rl_container) as RelativeLayout
        var image = itemView.findViewById(R.id.rv_item_last_vote_iv_image) as ImageView
        var profile = itemView.findViewById(R.id.rv_item_last_vote_iv_profile) as ImageView
        var creator = itemView.findViewById(R.id.rv_item_last_vote_creator) as TextView
        var content = itemView.findViewById(R.id.rv_item_last_vote_content) as TextView

    }
}