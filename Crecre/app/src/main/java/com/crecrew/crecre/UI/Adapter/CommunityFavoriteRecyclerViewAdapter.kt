package com.crecrew.crecre.UI.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.crecrew.crecre.Network.ApplicationController
import com.crecrew.crecre.Network.Get.CommunityBoardData
import com.crecrew.crecre.Network.CommunityNetworkService
import com.crecrew.crecre.Network.Get.CommunityRequestCntData
import com.crecrew.crecre.Network.Get.GetBoardRequestNumResponse
import com.crecrew.crecre.Network.Post.PostCommunityFavoriteLikeResponse
import com.crecrew.crecre.R
import com.crecrew.crecre.UI.Activity.Community.CommunityHotPostActivity
import org.jetbrains.anko.startActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommunityFavoriteRecyclerViewAdapter(
    val ctx: Context,
    val dataList: ArrayList<CommunityBoardData>,
    val flag: Int
) : RecyclerView.Adapter<CommunityFavoriteRecyclerViewAdapter.Holder>() {

    val communityNetworkService: CommunityNetworkService by lazy {
        ApplicationController.instance.communityNetworkService
    }

    val boardIdx = -1

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_favorite_community_frag, viewGroup, false)

        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        //게시판제목
        holder.title.text = dataList[position].name

        //container클릭시
        holder.container.setOnClickListener {
            ctx.startActivity<CommunityHotPostActivity>(
                "title" to dataList[position].name,
                //board_idx
                "idx" to dataList[position].idx
            )
        }
        var img_like = 0

        //is_love가 1이면 즐겨찾기에 색칠된 하트
        if (dataList[position].is_love == 1) {
            holder.img_like.isSelected = true
            img_like = 1
        }

        //즐겨찾기 버튼 눌렀을 경우
        holder.img_like.setOnClickListener {
            if (img_like == 0) {
                holder.img_like.isSelected = true
                img_like = 1
                //여기서 idx는 board_idx를 말함
                postLikeOnClickResponse(dataList[position].idx)
            } else {
                holder.img_like.isSelected = false
                img_like = 0
                deleteLikeoffClickResponse(dataList[position].idx)
            }
        }

        if (flag == 1) {
            //마지막 아이템의 line은 삭제
            if (position == 0)
                holder.line.visibility = View.INVISIBLE
        }
    }

    //좋아요 누르기 보여주기 통신
    fun postLikeOnClickResponse(boardIdx: Int) {
        val postBoardsFavoriteLike: Call<PostCommunityFavoriteLikeResponse> =
            communityNetworkService.postBoardsFavoriteLike(
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkeCI6MTIsImdyYWRlIjoiQURNSU4iLCJuYW1lIjoi66qF64uk7JewIiwiaWF0IjoxNTYyNDIzOTUyLCJleHAiOjE1NjM2MzM1NTIsImlzcyI6InlhbmcifQ.DbGROLSRyAm_NN1qcQ5sLmjxKpUACyMsFQRiDd2z3Lw"
                , boardIdx
            )

        postBoardsFavoriteLike.enqueue(object : Callback<PostCommunityFavoriteLikeResponse> {

            override fun onFailure(call: Call<PostCommunityFavoriteLikeResponse>, t: Throwable) {
                Log.e("즐겨찾기 누르기 fail", t.toString())
            }

            override fun onResponse(
                call: Call<PostCommunityFavoriteLikeResponse>,
                response: Response<PostCommunityFavoriteLikeResponse>
            ) {

                if (response.isSuccessful) {

                }
            }
        })
    }

    //좋아요 누르기 취소 통신
    fun deleteLikeoffClickResponse(boardIdx: Int){
        val postBoardsFavoriteLike: Call<PostCommunityFavoriteLikeResponse> =
            communityNetworkService.deleteBoardsFavoriteLike(
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkeCI6MTIsImdyYWRlIjoiQURNSU4iLCJuYW1lIjoi66qF64uk7JewIiwiaWF0IjoxNTYyNDIzOTUyLCJleHAiOjE1NjM2MzM1NTIsImlzcyI6InlhbmcifQ.DbGROLSRyAm_NN1qcQ5sLmjxKpUACyMsFQRiDd2z3Lw"
                , boardIdx
            )

        postBoardsFavoriteLike.enqueue(object : Callback<PostCommunityFavoriteLikeResponse> {

            override fun onFailure(call: Call<PostCommunityFavoriteLikeResponse>, t: Throwable) {
                Log.e("즐겨찾기 누르기 fail", t.toString())
            }

            override fun onResponse(
                call: Call<PostCommunityFavoriteLikeResponse>,
                response: Response<PostCommunityFavoriteLikeResponse>
            ) {

                if (response.isSuccessful) {

                }
            }
        })
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var container = itemView.findViewById(R.id.rl_item_favorite_container) as RelativeLayout
        var title = itemView.findViewById(R.id.tv_rv_title_community_frag) as TextView
        var img_like = itemView.findViewById(R.id.img_favorite_heart_community_frag) as ImageView
        var line = itemView.findViewById(R.id.rl_line_favorite_commu_frag) as RelativeLayout
    }

}