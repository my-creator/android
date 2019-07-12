package scom.crecrew.crecre.UI.Fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.inputmethod.InputMethodManager
import android.widget.RelativeLayout
import com.crecrew.crecre.R
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import com.crecrew.crecre.Base.BasePagerAdapter
import com.crecrew.crecre.Data.*
import com.crecrew.crecre.Network.ApplicationController
import com.crecrew.crecre.Network.CommunityNetworkService
import com.crecrew.crecre.Network.CreatorNetworkService
import com.crecrew.crecre.Network.Get.GetCommunitySmallNewPostResponse
import com.crecrew.crecre.Network.Get.GetCreatorTodayHotRank
import com.crecrew.crecre.UI.Activity.Community.CommunityHotPostActivity
import com.crecrew.crecre.UI.Activity.VoteSuggestActivity
import com.crecrew.crecre.UI.Adapter.TodayPostRecyclerViewAdapter
import org.jetbrains.anko.support.v4.startActivity
import com.crecrew.crecre.UI.Activity.CreatorSearchActivity
import com.crecrew.crecre.UI.Fragment.Home.ClosedVoteFragment
import com.crecrew.crecre.UI.Fragment.Home.RankRecyclerViewAdapter
import com.crecrew.crecre.UI.Fragment.HomeTodayRankFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList


class HomeFragment: Fragment() {

    private lateinit var rootView: View
    private var isChartOpen = false

    lateinit var todayPostRecyclerViewAdapter: TodayPostRecyclerViewAdapter


    var todayCreatorRankData : ArrayList<CreatorData> = ArrayList()

    val creatorNetworkService: CreatorNetworkService by lazy{
        ApplicationController.instance.creatorNetworkService
    }
    val communityNetworkService: CommunityNetworkService by lazy{
        ApplicationController.instance.communityNetworkService
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_home, container, false)

        // 통신
        //getCreatorTodayHotRank()
        getCommunityResponse()

        // 화면 전환
        rootView.run {

            fragment_home_iv_today_hot_btn.setOnClickListener() {
                openTodayHotChart()
            }

            fragment_home_vote_recommendation_btn.setOnClickListener {
                startActivity<VoteSuggestActivity>()
            }
            fragment_home_txt_today_hot_post_more.setOnClickListener {
                Log.e("click","click more btn")
                startActivity<CommunityHotPostActivity>()
                // TODO: hot과 new 구분하기!
            }
            fragment_home_txt_today_new_post_more.setOnClickListener {
                startActivity<CommunityHotPostActivity>()
            }

            // search bar 누르면 검색 화면으로 넘어가기
            fragment_home_txt_search.setOnClickListener {
                val intent = Intent(activity, CreatorSearchActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                startActivity(intent)

            }

            // 다른 화면 누르면 키보드 내리고 edittext focus 삭제
            fragment_home_container.setOnClickListener {
                downKeyboard(fragment_home_container)
            }
        }

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureRecyclerView()
        initVScrollLayout()
    }

    override fun onResume() {
        super.onResume()

        /*
        fragment_home_edit_search.setText(null)
        fragment_home_edit_search.clearFocus();
*/
    }

    // 실시간 크리에이터 차트 열고 닫기
    private fun openTodayHotChart(){
        if(isChartOpen == false) {
            fragment_home_today_rank_container.visibility = VISIBLE
            fragment_home_iv_today_hot_btn.setImageResource(R.drawable.icn_hide_off)
            fragment_home_today_hot_creator_container.visibility = GONE
            isChartOpen = true
        }
        else {
            fragment_home_today_rank_container.visibility = GONE
            fragment_home_iv_today_hot_btn.setImageResource(R.drawable.icn_hide_on)
            fragment_home_today_hot_creator_container.visibility = VISIBLE
            isChartOpen = false
        }
    }

    private fun configureRecyclerView(){

        // last vote
        var lastVoteData: ArrayList<LastVoteData> = ArrayList()

        lastVoteData.add(LastVoteData("https://img.sbs.co.kr/newimg/news/20170907/201091232_1280.jpg","https://news.imaeil.com/inc/photos/2019/04/29/2019042900310562698_l.jpg", "시연조교",1,"크리크리 짱, 2줄 넘어갔을 때 처리도 해야 함 !! 홍루이젠 맛있다."))
        lastVoteData.add(LastVoteData("https://s-i.huffpost.com/gen/1771947/images/n-DEFAULT-628x314.jpg","https://mblogthumb-phinf.pstatic.net/MjAxODA1MTlfOSAg/MDAxNTI2NzQwNjY5OTUx.VcucGKX52noaAETS5acZgeovzLRSCWs8AkzGJVJUuasg.PIDUYkcbI_IaBRJ25-Lgu4-pnrDdVuP8uWK4ZRQbxl8g.JPEG.okyunju0309/PicsArt_05-19-01.19.40.jpg?type=w800", "가희바희보",1,"하나빼기 일 >___<"))
        lastVoteData.add(LastVoteData("https://www.sanghafarm.co.kr/sanghafarm_Data/upload/shop/product/201803/A0000101_2018032109513585717.jpg","", "현희여신",1,"오늘은 잼을 가져오셨다."))

        frag_home_vp_clsd.run {
            adapter = BasePagerAdapter(fragmentManager!!).apply {
                for (i in lastVoteData.indices)
                    addFragment(ClosedVoteFragment.newInstance(lastVoteData[i]))
            }
        }



    }

    private fun downKeyboard(view: View) {

        val imm: InputMethodManager =
            activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)

