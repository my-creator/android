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
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.RelativeLayout
import com.crecrew.crecre.Data.CreatorNumData
import com.crecrew.crecre.Data.CreatorSearchData
import com.crecrew.crecre.Network.ApplicationController
import com.crecrew.crecre.Network.CreatorNetworkService
import com.crecrew.crecre.Network.Get.GetCreatorNum
import com.crecrew.crecre.Network.Get.GetCreatorSearch
import com.crecrew.crecre.Network.Get.RankData
import com.crecrew.crecre.R
import com.crecrew.crecre.UI.Adapter.RankChartRecyclerViewAdapter
import com.crecrew.crecre.UI.View.SimpleDividerItemDecoration
import kotlinx.android.synthetic.main.activity_creator_search.*
import kotlinx.android.synthetic.main.fragment_rank.*
import kotlinx.android.synthetic.main.rv_item_rank_creator.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CreatorSearchActivity : AppCompatActivity() {

    lateinit var rankChartRecyclerViewAdapter: RankChartRecyclerViewAdapter

    var rankData: ArrayList<RankData> = ArrayList()

    val creatorNetworkService: CreatorNetworkService by lazy{
        ApplicationController.instance.creatorNetworkService
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creator_search)

        activity_creator_search_et.requestFocus()
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)

        // creator 숫자 받아와서 뿌려주기
        getCreatorNum()

        // 검색
        activity_creator_search_et.setOnEditorActionListener { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_SEARCH){

                // 검색어가 없을 때
                if(activity_creator_search_et.text.toString().equals(""))
                    configureNoResult()
                // 검색어가 있을 떄
                else {
                    getCreatorSearch(activity_creator_search_et.text.toString())
                }
                true
            }
           false
        }


        activity_creator_search_iv_erase.setOnClickListener {
            activity_creator_search_et.setText(null)
        }
        activity_creator_search_container.setOnClickListener {
            downKeyboard(activity_creator_search_container)
        }


    }

    fun configureBeforeSearch(creator_num: Int){

        activity_creator_search_txt.visibility = VISIBLE

        // 숫자는 크리에이터의 수
        var num = String.format("%,d",creator_num)

        var str = "크리크리는 " + num +"명의" + '\n'+"크리에이터와 함께 하고 있습니다."

        var ssb = SpannableStringBuilder(str)
        ssb.setSpan(ForegroundColorSpan(Color.parseColor("#ff57f7")),6, 6 + num.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        ssb.setSpan(RelativeSizeSpan(1.1f) ,6, 6 + num.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        activity_creator_search_txt.setText(ssb)
    }

    fun configureNoResult(){
        activity_creator_search_iv_erase.visibility = VISIBLE
        activity_creator_search_result_container.visibility = GONE
        activity_creator_search_txt.visibility = VISIBLE

        activity_creator_search_txt.setText("검색 결과가 없습니다")
    }

    private fun configureResult(){
        activity_creator_search_iv_erase.visibility = VISIBLE
        activity_creator_search_result_container.visibility = VISIBLE
        activity_creator_search_txt.visibility = GONE

        rankChartRecyclerViewAdapter = RankChartRecyclerViewAdapter(this@CreatorSearchActivity, rankData, 0)
        activity_creator_rv_search_result.adapter = rankChartRecyclerViewAdapter
        activity_creator_rv_search_result.layoutManager = LinearLayoutManager(this@CreatorSearchActivity, LinearLayoutManager.VERTICAL, false)
        activity_creator_rv_search_result.addItemDecoration(SimpleDividerItemDecoration(Color.parseColor("#eaeaea"), 1))
    }


    private fun downKeyboard(view: View) {

        val imm: InputMethodManager =
            this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
        activity_creator_search_et.clearFocus()

    }

    /*********************************************** 통신 *******************************************************/

    private fun getCreatorNum(){
            val getCreatorNum = creatorNetworkService.getCreatorNum()

            getCreatorNum.enqueue(object: Callback<GetCreatorNum>{
                override fun onFailure(call: Call<GetCreatorNum>, t: Throwable) {
                    Log.e("get creator num fail",t.toString())
                }

                override fun onResponse(call: Call<GetCreatorNum>, response: Response<GetCreatorNum>) {
                    if(response.isSuccessful){
                        if(response.body()!!.status==200){
                            val tmp: ArrayList<CreatorNumData> = response.body()!!.data!!
                            configureBeforeSearch(tmp[0].creatorAllCnt)
                        }
                    }
                }
            })

    }

    private fun getCreatorSearch(str : String){

        val getCreatorSearch = creatorNetworkService.getCreatorSearch(str)
        getCreatorSearch.enqueue(object: Callback<GetCreatorSearch>{
            override fun onFailure(call: Call<GetCreatorSearch>, t: Throwable) {
                Log.e("creator search fail",t.toString())
            }

            override fun onResponse(call: Call<GetCreatorSearch>, response: Response<GetCreatorSearch>) {
                if(response.isSuccessful){
                    if(response.body()!!.status == 200){
                        val tmp :ArrayList<RankData> = response.body()!!.data!!
                        rankData = tmp

                        // data가 있는 경우
                        if(tmp.size >0){
                            configureResult()
                        }
                        else{   // data가 없는 경우
                            configureNoResult()
                        }

                        var same_creator = "중복 크리에이터 " + rankData.size + "명"
                        activity_creator_search_result_num.setText(same_creator)

                        downKeyboard(activity_creator_search_result_container)
                    }
                }
            }
        })
    }



}