@file:Suppress("UNREACHABLE_CODE")
package com.crecrew.crecre.UI.Adapter

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.crecrew.crecre.Data.VoteTestData
import com.crecrew.crecre.R
import android.graphics.drawable.shapes.OvalShape
import android.graphics.drawable.ShapeDrawable
import android.widget.*
import com.bumptech.glide.request.RequestOptions
import org.jetbrains.anko.image


class VoteTestAdapter(val ctx: Context, val dataList: ArrayList<VoteTestData>) :
    RecyclerView.Adapter<VoteTestAdapter.Holder>() {

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var img_thumnail = itemView.findViewById(R.id.card_main_image_test) as ImageView
        var txt_dayleft = itemView.findViewById(R.id.rv_item_current_card_dayleft_test) as TextView
        var txt_ongoing = itemView.findViewById(R.id.rv_item_current_card_isongoing_test) as TextView
        var stamp = itemView.findViewById(R.id.rv_item_current_card_stamp_test) as ImageView
        var title = itemView.findViewById(R.id.rv_item_vote_title_test) as TextView
        var explain = itemView.findViewById(R.id.rv_item_vote_explain_test) as TextView
        //개별 아이템들ㅇㅇ
        var img_item1 = itemView.findViewById(R.id.item_img1_test) as ImageView
        var img_item2 = itemView.findViewById(R.id.item_img2_test) as ImageView
        var img_item3 = itemView.findViewById(R.id.item_img3_test) as ImageView
        var img_item4 = itemView.findViewById(R.id.item_img4_test) as ImageView
        var img_item5 = itemView.findViewById(R.id.item_img5_test) as ImageView
        var txt_itemname1 = itemView.findViewById(R.id.item_name1_test) as TextView
        var txt_itemname2 = itemView.findViewById(R.id.item_name2_test) as TextView
        var txt_itemname3 = itemView.findViewById(R.id.item_name3_test) as TextView
        var txt_itemname4 = itemView.findViewById(R.id.item_name4_test) as TextView
        var txt_itemname5 = itemView.findViewById(R.id.item_name5_test) as TextView
        var txt_votenum1 = itemView.findViewById(R.id.vote_num1_test) as TextView
        var txt_votenum2 = itemView.findViewById(R.id.vote_num2_test) as TextView
        var txt_votenum3 = itemView.findViewById(R.id.vote_num3_test) as TextView
        var txt_votenum4 = itemView.findViewById(R.id.vote_num4_test) as TextView
        var txt_votenum5 = itemView.findViewById(R.id.vote_num5_test) as TextView
        var txt_rank1 = itemView.findViewById(R.id.vote_rank1_test) as TextView
        var txt_rank2 = itemView.findViewById(R.id.vote_rank2_test) as TextView
        var txt_rank3 = itemView.findViewById(R.id.vote_rank3_test) as TextView
        var txt_rank4 = itemView.findViewById(R.id.vote_rank4_test) as TextView
        var txt_rank5 = itemView.findViewById(R.id.vote_rank5_test) as TextView
        var imageView1 = itemView.findViewById(R.id.vote_check1_test) as ImageView
        var imageView2 = itemView.findViewById(R.id.vote_check2_test) as ImageView
        var imageView3 = itemView.findViewById(R.id.vote_check3_test) as ImageView
        var imageView4 = itemView.findViewById(R.id.vote_check4_test) as ImageView
        var imageView5 = itemView.findViewById(R.id.vote_check5_test) as ImageView
        var line1 = itemView.findViewById(R.id.ll1_test) as LinearLayout
        var line2 = itemView.findViewById(R.id.ll2_test) as LinearLayout
        var line3 = itemView.findViewById(R.id.ll3_test) as LinearLayout
        var line4 = itemView.findViewById(R.id.ll4_test) as LinearLayout
        var line5 = itemView.findViewById(R.id.ll5_test) as LinearLayout
        var letsVote = itemView.findViewById(R.id.lets_vote_test) as TextView
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): VoteTestAdapter.Holder {
        val view:View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_currentvote_card_test, p0, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val RGroup :RadioGroup
        Glide.with(ctx)
            .load(dataList[position].ImageURL)
            .into(holder.img_thumnail)
        Glide.with(ctx)
            .load(dataList[position].itemimg1)
            .apply(RequestOptions().circleCrop()).into(holder.img_item1)
        Glide.with(ctx)
            .load(dataList[position].itemimg2)
            .apply(RequestOptions().circleCrop()).into(holder.img_item2)
        Glide.with(ctx)
            .load(dataList[position].itemimg3)
            .apply(RequestOptions().circleCrop()).into(holder.img_item3)
        Glide.with(ctx)
            .load(dataList[position].itemimg4)
            .apply(RequestOptions().circleCrop()).into(holder.img_item4)
        Glide.with(ctx)
            .load(dataList[position].itemimg5)
            .apply(RequestOptions().circleCrop()).into(holder.img_item5)

        holder.title.text = dataList[position].title
        holder.explain.text = "# " + dataList[position].explain
        holder.txt_itemname1.text = dataList[position].itemname1
        holder.txt_itemname2.text = dataList[position].itemname2
        holder.txt_itemname3.text = dataList[position].itemname3
        holder.txt_itemname4.text = dataList[position].itemname4
        holder.txt_itemname5.text = dataList[position].itemname5
        holder.img_thumnail.scaleType = ImageView.ScaleType.CENTER_CROP
        holder.imageView1.setOnClickListener{
            holder.imageView1.setImageResource(R.drawable.btn_check)
            holder.imageView2.setImageResource(R.drawable.btn_uncheck)
            holder.imageView3.setImageResource(R.drawable.btn_uncheck)
            holder.imageView4.setImageResource(R.drawable.btn_uncheck)
            holder.imageView5.setImageResource(R.drawable.btn_uncheck)
        }

        if (dataList[position].isVotefinish == false ) //진행 중이면
        {
            holder.txt_ongoing.setVisibility(View.GONE)
            holder.txt_dayleft.text = "${dataList[position].date}일 후 개표"
            holder.txt_rank1.setVisibility(View.GONE)
            holder.txt_rank2.setVisibility(View.GONE)
            holder.txt_rank3.setVisibility(View.GONE)
            holder.txt_rank4.setVisibility(View.GONE)
            holder.txt_rank5.setVisibility(View.GONE)
            holder.txt_votenum1.setVisibility(View.GONE)
            holder.txt_votenum2.setVisibility(View.GONE)
            holder.txt_votenum4.setVisibility(View.GONE)
            holder.txt_votenum3.setVisibility(View.GONE)
            holder.txt_votenum5.setVisibility(View.GONE)
            holder.letsVote.text = "투표하기"
            holder.stamp.setVisibility(View.GONE)
            if (dataList[position].itemname5.isEmpty()) {
                holder.line5.setVisibility(View.GONE)
                if (dataList[position].itemname4.isEmpty()){
                    holder.line4.setVisibility(View.GONE)
                    if (dataList[position].itemname5.isEmpty()){
                        holder.line5.setVisibility(View.GONE)
                    }
                }
            }
            else{

            }

        }else {
            //여기 수정해야 합니다
            holder.txt_dayleft.text = "${dataList[position].date}일 후 마감"
            holder.letsVote.text = "투표완료"
            holder.txt_rank1.text = "1등"
            holder.txt_rank2.text = "2등"
            holder.txt_rank3.text = "3등"
            holder.txt_rank4.text = "4등"
            holder.txt_rank5.text = "5등"
            holder.txt_votenum1.text = "${dataList[position].votenum1}표"
            holder.txt_votenum2.text = "${dataList[position].votenum2}표"
            holder.txt_votenum3.text = "${dataList[position].votenum3}표"
            holder.txt_votenum4.text = "${dataList[position].votenum4}표"
            holder.txt_votenum5.text = "${dataList[position].votenum5}표"
            if (dataList[position].itemname5.isEmpty()) holder.line5.setVisibility(View.GONE)
            if (dataList[position].itemname4.isEmpty()) holder.line4.setVisibility(View.GONE)
            if (dataList[position].itemname3.isEmpty()) holder.line3.setVisibility(View.GONE)
            holder.letsVote.setTextColor(Color.parseColor("#aaaaaa"));
        }

    }


}


