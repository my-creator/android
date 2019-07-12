package com.crecrew.crecre.UI.Adapter

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.crecrew.crecre.Data.VoteChoiceData
import com.crecrew.crecre.Data.VoteData
import com.crecrew.crecre.Data.VoteItemData
import com.crecrew.crecre.Data.VoteTestData
import com.crecrew.crecre.R

class VoteChoiceRecyclerviewAdapter (val ctx: Context, val dataList: ArrayList<VoteChoiceData>) : RecyclerView.Adapter<VoteChoiceRecyclerviewAdapter.Holder>() {

    var hangmock = dataList.size
    var isChecked = ArrayList<Boolean>()

    lateinit var listener: onItemCheckListener

    fun setOnItemClickListener(listener: onItemCheckListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): VoteChoiceRecyclerviewAdapter.Holder {
        val view:View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_invote_choice, p0, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        Glide.with(ctx)
            .load(dataList[position].creator_profile_url)
            .apply(RequestOptions().circleCrop()).into(holder.img_thumnail)

        holder.item_name.text = dataList[position].name;

        //holder.img_isCheck.setImageResource(R.drawable.btn_uncheck)

        for (i in dataList.indices) isChecked.add(false)

        if (dataList[position].cho == -2){
            if (dataList[position].checked) holder.img_isCheck.setImageResource(R.drawable.btn_check)
            else holder.img_isCheck.setImageResource(R.drawable.btn_uncheck)
        }else{
            if (!isChecked[position]) holder.img_isCheck.setImageResource(R.drawable.btn_uncheck)
            else holder.img_isCheck.setImageResource(R.drawable.btn_check)
        }

       holder.img_isCheck.setOnClickListener {
            if (!dataList[position].checked) isChecked[position] = true
            dataList[position].cho = position

            for(i in 0..dataList.size-1) {
                if (i != position) {
                    isChecked[i] = false
                    dataList[position].cho = -1
                }
                else dataList[i].cho = i
            }
           listener.onCheck(true)
           notifyDataSetChanged()
        }
        if (dataList[position].checked==false){
            holder.ranktext.setVisibility(View.GONE)
            holder.counttext.setVisibility(View.GONE)
        }
        if (dataList[position].rank == 1){holder.ranktext.setTextColor(Color.parseColor("#ff57f7"))}
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var img_thumnail = itemView.findViewById(R.id.item_img) as ImageView
        var item_name = itemView.findViewById(R.id.item_name) as TextView
        var img_isCheck = itemView.findViewById(R.id.item_ischeckimg) as ImageView
        var ranktext = itemView.findViewById(R.id.rank_text) as TextView
        var counttext = itemView.findViewById(R.id.count_text) as TextView
    }

    interface onItemCheckListener {
        fun onCheck(isCheck: Boolean)
    }

}



