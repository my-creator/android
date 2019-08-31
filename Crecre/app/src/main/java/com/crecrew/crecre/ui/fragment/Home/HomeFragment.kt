package scom.crecrew.crecre.UI.Fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
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
import android.widget.TextView
import com.crecrew.crecre.R
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import com.crecrew.crecre.base.BasePagerAdapter
import com.crecrew.crecre.db.SharedPreferenceController
import com.crecrew.crecre.data.*
import com.crecrew.crecre.network.ApplicationController
import com.crecrew.crecre.network.CommunityNetworkService
import com.crecrew.crecre.network.CreatorNetworkService
import com.crecrew.crecre.network.get.*
import com.crecrew.crecre.network.VoteNetworkService
import com.crecrew.crecre.ui.activity.Community.CommunityHotPostActivity
import com.crecrew.crecre.ui.activity.VoteSuggestActivity
import com.crecrew.crecre.ui.adapter.TodayPostRecyclerViewAdapter
import org.jetbrains.anko.support.v4.startActivity
import com.crecrew.crecre.ui.activity.CreatorSearchActivity
import com.crecrew.crecre.ui.fragment.Home.ClosedVoteFragment
import com.crecrew.crecre.ui.fragment.Home.RankRecyclerViewAdapter
import com.crecrew.crecre.ui.fragment.HomeTodayRankFragment
import com.crecrew.crecre.ui.fragment.vote.votePage.VotePageFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : Fragment() {

    private lateinit var rootView: View
    private var isChartOpen = false
    private var isInit = false

    lateinit var todayPostRecyclerViewAdapter: TodayPostRecyclerViewAdapter

    var todayCreatorRankData: ArrayList<CreatorData> = ArrayList()

    val creatorNetworkService: CreatorNetworkService by lazy {
        ApplicationController.instance.creatorNetworkService
    }
    val communityNetworkService: CommunityNetworkService by lazy {
        ApplicationController.instance.communityNetworkService
    }
    val voteNetworkService: VoteNetworkService by lazy {
        ApplicationController.instance.voteNetworkService
    }

    private lateinit var rank_time: TextView

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if(isInit) {
            var voteFragment = VotePageFragment.newInstance(true, 0)

            fragmentManager!!.beginTransaction().add(R.id.fragment_home_now_vote_rl, voteFragment).commit()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_home, container, false)

        rank_time = rootView.findViewById(R.id.fragment_home_today_rank_time) as TextView

        val handler = Handler()

        val handlerTask = object : Runnable {
            override fun run() {
                getCreatorTodayHotRank()

                var sdf = SimpleDateFormat("yyyy.MM.dd HH:mm:ss")
                var current_time = sdf.format(Date())

                rank_time.setText(current_time)


                handler.postDelayed(this, 1000 * 20)
            }
        }

        handler.post(handlerTask)

        getCommunityResponse()

        getLastVoteResponse()


        var voteFragment = VotePageFragment.newInstance(true, 0)

        fragmentManager!!.beginTransaction().add(R.id.fragment_home_now_vote_rl, voteFragment).commit()

        // 화면 전환
        rootView.run {

            fragment_home_rl_today_hot_btn_container.setOnClickListener() {
                openTodayHotChart()
            }

            fragment_home_now_vote_more.setOnClickListener {
                // TODO: 투표탭으로 화면전환
            }

            fragment_home_vote_recommendation_btn.setOnClickListener {
                startActivity<VoteSuggestActivity>()
            }
            fragment_home_txt_today_hot_post_more.setOnClickListener {
                val intent = Intent(activity, CommunityHotPostActivity::class.java)
                intent.putExtra("flag", 1)
                intent.putExtra("title", "인기글")
                startActivity(intent)

            }
            fragment_home_txt_today_new_post_more.setOnClickListener {
                val intent = Intent(activity, CommunityHotPostActivity::class.java)
                intent.putExtra("flag", 0)
                intent.putExtra("title", "최신글")
                startActivity(intent)
            }

            // search bar 누르면 검색 화면으로 넘어가기
            fragment_home_txt_search.setOnClickListener {
                val intent = Intent(activity, CreatorSearchActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                startActivity(intent)

            }

            fragment_home_now_vote_more.setOnClickListener {

                //activity.supportFragmentManager(fragmentManager)

            }

            // 다른 화면 누르면 키보드 내리고 edittext focus 삭제
            fragment_home_container.setOnClickListener {
                downKeyboard(fragment_home_container)
            }
        }
        isInit = true
        return rootView
    }


    // 실시간 크리에이터 차트 열고 닫기
    private fun openTodayHotChart() {
        if (isChartOpen == false) {
            fragment_home_today_rank_container.visibility = VISIBLE
            fragment_home_iv_today_hot_btn.setImageResource(R.drawable.icn_hide_off)
            fragment_home_today_hot_creator_container.visibility = GONE
            isChartOpen = true
        } else {
            fragment_home_today_rank_container.visibility = GONE
            fragment_home_iv_today_hot_btn.setImageResource(R.drawable.icn_hide_on)
            fragment_home_today_hot_creator_container.visibility = VISIBLE
            isChartOpen = false
        }
    }


    fun getLastVoteResponse() {
        val getLastVoteHome = voteNetworkService.getLastVoteHome(SharedPreferenceController.getUserToken(activity!!))
        getLastVoteHome.enqueue(object : Callback<GetLastVoteHomeResponse> {
            override fun onFailure(call: Call<GetLastVoteHomeResponse>, t: Throwable) {
                Log.e("last vote fail", t.toString())
            }

            override fun onResponse(call: Call<GetLastVoteHomeResponse>, response: Response<GetLastVoteHomeResponse>) {
                if (response.isSuccessful) {
                    Log.e("status", response.body()!!.status.toString())
                    if (response.body()!!.status == 200) {
                        var tmp: ArrayList<LastVoteHomeData> = response.body()!!.data
                        Log.e("data", tmp[0].title)

                        frag_home_vp_clsd.run {
                            adapter = BasePagerAdapter(fragmentManager!!).apply {
                                for (i in tmp.indices)
                                    addFragment(ClosedVoteFragment.newInstance(tmp[i]))
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

        for (i in 0..todayCreatorRankData.size - 1) {
            val item =
                CurrentRankData((todayCreatorRankData[i].ranking).toString(), todayCreatorRankData[i].creatorName)
            items.add(item)
        }
        val adapter = RankRecyclerViewAdapter(items)
        vScrollLayout.setAdapter(adapter)
    }


    // creator ranking networking
    private fun getCreatorTodayHotRank() {

        val getCreatorTodayHotRank = creatorNetworkService.getCreatorTodayHotRank()
        getCreatorTodayHotRank.enqueue(object : Callback<GetCreatorTodayHotRank> {
            override fun onFailure(call: Call<GetCreatorTodayHotRank>, t: Throwable) {
                Log.e("creator search fail", t.toString())
            }

            override fun onResponse(call: Call<GetCreatorTodayHotRank>, response: Response<GetCreatorTodayHotRank>) {

                if (response.isSuccessful) {

                    if (response.body()!!.status == 200) {
                        todayCreatorRankData = response.body()!!.data
                        var todayCreatorRankTopData: ArrayList<CreatorData> = ArrayList(5)
                        var todayCreatorRankBottomData: ArrayList<CreatorData> = ArrayList(5)

                        var index = 0
                        var isEmpty = false
                        for (i in 0..4) {
                            if(todayCreatorRankData[index].searchCnt != 0)
                                todayCreatorRankTopData.add(todayCreatorRankData[index++])
                            else {
                                isEmpty = true
                                break
                            }
                        }
                        if(!isEmpty) {
                            for (i in 0..4) {
                                if (todayCreatorRankData[index].searchCnt != 0)
                                    todayCreatorRankBottomData.add(todayCreatorRankData[index++])
                                else
                                    break
                            }
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
                                    LayoutInflater.from(activity!!)
                                        .inflate(R.layout.fragment_home_today_rank_navi, null, false)
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
    fun getCommunityResponse() {

        // TODO: 가희한테 말해서 getCommunitySmallPosts로 이름 바꾸기 (new나 hot이 들어가지 않도록)
        val getTodayHotPost : Call<GetTodayPostResponse> = communityNetworkService.getTodayHotPost()
        getTodayHotPost.enqueue(object : Callback<GetTodayPostResponse> {

            override fun onFailure(call: Call<GetTodayPostResponse>, t: Throwable) {
                Log.e("hot post list fail", t.toString())
            }

            override fun onResponse(call: Call<GetTodayPostResponse>, response: Response<GetTodayPostResponse>) {
                if (response.isSuccessful) {
                    if(response.body()!!.status == 200) {
                        val tmp: ArrayList<TodayPost> = response.body()!!.data
                        if (tmp.size > 0) {
                            var todayDataList : ArrayList<TodayPost> = ArrayList(3)

                            // max size 이런걸로 설정할 수 있나?
                            var size = tmp.size-1
                            if(size > 2)
                                size = 2

                            for (i in 0..size) {
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
                        } else
                            fragment_home_rl_hot_post_null.visibility = VISIBLE
                    }
                }

            }

        })

        val getTodayNewPost : Call<GetTodayPostResponse> = communityNetworkService.getTodayNewPost()
        getTodayNewPost.enqueue(object : Callback<GetTodayPostResponse> {
            override fun onFailure(call: Call<GetTodayPostResponse>, t: Throwable) {
                Log.e("new post list fail", t.toString())
            }

            override fun onResponse(call: Call<GetTodayPostResponse>, response: Response<GetTodayPostResponse>) {
                if (response.isSuccessful) {
                    if(response.body()!!.status == 200) {
                        val tmp: ArrayList<TodayPost> = response.body()!!.data
                        if (tmp.size > 0) {
                            var todayDataList : ArrayList<TodayPost> = ArrayList(3)

                            var size = tmp.size-1
                            if(size > 2)
                                size = 2

                            for (i in 0..size) {
                                todayDataList.add(tmp[i])
                            }

                            todayPostRecyclerViewAdapter = TodayPostRecyclerViewAdapter(activity!!, todayDataList)
                            fragment_home_rv_today_new_post.adapter = todayPostRecyclerViewAdapter
                            fragment_home_rv_today_new_post.layoutManager =
                                LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                            fragment_home_rv_today_new_post.addItemDecoration(
                                DividerItemDecoration(
                                    context!!,
                                    DividerItemDecoration.VERTICAL
                                )
                            )
                        }
                        else
                            fragment_home_rl_new_post_null.visibility = VISIBLE
                    }
                }

            }
        })
    }
}