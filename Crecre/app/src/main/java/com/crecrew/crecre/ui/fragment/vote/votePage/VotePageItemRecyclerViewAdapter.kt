package com.crecrew.crecre.ui.fragment.vote.votePage

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
import com.crecrew.crecre.data.VoteChoiceData
import com.crecrew.crecre.R

class VotePageItemRecyclerViewAdapter(
    val ctx: Context,
    val dataList: ArrayList<VoteChoiceData>,
    var myChoice: Int?,
    var isCurrent: Boolean
) :
    RecyclerView.Adapter<VotePageItemRecyclerViewAdapter.Holder>() {

    lateinit var listener: IItemCheckListener

    fun setOnItemClickListener(listener: IItemCheckListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_invote_choice, p0, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        Glide.with(ctx)
            .load(dataList[position].creator_profile_url)
            .apply(RequestOptions().circleCrop()).into(holder.img_thumnail)

        holder.item_name.text = dataList[position].name

        if (dataList[position].checked)
            holder.img_isCheck.setImageResource(R.drawable.btn_check)
        else
            holder.img_isCheck.setImageResource(R.drawable.btn_uncheck)


        if (isCurrent) {
            myChoice?.let {
                if (dataList[position].choice_idx == it)
                    holder.img_isCheck.setImageResource(R.drawable.btn_check)
                holder.ranktext.text = "${dataList[position].rank}등"
                holder.counttext.text = "${dataList[position].count}표"
                if (dataList[position].rank == 1)
                    holder.ranktext.setTextColor(Color.parseColor("#ff57f7"))
            } ?: run {
                holder.img_isCheck.setOnClickListener {
                    for (i in dataList.indices)
                        dataList[i].checked = false

                    dataList[position].checked = !dataList[position].checked

                    listener.onItemCheck(dataList[position].choice_idx)
                    notifyDataSetChanged()
                }
                holder.ranktext.visibility = View.GONE
                holder.counttext.visibility = View.GONE
            }
        } else {
            myChoice?.let{
                if (dataList[position].choice_idx == it)
                    holder.img_isCheck.setImageResource(R.drawable.btn_check)
            }
            holder.ranktext.text = "${dataList[position].rank}등"
            holder.counttext.text = "${dataList[position].count}표"
            if (dataList[position].rank == 1)
                holder.ranktext.setTextColor(Color.parseColor("#ff57f7"))
        }
    }


    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var img_thumnail = itemView.findViewById(R.id.item_img) as ImageView
        var item_name = itemView.findViewById(R.id.item_name) as TextView
        var img_isCheck = itemView.findViewById(R.id.item_ischeckimg) as ImageView
        var ranktext = itemView.findViewById(R.id.rank_text) as TextView
        var counttext = itemView.findViewById(R.id.count_text) as TextView
    }

    interface IItemCheckListener {
        fun onItemCheck(choiceIdx: Int)
    }
}



