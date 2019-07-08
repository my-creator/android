package com.crecrew.crecre.UI.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.crecrew.crecre.Data.VoteCurrentOverViewData
import com.crecrew.crecre.Data.VoteItemData
import com.crecrew.crecre.Data.VoteTestData
import com.crecrew.crecre.R
import com.crecrew.crecre.UI.Activity.Community.CommunityHotPostActivity
import com.crecrew.crecre.UI.Activity.VoteSuggestActivity
import com.crecrew.crecre.UI.Adapter.VoteCurrentRecyclerViewAdapter
import com.crecrew.crecre.UI.Adapter.VoteItemRecyclerViewAdapter
import com.crecrew.crecre.UI.Adapter.VoteTestAdapter
import kotlinx.android.synthetic.main.rv_item_invote_choice.*
import kotlinx.android.synthetic.main.fragment_community_popular.view.*
import kotlinx.android.synthetic.main.fragment_vote.*
import kotlinx.android.synthetic.main.fragment_vote_current.view.*
import kotlinx.android.synthetic.main.rv_item_currentvote_card.*
import org.jetbrains.anko.support.v4.startActivity

class VoteCurrentFragment : Fragment() {
    lateinit var rootView:View
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        /*나중에 서버통신 ㅇㅇ*//*
        var dataList : ArrayList<VoteItemData> = ArrayList()
        dataList.add(VoteItemData("https://m.blog.naver.com/PostView.nhn?blogId=jini2877&logNo=221274305035&proxyReferer=https%3A%2F%2Fwww.google.com%2F&view=img_1","딕헌터", isChecked = false))
        dataList.add(VoteItemData("https://namu.wiki/w/파일:봄나물.jpg","딕딕딕", isChecked = false))
        dataList.add(VoteItemData("https://namu.wiki/w/파일:욕망의%20참치회.jpg","thㅏ시미",isChecked = false))
        dataList.add(VoteItemData("https://namu.wiki/w/파일:욕망의%20참치회.jpg","thㅏ시미",isChecked = false))

        var voteItemRecyclerViewAdapter = VoteItemRecyclerViewAdapter(context!!, dataList)
        rootView.rv_fragment_vote_current.adapter = voteItemRecyclerViewAdapter
        arguments?.let {
        }*/


        /*var dataList : ArrayList<VoteCurrentOverViewData> = ArrayList()
        dataList.add(VoteCurrentOverViewData("https://ichef.bbci.co.uk/news/660/cpsprodpb/7624/production/_104444203_d03fb5eb-685c-42c3-8fa2-eea0ee2dac26.jpg",2, "절 대 투 표 해^0^", "o"))
        dataList.add(VoteCurrentOverViewData("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRhrktf6ORs6WsmFEnxiB2KIdZe26_QhkqQTJqJFuOQoMrhuW9x",1, "@==(^0^@)", "선풍적 인기!"))
        */

        //this is for test
        var dataList : ArrayList<VoteTestData> = ArrayList()
        dataList.add(VoteTestData("https://ichef.bbci.co.uk/news/660/cpsprodpb/7624/production/_104444203_d03fb5eb-685c-42c3-8fa2-eea0ee2dac26.jpg", false, 2, "다음 중 가장 맛있는 라면은?", "컵라면 포함",
            "진라면", "육개장", "참깨라면", "짜파게티","안성탕면",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRJYBWWSb_aRjnJ3wCdWQfddr8guktDwhMhItH5JJx4ziO6KKuF", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ__opO3mmxb-XiTNQi8sHVPeRcMgQCtfQC4QivTH32JHtN7c5b"
        , "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTPopgm75mGeWIhsaRKVFg1wGmrYXTjV0R4g1cFnn_TNvYffrdw", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTaZy-oN8sO58qr0xuGDI0383LMo6nfHk-13E1JVPTcBTrWVX6L", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSv2BDLe72_Sd3C0c6Vhdkxl_f2KhEpVlconT4X5NZfJ_qG47KP",
            5,4,3,2,1))

        dataList.add(VoteTestData("https://ichef.bbci.co.uk/news/660/cpsprodpb/7624/production/_104444203_d03fb5eb-685c-42c3-8fa2-eea0ee2dac26.jpg", true, 2, "다음 중 가장 맛있는 라면은?", "컵라면 포함",
            "진라면", "육개장", "참깨라면", "짜파게티","안성탕면",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRJYBWWSb_aRjnJ3wCdWQfddr8guktDwhMhItH5JJx4ziO6KKuF", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ__opO3mmxb-XiTNQi8sHVPeRcMgQCtfQC4QivTH32JHtN7c5b"
            , "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTPopgm75mGeWIhsaRKVFg1wGmrYXTjV0R4g1cFnn_TNvYffrdw", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTaZy-oN8sO58qr0xuGDI0383LMo6nfHk-13E1JVPTcBTrWVX6L", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSv2BDLe72_Sd3C0c6Vhdkxl_f2KhEpVlconT4X5NZfJ_qG47KP",
            5,4,3,2,1))


        /*var voteCurrentRecyclerViewAdapter = VoteCurrentRecyclerViewAdapter(context!!, dataList)
        rootView.rv_fragment_vote_current.adapter = voteCurrentRecyclerViewAdapter
        arguments?.let {
        }*/


        //this is for test
        var voteTestAdapter = VoteTestAdapter(context!!, dataList)
        rootView.rv_fragment_vote_current.adapter = voteTestAdapter
        arguments?.let {
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        rootView = inflater.inflate(R.layout.fragment_vote_current, container, false)
        return rootView
    }
}