package com.crecrew.crecre.UI.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.crecrew.crecre.Data.CommunityFavoriteData
import com.crecrew.crecre.R
import com.crecrew.crecre.UI.Activity.CommunitySearchActivity
import com.crecrew.crecre.UI.Adapter.CommunityFavoriteRecyclerViewAdapter
import com.crecrew.crecre.UI.Adapter.CommunityPostFragmentAdapter
import kotlinx.android.synthetic.main.fragment_community.*
import kotlinx.android.synthetic.main.fragment_community.view.*
import kotlinx.android.synthetic.main.rv_item_favorite_community_frag.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.startActivity

class CommunityFragment: Fragment() {

    private lateinit var rootView: View
    //private val communityfavoriteDataList = ArrayList<CommunityFavoriteData>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_community, container, false)

        rootView.btn_search_community_frag.setOnClickListener() {
            startActivity<CommunitySearchActivity>()
        }

        return rootView

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        configureMainTab()

//        tablayoutTextColor()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView()
    }

    //RecyclerView
    private fun setRecyclerView() {

        var dataList: ArrayList<CommunityFavoriteData> = ArrayList()
        dataList.add(CommunityFavoriteData("자유게시판", 0,1))
        dataList.add(CommunityFavoriteData("온도Ondo", 1,1))
        dataList.add(CommunityFavoriteData("입짧은 햇님", 1,1))
        dataList.add(CommunityFavoriteData("입짧은 햇미니인", 1,0))
        dataList.add(CommunityFavoriteData("바보바보", 1,0))

        //즐겨찾기 rv
        val communityfavoriteRecyclerViewAdapter = CommunityFavoriteRecyclerViewAdapter(activity!!, dataList)
        rv_favorite_community_frag.adapter = communityfavoriteRecyclerViewAdapter
        rv_favorite_community_frag.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        //일반 게시글 rv
        val communityPostListRecyclerViewAdapter = CommunityFavoriteRecyclerViewAdapter(activity!!, dataList)
        rv_postlist_community_fg.adapter = communityPostListRecyclerViewAdapter
        rv_postlist_community_fg.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
    }

    //ViewPager
    private fun configureMainTab() {

        vp_community_post_frag.adapter = CommunityPostFragmentAdapter(fragmentManager!!, 2)
        vp_community_post_frag.offscreenPageLimit = 1

   /*     tl_community_post_frag.run {
            setupWithViewPager(vp_community_post_frag)

            getTabAt(0)!!.setText("최신글")
            getTabAt(1)!!.setText("인기글")
        }*/

        tl_community_post_frag.setupWithViewPager(vp_community_post_frag)
        val topTabLayout: View = activity!!.layoutInflater.inflate(R.layout.fragment_community_navi, null, false)
        tl_community_post_frag.getTabAt(0)!!.customView = topTabLayout.
            findViewById(R.id.rl_recentpost_community_navi_frag) as RelativeLayout
        tl_community_post_frag.getTabAt(1)!!.customView = topTabLayout.
            findViewById(R.id.rl_popularpost_community_navi_frag) as RelativeLayout

    }

/*
    // 처음 프래그먼트 추가
    private fun addFragment(fragment : Fragment){
        val fm = childFragmentManager
        val transaction = fm.beginTransaction()
        val myIntroFragment = MypageIntroFragment()
        val bundle = Bundle()

        transaction.add(R.id.mypage_content_layout, myIntroFragment)
        transaction.commit()
    }

    // 프래그먼트 교체
    private fun replaceFragment(fragment: Fragment, checkFlag : Int) {
        val fm = childFragmentManager
        val transaction = fm.beginTransaction()

        if(checkFlag == 0){
            val myIntroFragment = MypageIntroFragment()
            val bundle = Bundle()
            bundle.putString("name", name)
            bundle.putString("job", job)
            bundle.putString("company", company)
            bundle.putString("image", image)
            bundle.putString("field", field)
            bundle.putInt("my_or_other_flag", my_or_other_flag)
            bundle.putInt("userID", userID)
            Log.v("asdf", "보내는필드 = " + field)
            bundle.putString("status", status)
            bundle.putInt("coworkingEnabled", coworkingEnabled)
            myIntroFragment.setArguments(bundle)
            transaction.replace(R.id.mypage_content_layout, myIntroFragment)
            transaction.commit()
        }
        else{
            val myactFragment = MypageActFragment()
            val bundle = Bundle()
            bundle.putString("name", name)
            bundle.putInt("my_or_other_flag", my_or_other_flag)
            bundle.putInt("userID", userID)
            myactFragment.setArguments(bundle)
            transaction.replace(R.id.mypage_content_layout, myactFragment)
            transaction.commit()
        }


    }

*/


}