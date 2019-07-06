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
import com.crecrew.crecre.R

/*class VoteItemRecyclerViewAdapter(val ctx:Context, val dataList: ArrayList<vote_itemdata>):RecyclerView.Adapter<VoteItemRecyclerViewAdapter.Holder>(){
    override fun onBindViewHolder(holder: Holder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        holder.item_name.text = dataList[position].item_name;

        holder.img_isCheck.setOnClickListener{
            if (holder.isCheck == false) holder.isCheck = true;
        }
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return dataList.size
    }

    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView){
        var img_thumnail = itemView.findViewById(R.id.item_img) as ImageView
        var item_name = itemView.findViewById(R.id.item_name) as TextView
        var img_isCheck = itemView.findViewById(R.id.item_ischeckimg) as ImageView
        var isCheck: Boolean = false;
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int ) :Holder{
        val view: View = LayoutInflater.from(ctx)
    }
}*/