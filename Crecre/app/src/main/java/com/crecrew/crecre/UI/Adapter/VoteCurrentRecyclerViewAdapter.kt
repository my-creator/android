@file:Suppress("UNREACHABLE_CODE")

package com.crecrew.crecre.UI.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.crecrew.crecre.Data.VoteCurrentOverViewData
import com.crecrew.crecre.R

    class VoteCurrentRecyclerViewAdapter(val ctx:Context, val dataList: ArrayList<VoteCurrentOverViewData>):RecyclerView.Adapter<VoteCurrentRecyclerViewAdapter.Holder>() {
        inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView){
            var img_thumnail1 = itemView.findViewById(R.id.item_img1) as ImageView
            var img_thumnail2 = itemView.findViewById(R.id.item_img2) as ImageView
            var img_thumnail3 = itemView.findViewById(R.id.item_img3) as ImageView
            var img_thumnail4 = itemView.findViewById(R.id.item_img4) as ImageView
            var img_thumnail5 = itemView.findViewById(R.id.item_img5) as ImageView
            var item_name1 = itemView.findViewById(R.id.item_name1) as TextView
            var item_name2 = itemView.findViewById(R.id.item_name2) as TextView
            var item_name3:TextView = itemView.findViewById(R.id.item_name3) as TextView
            var item_name4:TextView = itemView.findViewById(R.id.item_name4) as TextView
            var item_name5:TextView = itemView.findViewById(R.id.item_name5) as TextView
            var img_isCheck1 = itemView.findViewById(R.id.item_ischeckimg1) as ImageView
            var img_isCheck2 = itemView.findViewById(R.id.item_ischeckimg1) as ImageView
            var img_isCheck3 = itemView.findViewById(R.id.item_ischeckimg1) as ImageView
            var img_isCheck4 = itemView.findViewById(R.id.item_ischeckimg1) as ImageView
            var img_isCheck5 = itemView.findViewById(R.id.item_ischeckimg1) as ImageView
            var isCheck1: Boolean = false;
            var isCheck2: Boolean = false;
            var isCheck3: Boolean = false;
            var isCheck4: Boolean = false;
            var isCheck5: Boolean = false;
        }

        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): VoteCurrentRecyclerViewAdapter.Holder {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun getItemCount(): Int {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onBindViewHolder(holder: Holder, position: Int) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

            holder.img_isCheck1.setOnClickListener{
                holder.isCheck1 = true;
                holder.isCheck2 = false;
                holder.isCheck3 = false;
                holder.isCheck4 = false;
                holder.isCheck5 = false;
            }
            holder.img_isCheck2.setOnClickListener{
                holder.isCheck2 = true;
                holder.isCheck1 = false;
                holder.isCheck3 = false;
                holder.isCheck4 = false;
                holder.isCheck5 = false;
            }
            holder.img_isCheck3.setOnClickListener{
                holder.isCheck3 = true;
                holder.isCheck2 = false;
                holder.isCheck1 = false;
                holder.isCheck4 = false;
                holder.isCheck5 = false;
            }
            holder.img_isCheck4.setOnClickListener{
                holder.isCheck4 = true;
                holder.isCheck2 = false;
                holder.isCheck3 = false;
                holder.isCheck1 = false;
                holder.isCheck5 = false;
            }
            holder.img_isCheck5.setOnClickListener{
                holder.isCheck5 = true;
                holder.isCheck2 = false;
                holder.isCheck3 = false;
                holder.isCheck1 = false;
                holder.isCheck4 = false;
            }
            if (holder.isCheck1) holder.img_isCheck1.setBackgroundResource(R.drawable.btn_check)
            else if (holder.isCheck2) holder.img_isCheck2.setBackgroundResource(R.drawable.btn_check)
            else if (holder.isCheck3) holder.img_isCheck3.setBackgroundResource(R.drawable.btn_check)
            else if (holder.isCheck4) holder.img_isCheck4.setBackgroundResource(R.drawable.btn_check)
            else if (holder.isCheck5) holder.img_isCheck5.setBackgroundResource(R.drawable.btn_check)

        }

    }
