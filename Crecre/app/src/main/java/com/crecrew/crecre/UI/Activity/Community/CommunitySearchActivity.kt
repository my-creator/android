package com.crecrew.crecre.UI.Activity.Community

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.crecrew.crecre.Network.ApplicationController
import com.crecrew.crecre.Network.Get.CommunityBoardData
import com.crecrew.crecre.Network.Get.GetCommunityUnlikeBoardsResponse
import com.crecrew.crecre.Network.CommunityNetworkService
import com.crecrew.crecre.R
import com.crecrew.crecre.UI.Adapter.CoummunitySearchRecyclerViewAdapter
import com.crecrew.crecre.utils.SearchAlarmDialog
import kotlinx.android.synthetic.main.activity_community_search.*
import org.jetbrains.anko.startActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommunitySearchActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var communitysearchRecyclerViewAdapter: CoummunitySearchRecyclerViewAdapter
    var board_flag = -1
    var search_tv : String = ""

    val communityNetworkService: CommunityNetworkService by lazy {
        ApplicationController.instance.communityNetworkService
    }

    val requestEnterDialog : SearchAlarmDialog by lazy {
        SearchAlarmDialog(this@CommunitySearchActivity, "알림","검색어가 너무 짧습니다.",
            completefailConfirmListener,"확인")
    }

    //click
    override fun onClick(v: View?) {
        when (v!!) {

            //키보드 다운
            rl_search_comm_act -> {
                downKeyboard(rl_search_comm_act)
            }

            //뒤로가기
            btn_back_community_search -> {
                finish()
            }

            //검색 버튼을 눌렀을 경우
            btn_find_search_com_act -> {

                search_tv = et_searchword_search_act.text.toString()

                if(search_tv == "")
                {
                    requestEnterDialog.show()
                }
                else
                {
                    //제대로 검색이 되었을 경우
                    getCommunitySearchResultResponse()
                    rl_after_search_view.visibility = View.VISIBLE
                    ll_no_resul_com_search_act.visibility = View.GONE
                    ll_first_search_view_com_act.visibility = View.GONE
                }
                /*
                //검색한 결과가 없다면 나오는 뷰
                //##추후 통신
                ll_no_resul_com_search_act.visibility = View.VISIBLE
                rl_after_search_view.visibility = View.GONE
                ll_first_search_view_com_act.visibility = View.GONE
                */

            }

            //communityrequestactivity로 넘어갈 때
            btn_search_request_community_act -> {
                startActivity<CommunityRequestActivity>()
                finish()
            }

            btn1_search_request_community_act -> {
                startActivity<CommunityRequestActivity>()
                finish()
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community_search)

        init()
        configureRecyclerView()
        configureEditText()

    }

    private fun init() {
        rl_search_comm_act.setOnClickListener(this)
        btn_back_community_search.setOnClickListener(this)
        btn_find_search_com_act.setOnClickListener(this)
        btn_search_request_community_act.setOnClickListener(this)
        btn_search_request_community_act.setOnClickListener(this)
        btn1_search_request_community_act.setOnClickListener(this)

    }

    //recyclerView
    private fun configureRecyclerView() {
        var dataList: ArrayList<CommunityBoardData> = ArrayList()

        communitysearchRecyclerViewAdapter = CoummunitySearchRecyclerViewAdapter(this, dataList)
        rv_search_result_com_sear_act.adapter = communitysearchRecyclerViewAdapter
        rv_search_result_com_sear_act.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    //키보드 다운
    private fun downKeyboard(view: View) {
        val imm: InputMethodManager =
            applicationContext!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    //editText hint의 값 설정
    private fun configureEditText() {
        //게시글인지 작성글인지
        board_flag = intent.getIntExtra("board_flag", -1)

        //게시판검색
        if (board_flag == 0) {
            et_searchword_search_act.setHint("게시판 검색")

        } else
            et_searchword_search_act.setHint("글 제목,내용 검색")
    }

    //검색버튼 누른 후 통신
    private fun getCommunitySearchResultResponse() {
        val getCommunitySmallNewPosts: Call<GetCommunityUnlikeBoardsResponse> = communityNetworkService.getBoardsSearch("",search_tv)

        getCommunitySmallNewPosts.enqueue(object : Callback<GetCommunityUnlikeBoardsResponse> {

            override fun onFailure(call: Call<GetCommunityUnlikeBoardsResponse>, t: Throwable) {
                Log.e("검색 list fail", t.toString())
            }

            override fun onResponse(
                call: Call<GetCommunityUnlikeBoardsResponse>,
                response: Response<GetCommunityUnlikeBoardsResponse>
            ) {

                if (response.isSuccessful) {
                    val temp: ArrayList<CommunityBoardData> = response.body()!!.data
                    if (temp.size > 0) {

                        val position = communitysearchRecyclerViewAdapter.itemCount
                        communitysearchRecyclerViewAdapter.dataList.addAll(temp)
                        communitysearchRecyclerViewAdapter.notifyItemInserted(position)
                    }
                    else
                    {
                    }
                }
            }
        })
    }

    private val completefailConfirmListener = View.OnClickListener {
        requestEnterDialog!!.dismiss()

    }
}
