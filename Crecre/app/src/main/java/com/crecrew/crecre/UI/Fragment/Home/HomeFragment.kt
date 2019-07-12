package scom.crecrew.crecre.UI.Fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
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
import com.crecrew.crecre.DB.SharedPreferenceController
import com.crecrew.crecre.Data.*
import com.crecrew.crecre.Network.ApplicationController
import com.crecrew.crecre.Network.CommunityNetworkService
import com.crecrew.crecre.Network.CreatorNetworkService
import com.crecrew.crecre.Network.Get.GetCommunitySmallNewPostResponse
import com.crecrew.crecre.Network.Get.GetCreatorTodayHotRank
import com.crecrew.crecre.Network.Get.GetVoteEndResponse
import com.crecrew.crecre.Network.VoteNetworkService
import com.crecrew.crecre.UI.Activity.Community.CommunityHotPostActivity
import com.crecrew.crecre.UI.Activity.VoteSuggestActivity
import com.crecrew.crecre.UI.Adapter.TodayPostRecyclerViewAdapter
import org.jetbrains.anko.support.v4.startActivity
import com.crecrew.crecre.UI.Activity.CreatorSearchActivity
import com.crecrew.crecre.UI.Fragment.Home.ClosedVoteFragment
import com.crecrew.crecre.UI.Fragment.Home.RankRecyclerViewAdapter
import com.crecrew.crecre.UI.Fragment.HomeTodayRankFragment
import com.crecrew.crecre.UI.Fragment.VoteCurrentFragment
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
    val voteNetworkService: VoteNetworkService by lazy{
        ApplicationController.instance.voteNetworkService
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_home, container, false)


        Log.e("token", SharedPreferenceController.getUserToken(activity!!))

        // 통신
        getCreatorTodayHotRank()
        getCommunityResponse()
        //getLastVoteResponse()

        var voteCurrentFragment = VoteCurrentFragment()
        voteCurrentFragment.flag = 1

        fragmentManager!!.beginTransaction().add(R.id.fragment_home_now_vote_rl,voteCurrentFragment).commit()

        // 화면 전환
        rootView.run {

            fragment_home_iv_today_hot_btn.setOnClickListener() {
                openTodayHotChart()
            }

            fragment_home_now_vote_more.setOnClickListener{
                // TODO: 투표탭으로 화면전환
            }

            fragment_home_vote_recommendation_btn.setOnClickListener {
                startActivity<VoteSuggestActivity>()
            }
            fragment_home_txt_today_hot_post_more.setOnClickListener {
                val intent = Intent(activity, CommunityHotPostActivity::class.java)
                intent.putExtra("flag",1)
                intent.putExtra("title","인기글")
                startActivity(intent)

            }
            fragment_home_txt_today_new_post_more.setOnClickListener {
                val intent = Intent(activity, CommunityHotPostActivity::class.java)
                intent.putExtra("flag",0)
                intent.putExtra("title","최신글")
                startActivity(intent)
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


    fun getLastVoteResponse(){
        val getLastVote = voteNetworkService.getLastVote()
        getLastVote.enqueue(object: Callback<GetVoteEndResponse> {
            override fun onFailure(call: Call<GetVoteEndResponse>, t: Throwable) {
                Log.e("last vote fail" , t.toString())
            }

            override fun onResponse(call: Call<GetVoteEndResponse>, response: Response<GetVoteEndResponse>) {
                if(response.isSuccessful) {
                    if (response.body()!!.status == 200) {
                        var tmp: ArrayList<GetVoteEndData> = response.body()!!.data
                        var dataList: ArrayList<LastVoteData> = ArrayList()

                        // data 넣기
                        for (i in 0..2) {
                            dataList[i].image = tmp[0].thumbnail_url
                            dataList[i].content = tmp[0].title
                            for (j in 0..tmp[i].choices.size - 1) {
                                if (tmp[i].choices[j].rank == 1) {
                                    dataList[i].creator = tmp[i].choices[j].name
                                    dataList[i].profile = tmp[i].choices[j].creator_profile_url
                                }
                            }
                        }
                        frag_home_vp_clsd.run {
                            adapter = BasePagerAdapter(fragmentManager!!).apply {
                                for (i in dataList.indices)
                                    addFragment(ClosedVoteFragment.newInstance(dataList[i]))
                            }
                        }
                    }
                }
            }
        })
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

        for (i in 0..todayCreatorRankData.size-1) {
            val item = CurrentRankData((todayCreatorRankData[i].ranking).toString(), todayCreatorRankData[i].creatorName)
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

                Log.e("home creator rank",response.body()!!.status.toString())
                if(response.isSuccessful){
                    Log.e("home creator rank",response.body()!!.status.toString())
                    //Log.e("home creator rank",response.body()!!.data[0].creatorName)

                    if(response.body()!!.status == 200){
                        todayCreatorRankData = response.body()!!.data
                        var todayCreatorRankTopData : ArrayList<CreatorData> = ArrayList(5)
                        var todayCreatorRankBottomData: ArrayList<CreatorData> = ArrayList(5)

                        var index = 0
                        for(i in 0 ..4)
                            todayCreatorRankTopData.add(todayCreatorRankData[index++])
                        for(i in 0 ..4) {
                            todayCreatorRankBottomData.add(todayCreatorRankData[index++])
                            Log.e("hi",todayCreatorRankBottomData[i].creatorName)
                        }
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

                        initVScrollLayout()
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
