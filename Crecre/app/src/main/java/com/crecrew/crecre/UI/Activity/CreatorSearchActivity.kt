package com.crecrew.crecre.UI.Activity

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.util.Log
import android.util.Size
import android.view.View.VISIBLE
import android.view.inputmethod.InputMethodManager
import com.crecrew.crecre.Data.RankData
import com.crecrew.crecre.R
import com.crecrew.crecre.UI.Adapter.RankChartRecyclerViewAdapter
import com.crecrew.crecre.UI.View.SimpleDividerItemDecoration
import kotlinx.android.synthetic.main.activity_creator_search.*
import kotlinx.android.synthetic.main.fragment_rank.*

class CreatorSearchActivity : AppCompatActivity() {

    lateinit var rankChartRecyclerViewAdapter: RankChartRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creator_search)

        activity_creator_search_et.requestFocus()
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)

        // 검색 전 : configureBeforeSearch()


        // 검색 결과 없음: configureNoResult()

        // 검색 결과
        configureRecyclerView()

        activity_creator_search_iv_erase.setOnClickListener {
            activity_creator_search_et.setText(null)
        }


    }

    fun configureBeforeSearch(){
        // 숫자는 크리에이터의 수
        var creator_num = String.format("%,d",213965)

        var str = "크리크리는 " + creator_num +"명의" + '\n'+"크리에이터와 함께 하고 있습니다."

        var ssb = SpannableStringBuilder(str)
        ssb.setSpan(ForegroundColorSpan(Color.parseColor("#ff57f7")),6, 6 + creator_num.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        ssb.setSpan(RelativeSizeSpan(1.1f) ,6, 6 + creator_num.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        activity_creator_search_txt.setText(ssb)
    }

    fun configureNoResult(){
        activity_creator_search_iv_erase.visibility = VISIBLE
        activity_creator_search_txt.setText("검색 결과가 없습니다")
    }

    private fun configureRecyclerView(){

        // rank data
        var rankData: ArrayList<RankData> = ArrayList()

        rankData.add(RankData(1,3, "https://news.imaeil.com/inc/photos/2019/04/29/2019042900310562698_l.jpg","먹방","시연조교",R.drawable.icn_rank1.toString(),13575321548))
        rankData.add(RankData(1,10, "https://mblogthumb-phinf.pstatic.net/MjAxODA1MTlfOSAg/MDAxNTI2NzQwNjY5OTUx.VcucGKX52noaAETS5acZgeovzLRSCWs8AkzGJVJUuasg.PIDUYkcbI_IaBRJ25-Lgu4-pnrDdVuP8uWK4ZRQbxl8g.JPEG.okyunju0309/PicsArt_05-19-01.19.40.jpg?type=w800","브이로그","가희바위보슬보슬개미똥꼬멍멍이가노래를한다",R.drawable.icn_rank1.toString(),453215))


        rankChartRecyclerViewAdapter = RankChartRecyclerViewAdapter(this, rankData)
        activity_creator_rv_search_result.adapter = rankChartRecyclerViewAdapter
        activity_creator_rv_search_result.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        activity_creator_rv_search_result.addItemDecoration(SimpleDividerItemDecoration(Color.parseColor("#eaeaea"), 1))

        var str = "중복 크리에이터" + rankData.size + "명"
        activity_creator_search_result_num.setText(str)



    }

}