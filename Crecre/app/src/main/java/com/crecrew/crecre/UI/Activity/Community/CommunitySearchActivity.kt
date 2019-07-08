package com.crecrew.crecre.UI.Activity.Community

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.crecrew.crecre.Data.CommunityHotPostData
import com.crecrew.crecre.Data.SearchResultData
import com.crecrew.crecre.R
import com.crecrew.crecre.UI.Adapter.CommunityHotPostRecyclerViewAdapter
import com.crecrew.crecre.UI.Adapter.CoummunitySearchRecyclerViewAdapter
import com.crecrew.crecre.utils.CustomRequestCompleteDialog
import com.crecrew.crecre.utils.SearchAlarmDialog
import kotlinx.android.synthetic.main.activity_community_hot_post.*
import kotlinx.android.synthetic.main.activity_community_search.*
import org.jetbrains.anko.keyguardManager
import org.jetbrains.anko.startActivity

class CommunitySearchActivity : AppCompatActivity() {

    lateinit var communitysearchRecyclerViewAdapter: CoummunitySearchRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community_search)

        rl_search_comm_act.setOnClickListener {
            downKeyboard(rl_search_comm_act)
        }

        //뒤로가기
        btn_back_community_search.setOnClickListener {
            finish()
        }

        //검색 버튼을 눌렀을 경우
        btn_find_search_com_act.setOnClickListener {

            /*
            //검색한 결과가 없다면 나오는 뷰
            //##추후 통신
            ll_no_resul_com_search_act.visibility = View.VISIBLE
            rl_after_search_view.visibility = View.GONE
            ll_first_search_view_com_act.visibility = View.GONE
            */

            //제대로 검색이 되었을 경우
            rl_after_search_view.visibility = View.VISIBLE
            ll_no_resul_com_search_act.visibility = View.GONE
            ll_first_search_view_com_act.visibility = View.GONE
        }

        //communityrequestactivity로 넘어갈 때
        btn_search_request_community_act.setOnClickListener {
            startActivity<CommunityRequestActivity>()
            finish()
        }

        btn1_search_request_community_act.setOnClickListener {
            startActivity<CommunityRequestActivity>()
            finish()
    }

        configureRecyclerView()
    }

    //recyclerView
    private fun configureRecyclerView() {
        var dataList: ArrayList<SearchResultData> = ArrayList()
        dataList.add(
            SearchResultData("햇님이", 0)
        )
        dataList.add(
            SearchResultData("햇님123123", 0)
        )
        dataList.add(
            SearchResultData("입짧은 햇님이와 친구엥ㅇ", 1)
        )
        dataList.add(
            SearchResultData("햇님", 0)
        )
        dataList.add(
            SearchResultData("햇님무이", 1)
        )

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


}
