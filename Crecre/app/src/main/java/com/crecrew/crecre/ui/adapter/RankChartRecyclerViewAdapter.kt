package com.crecrew.crecre.ui.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.crecrew.crecre.network.get.RankData
import com.crecrew.crecre.R
import com.crecrew.crecre.ui.activity.CreatorProfileActivity


class RankChartRecyclerViewAdapter(private val ctx : Context, private val dataList : ArrayList<RankData>, private val flag: Int) : RecyclerView.Adapter<RankChartRecyclerViewAdapter.Holder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx)!!.inflate(R.layout.rv_item_rank_creator, viewGroup, false)

        init(view)

        return Holder(view)
    }

    fun init(v: View){

        var container = v.findViewById(R.id.rv_item_rank_creator_container) as LinearLayout
        var raking_container = v.findViewById(R.id.rv_item_rank_creator_rl_ranking_container) as RelativeLayout

        // Todo: flag를 Int가 아니라 context로 넘겨주는 방법!

        // 크리에이터 검색결과
        if(flag == 0) {
            container.setBackgroundColor(Color.parseColor("#ffffff"))
            raking_container.visibility = GONE
        }
        // 랭킹
        else{
            container.setBackgroundColor(Color.parseColor("#f9fafa"))
            raking_container.visibility = VISIBLE

        }

    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {


        if (dataList[position].profile_url == "")
            Glide.with(ctx).load(R.drawable.icn_profile).into(holder.image)
        else
            Glide.with(ctx).load(dataList[position].profile_url)
                .apply(RequestOptions().circleCrop()).into(holder.image)


        Log.e("updown", dataList[position].upDown.toString())

        if (dataList[position].upDown > 0)
            Glide.with(ctx).load(R.drawable.icn_up).into(holder.arrow)
        else if(dataList[position].upDown < 0) {
            Glide.with(ctx).load(R.drawable.icn_down).into(holder.arrow)
            holder.gap.setTextColor(Color.parseColor("#2befef"))
        }

        holder.ranking.text = (position+1).toString()
        if(dataList[position].upDown != 0) {
            holder.gap.text = Math.abs(dataList[position].upDown).toString()
        }else{
            holder.gap.text = ""
        }

        holder.ranking.text = dataList[position].ranking.toString()
        holder.category.text = dataList[position].categoryName
        holder.name.text = dataList[position].creatorName
        Glide.with(ctx).load(dataList[position].img_url).into(holder.rank_img)

        if(flag == 1) {
            var str = String.format("%,d", dataList[position].youtube_subscriber_cnt)
            holder.number.text = str
        }
        else if(flag == 2){
            var str = String.format("%,d", dataList[position].youtube_view_cnt)
            holder.number.text = str
        }

        holder.container.setOnClickListener {
            val intent = Intent(ctx, CreatorProfileActivity::class.java)
            intent.putExtra("creator_idx",dataList[position].idx)
            ctx.startActivity(intent)
        }
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var container = itemView.findViewById(R.id.rv_item_rank_creator_container) as LinearLayout
        var ranking = itemView.findViewById(R.id.rv_item_rank_creator_ranking) as TextView
        var gap = itemView.findViewById(R.id.rv_item_rank_creator_gap) as TextView
        var arrow = itemView.findViewById(R.id.rv_item_rank_creator_arrow) as ImageView
        var image = itemView.findViewById(R.id.rv_item_rank_creator_img) as ImageView
        var category = itemView.findViewById(R.id.rv_item_rank_creator_category) as TextView
        var name = itemView.findViewById(R.id.rv_item_rank_creator_name) as TextView
        var rank_img = itemView.findViewById(R.id.rv_item_rank_creator_rank_img) as ImageView
        var number = itemView.findViewById(R.id.rv_item_rank_creator_number) as TextView
    }
}
