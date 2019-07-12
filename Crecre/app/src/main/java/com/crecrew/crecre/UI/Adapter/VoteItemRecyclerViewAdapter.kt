package com.crecrew.crecre.UI.Adapter

import android.content.Context
import android.support.v7.view.menu.MenuView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.crecrew.crecre.Data.VoteItemData
import com.crecrew.crecre.R

/*class VoteItemRecyclerViewAdapter(val ctx:Context, val dataList: ArrayList<VoteItemData>):RecyclerView.Adapter<VoteItemRecyclerViewAdapter.Holder>(){
    override fun onBindViewHolder(holder: Holder, position: Int) {
        Glide.with(ctx)
            .load(dataList[position].)
            .into(holder.img_thumnail)

        holder.item_name.text = dataList[position].item_name;
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView){
        var img_thumnail = itemView.findViewById(R.id.item_img) as ImageView
        var item_name = itemView.findViewById(R.id.item_name) as TextView
        var img_isCheck = itemView.findViewById(R.id.item_ischeckimg) as ImageView
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int ) :Holder{
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_invote_choice, viewGroup, false)
        return Holder(view)
    }
}
*/