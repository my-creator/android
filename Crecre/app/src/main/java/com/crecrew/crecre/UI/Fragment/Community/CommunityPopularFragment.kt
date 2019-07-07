package com.crecrew.crecre.UI.Fragment.Community

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.crecrew.crecre.Data.CommunityHotPostData

import com.crecrew.crecre.R
import com.crecrew.crecre.UI.Activity.Community.CommunityHotPostActivity
import com.crecrew.crecre.UI.Adapter.CommunityHotPostRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_community_popular.*
import kotlinx.android.synthetic.main.fragment_community_popular.view.*
import org.jetbrains.anko.support.v4.startActivity

class CommunityPopularFragment : Fragment() {

    lateinit var communityPopularRecyclerViewAdapter: CommunityHotPostRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView =  inflater.inflate(R.layout.fragment_community_popular, container, false)

        rootView.btn_more_community_popular_fg.setOnClickListener {
            startActivity<CommunityHotPostActivity>()
        }
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView()
    }

    //인기글 RecyclerView
    private fun setRecyclerView() {

        var dataList: ArrayList<CommunityHotPostData> = ArrayList()
        dataList.add(CommunityHotPostData("https://t1.daumcdn.net/cfile/tistory/9903764A5B5D46D621",1,
            "햇님이 먹었던 과자 브랜드 이게 맞나?", 10, 10, "18:47","인기게시판",0,0))
        dataList.add(CommunityHotPostData("", 1,"안녀안알ㄴ여ㅏㄴ여낭",
            1, 8, "14:27","입짧은 햇님",0,0))
        dataList.add(CommunityHotPostData("",1,
            "입짧은햇님보세여", 19, 19, "12:35","온도",0,0))
        dataList.add(CommunityHotPostData("https://dispatch.cdnser.be/wp-content/uploads/2018/01/1f0678395e9cb7d356680889415c5b2c.png",0,
            "먹방요정", 4, 8, "11:20","인기게시판",0,0))
        dataList.add(CommunityHotPostData("https://pbs.twimg.com/profile_images/520547323894456320/e028GhkV_400x400.jpeg",0,
            "바부야", 2, 7, "04:30","감성",0,0))


        communityPopularRecyclerViewAdapter = CommunityHotPostRecyclerViewAdapter(context!!, dataList)
        rv_popular_community_fg.adapter = communityPopularRecyclerViewAdapter
        rv_popular_community_fg.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

    }

}
