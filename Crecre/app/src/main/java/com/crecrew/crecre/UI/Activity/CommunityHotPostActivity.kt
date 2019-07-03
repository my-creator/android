package com.crecrew.crecre.UI.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.crecrew.crecre.Data.CommunityHotPostData
import com.crecrew.crecre.R
import com.crecrew.crecre.UI.Adapter.CommunityFavoriteRecyclerViewAdapter
import com.crecrew.crecre.UI.Adapter.CommunityHotPostRecyclerViewAdapter
import com.crecrew.crecre.UI.Adapter.CommunityPostFragmentAdapter
import kotlinx.android.synthetic.main.activity_community_hot_post.*
import kotlinx.android.synthetic.main.activity_community_search.*

class CommunityHotPostActivity : AppCompatActivity() {

    lateinit var communityHotPostRecyclerViewAdapter:CommunityHotPostRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community_hot_post)

        configureRecyclerView()

        btn_back_hotpost_community_act.setOnClickListener {
            finish()
        }
    }

    //추가해야할 부분 ##서버통신
    /*
    private fun configureTitleBar(){
        title = intent.getStringExtra("title")
        product_id = intent.getIntExtra("idx", -1)
        if(product_id == -1) finish()

        txt_toolbar_product_title.text = title

        btn_toolbar_product_like.setOnClickListener {
            btn_toolbar_product_like.isSelected = !btn_toolbar_product_like.isSelected
        }

        btn_toolbar_product_back.setOnClickListener {
            finish()
        }
    }
    */

    private fun configureRecyclerView() {
        var dataList: ArrayList<CommunityHotPostData> = ArrayList()
        dataList.add(CommunityHotPostData("http://sopt.org/wp/wp-content/uploads/2014/01/24_SOPT-LOGO_COLOR-1.png",1,
                "햇님이 먹었던 과자 브랜드 이게 맞나?", 10, 10, "18:47","",0,0))
        dataList.add(CommunityHotPostData("", 1,
            "안녀안알ㄴ여ㅏㄴ여낭", 1, 8, "14:27","",0,0))
        dataList.add(CommunityHotPostData("",1,
            "입짧은햇님보세여", 19, 19, "12:35","",0,0))
        dataList.add(CommunityHotPostData("http://sopt.org/wp/wp-content/uploads/2014/01/24_SOPT-LOGO_COLOR-1.png",0,
            "먹방요정", 4, 8, "11:20","",0,0))
        dataList.add(CommunityHotPostData("http://sopt.org/wp/wp-content/uploads/2014/01/24_SOPT-LOGO_COLOR-1.png",0,
            "바부야", 2, 7, "04:30","",0,0))

        communityHotPostRecyclerViewAdapter = CommunityHotPostRecyclerViewAdapter(this, dataList)
        rv_community_hotpost_act_list.adapter = communityHotPostRecyclerViewAdapter
        rv_community_hotpost_act_list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    }


}