        // fragment_home_edit_search.clearFocus()
    }

    private fun initVScrollLayout() {
        val vScrollLayout = rootView.fragment_home_txt_today_hot_creator
        val items = ArrayList<CurrentRankData>()

        // TODO: todayCreatorRankData 넣기!

        for (i in 1..10) {
            val item = CurrentRankData("$i", "항목$i")
            items.add(item)
        }
        val adapter = RankRecyclerViewAdapter(items)
        vScrollLayout.setAdapter(adapter)
    }



    // creator ranking networking
    private fun getCreatorTodayHotRank(){

        val getCreatorTodayHotRank = creatorNetworkService.getCreatorTodayHotRank()
        getCreatorTodayHotRank.enqueue(object: Callback<GetCreatorTodayHotRank> {
            override fun onFailure(call: Call<GetCreatorTodayHotRank>, t: Throwable) {
                Log.e("creator search fail",t.toString())
            }
            override fun onResponse(call: Call<GetCreatorTodayHotRank>, response: Response<GetCreatorTodayHotRank>) {
                if(response.isSuccessful){
                    if(response.body()!!.status == 200){
                        todayCreatorRankData = response.body()!!.data

                        var todayCreatorRankTopData : ArrayList<CreatorData> = ArrayList(5)
                        var todayCreatorRankBottomData: ArrayList<CreatorData> = ArrayList(5)

                        var index = 0
                        for(i in 0 ..4)
                            todayCreatorRankTopData.add(todayCreatorRankData[index++])
                        for(i in 0 ..2)
                            todayCreatorRankBottomData.add(todayCreatorRankData[index++])

                        rootView.fragment_home_vp_today_rank.run {
                            adapter = BasePagerAdapter(fragmentManager!!).apply {

                                var homeTodayRankTopFragment: HomeTodayRankFragment = HomeTodayRankFragment()
                                homeTodayRankTopFragment.setList(todayCreatorRankTopData)

                                var homeTodayRankBottomFragment: HomeTodayRankFragment = HomeTodayRankFragment()
                                homeTodayRankBottomFragment.setList(todayCreatorRankBottomData)

                                addFragment(homeTodayRankTopFragment)
                                addFragment(homeTodayRankBottomFragment)
                            }
                        }

                        rootView.let {

                            // tab설정
                            it.fragment_home_tl_today_rank.run {
                                val navigationLayout: View =
                                    LayoutInflater.from(activity!!).inflate(R.layout.fragment_home_today_rank_navi, null, false)
                                setupWithViewPager(it.fragment_home_vp_today_rank)
                                getTabAt(0)!!.customView =
                                    navigationLayout.findViewById(R.id.fragment_home_today_rank_navi_top) as RelativeLayout
                                getTabAt(1)!!.customView =
                                    navigationLayout.findViewById(R.id.fragment_home_today_rank_navi_bottom) as RelativeLayout
                            }
                        }
                    }
                }
            }
        })
    }

    // post networking
    private fun getCommunityResponse() {

        // TODO: 가희한테 말해서 getCommunitySmallPosts로 이름 바꾸기 (new나 hot이 들어가지 않도록)
        val getCommunitySmallHotPosts : Call<GetCommunitySmallNewPostResponse> = communityNetworkService.getCommunitySmallHotPosts()
        getCommunitySmallHotPosts.enqueue(object : Callback<GetCommunitySmallNewPostResponse> {

            override fun onFailure(call: Call<GetCommunitySmallNewPostResponse>, t: Throwable) {
                Log.e("hot post list fail", t.toString())
            }

            override fun onResponse(call: Call<GetCommunitySmallNewPostResponse>, response: Response<GetCommunitySmallNewPostResponse>) {
                if (response.isSuccessful) {
                    if(response.body()!!.status == 200) {
                        val tmp: ArrayList<CommunitySmallNewGetData> = response.body()!!.data
                        if (tmp.size > 0) {
                            var todayDataList : ArrayList<CommunitySmallNewGetData> = ArrayList(3)

                            for(i in 0..2) {
                                todayDataList.add(tmp[i])
                            }
                            todayPostRecyclerViewAdapter = TodayPostRecyclerViewAdapter(activity!!, todayDataList)
                            fragment_home_rv_today_hot_post.adapter = todayPostRecyclerViewAdapter
                            fragment_home_rv_today_hot_post.layoutManager =
                                LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                            fragment_home_rv_today_hot_post.addItemDecoration(
                                DividerItemDecoration(
                                    context!!,
                                    DividerItemDecoration.VERTICAL
                                )
                            )
                        }
                    }
                }

            }

        })

        val getCommunitySmallNewPosts : Call<GetCommunitySmallNewPostResponse> = communityNetworkService.getCommunitySmallNewPosts()
        getCommunitySmallNewPosts.enqueue(object : Callback<GetCommunitySmallNewPostResponse> {

            override fun onFailure(call: Call<GetCommunitySmallNewPostResponse>, t: Throwable) {
                Log.e("new post list fail", t.toString())
            }

            override fun onResponse(call: Call<GetCommunitySmallNewPostResponse>, response: Response<GetCommunitySmallNewPostResponse>) {
                if (response.isSuccessful) {
                    if(response.body()!!.status == 200) {
                        val tmp: ArrayList<CommunitySmallNewGetData> = response.body()!!.data
                        if (tmp.size > 0) {
                            var todayDataList : ArrayList<CommunitySmallNewGetData> = ArrayList(3)

                            for(i in 0..2) {
                                todayDataList.add(tmp[i])
                            }

                            todayPostRecyclerViewAdapter = TodayPostRecyclerViewAdapter(activity!!, todayDataList)
                            fragment_home_rv_today_new_post.adapter = todayPostRecyclerViewAdapter
                            fragment_home_rv_today_new_post.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                            fragment_home_rv_today_new_post.addItemDecoration(DividerItemDecoration(context!!, DividerItemDecoration.VERTICAL))
                        }
                    }
                }

            }
        })
    }


}
