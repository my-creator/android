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

    lateinit var communitysearchRecyclerViewAdapter : CoummunitySearchRecyclerViewAdapter

    val requestDialog :  SearchAlarmDialog by lazy {
        SearchAlarmDialog(this@CommunitySearchActivity, "알림","검색어가 너무 짧습니다.",
            completeConfirmListener,"확인")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community_search)

        rl_search_comm_act.setOnClickListener {
            downKeyboard(rl_search_comm_act)
        }

        btn_back_community_search.setOnClickListener {
            finish()
        }

        btn_find_search_com_act.setOnClickListener {

            //검색한 글자가 1글자면 경고 다이얼로그
            if(et_searchword_search_act.text.length == 0 || et_searchword_search_act.text.length == 1)
            {
                requestDialog.show()
            }
            else{
                //제대로 검색이 되었을 경우
                rl_after_search_view.visibility = View.VISIBLE
                ll_first_search_view_com_act.visibility = View.GONE
            }
        }

        btn_search_request_community_act.setOnClickListener {
            startActivity<CommunityRequestActivity>()
        }

        configureRecyclerView()
    }

    //recyclerView
    private fun configureRecyclerView() {
        var dataList: ArrayList<SearchResultData> = ArrayList()
        dataList.add(
            SearchResultData("햇님이",0)
        )
        dataList.add(
            SearchResultData("햇님123123",0)
        )
        dataList.add(
            SearchResultData("입짧은 햇님이와 친구엥ㅇ",1)
        )
        dataList.add(
            SearchResultData("햇님",0)
        )
        dataList.add(
            SearchResultData("햇님무이",1)
        )

        communitysearchRecyclerViewAdapter = CoummunitySearchRecyclerViewAdapter(this, dataList)
        rv_search_result_com_sear_act.adapter = communitysearchRecyclerViewAdapter
        rv_search_result_com_sear_act.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    }

    //다이얼로그 확인 버튼
    private val completeConfirmListener = View.OnClickListener {
        requestDialog!!.dismiss()
        //startActivity<CommunityFragment>()
    }

    //키보드 다운
    private fun downKeyboard(view: View) {
        val imm: InputMethodManager =
            applicationContext!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }


}
