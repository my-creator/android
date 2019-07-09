package com.crecrew.crecre.UI.Activity

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.crecrew.crecre.Data.ProfileHotVideoData
import com.crecrew.crecre.Data.TodayPost
import android.view.inputmethod.InputMethodManager
import com.crecrew.crecre.R
import com.crecrew.crecre.UI.Activity.Community.CommunityHotPostActivity
import com.crecrew.crecre.UI.Adapter.ProfileHotVideoRecyclerViewAdapter
import com.crecrew.crecre.UI.Adapter.TodayPostRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_creator_profile.*
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.startActivity
import java.util.ArrayList

class CreatorProfileActivity : AppCompatActivity() {

    lateinit var profileHotVideoRecyclerViewAdapter: ProfileHotVideoRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creator_profile)
        configureRecyclerView()

        val intent = intent
        val creator_name = intent.getStringExtra("creator_name")

        activity_creator_profile_btn_go_fanpage.setOnClickListener{
            startActivity<CommunityHotPostActivity>()
        }

        activity_creator_profile_btn_join_stat.setOnClickListener{
            startActivity<ProfileJoinStatActivity>()
        }
        // activity_creator_profile_tv_name.setText(creator_name)

    }

    private fun configureRecyclerView(){
        var profileHotDataList: ArrayList<ProfileHotVideoData> = ArrayList()

        profileHotDataList.add(ProfileHotVideoData(2, "대박대박 정호의 누네띄네 100개 먹방 영상", "https://www.naver.com", 1200000, "https://i.ytimg.com/vi/SzJo9QfhZg8/maxresdefault.jpg","2019-08-07 04:51","정호네식당"))
        profileHotDataList.add(ProfileHotVideoData(4, "대박대박 시연의 좋은데이 100개 먹방 영상", "https://www.naver.com", 1000000,"https://i.ytimg.com/vi/SzJo9QfhZg8/maxresdefault.jpg","2019-10-07 04:51","시연네식당"))
        profileHotDataList.add(ProfileHotVideoData(6, "대박대박 홍삼의 고구마간식 100개 먹방 영상", "https://www.naver.com", 120000, "https://i.ytimg.com/vi/SzJo9QfhZg8/maxresdefault.jpg","2019-07-02 04:51","홍삼네식당"))

        profileHotVideoRecyclerViewAdapter = ProfileHotVideoRecyclerViewAdapter(this, profileHotDataList)
        activity_creator_profile_rv_hot_video.adapter = profileHotVideoRecyclerViewAdapter
        activity_creator_profile_rv_hot_video.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        activity_creator_profile_rv_hot_video.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        // new post
        var profileNewDataList: ArrayList<ProfileHotVideoData> = ArrayList()

        profileNewDataList.add(ProfileHotVideoData(2, "대박대박 정호의 누네띄네 100개 먹방 영상", "https://www.naver.com", 1200000, "https://i.ytimg.com/vi/SzJo9QfhZg8/maxresdefault.jpg","2019-07-07 04:51","정호네식당"))
        profileNewDataList.add(ProfileHotVideoData(4, "대박대박 시연의 좋은데이 100개 먹방 영상", "https://www.naver.com", 1000000,"https://i.ytimg.com/vi/SzJo9QfhZg8/maxresdefault.jpg","2019-07-03 04:51","시연네식당"))
        profileNewDataList.add(ProfileHotVideoData(6, "대박대박 홍삼의 고구마간식 100개 먹방 영상", "https://www.naver.com", 120000,"https://i.ytimg.com/vi/SzJo9QfhZg8/maxresdefault.jpg","2019-07-07 12:51","홍삼네식당"))

        profileHotVideoRecyclerViewAdapter = ProfileHotVideoRecyclerViewAdapter(this, profileNewDataList)
        activity_creator_profile_rv_new_video.adapter = profileHotVideoRecyclerViewAdapter
        activity_creator_profile_rv_new_video.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        activity_creator_profile_rv_new_video.addItemDecoration(DividerItemDecoration(this!!, DividerItemDecoration.VERTICAL))

    }

}