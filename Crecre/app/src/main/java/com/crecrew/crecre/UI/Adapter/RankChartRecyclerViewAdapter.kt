package com.crecrew.crecre.UI.Adapter

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.crecrew.crecre.Data.RankData
import com.crecrew.crecre.R
import com.crecrew.crecre.UI.Activity.CreatorProfileActivity
import org.jetbrains.anko.startActivity


class RankChartRecyclerViewAdapter(private val ctx : Context, private val dataList : ArrayList<RankData>) : RecyclerView.Adapter<RankChartRecyclerViewAdapter.Holder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx)!!.inflate(R.layout.rv_item_rank_creator, viewGroup, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {


        if (dataList[position].image == "")
            Glide.with(ctx).load(R.drawable.icn_profile).into(holder.image)
        else
            Glide.with(ctx).load(dataList[position].image)
                .apply(RequestOptions().circleCrop()).into(holder.image)

        if (dataList[position].gap > 0)
            Glide.with(ctx).load(R.drawable.icn_up).into(holder.arrow)
        else if(dataList[position].gap < 0) {
            Glide.with(ctx).load(R.drawable.icn_down).into(holder.arrow)
            holder.gap.setTextColor(Color.parseColor("#2befef"))
        }
        else {
            // Todo: 아무것도 안오르면 어떻게 처리할지 고민
        }

        holder.ranking.text = (position+1).toString()
        holder.gap.text = Math.abs(dataList[position].gap).toString()
        holder.category.text = dataList[position].category
        holder.name.text = dataList[position].name
        Glide.with(ctx).load(dataList[position].rank_img).into(holder.rank_img)

        var str = String.format("%,d",dataList[position].number)
        holder.number.text = str

        holder.container.setOnClickListener {
            ctx.startActivity<CreatorProfileActivity>(
                // TODO: 정보 넣기
            )
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
