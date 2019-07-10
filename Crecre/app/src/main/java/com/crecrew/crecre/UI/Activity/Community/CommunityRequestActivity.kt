package com.crecrew.crecre.UI.Activity.Community

import android.content.Context
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.crecrew.crecre.Data.CommunitySmallNewGetData
import com.crecrew.crecre.Network.ApplicationController
import com.crecrew.crecre.Network.CommunityNetworkService
import com.crecrew.crecre.Network.Get.*
import com.crecrew.crecre.Network.Post.PostCommunityFavoriteLikeResponse
import com.crecrew.crecre.R
import com.crecrew.crecre.UI.Fragment.Community.CommunityFragment
import com.crecrew.crecre.utils.CustomRequestCompleteDialog
import com.crecrew.crecre.utils.SearchAlarmDialog
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.activity_community_request.*
import kotlinx.android.synthetic.main.activity_creator_search.*
import kotlinx.android.synthetic.main.custom_dialog_response_complete.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class CommunityRequestActivity : AppCompatActivity(), View.OnClickListener {

    var request_num = 0
    val requestDialog: CustomRequestCompleteDialog by lazy {
        CustomRequestCompleteDialog(
            this@CommunityRequestActivity, "요청완료", configureBeforeSearch(), "팬 100명이 모이면 유튜버의\n" +
                    "개인 게시판이 만들어져요.", completeConfirmListener, "확인"
        )
    }

    val requestfailDialog: SearchAlarmDialog by lazy {
        SearchAlarmDialog(
            this@CommunityRequestActivity, "알림", "아직 입력하지 않은 항목이 있습니다.\n" +
                    "모든 항목을 입력해주세요.",
            completefailConfirmListener, "확인"
        )
    }

    val communityNetworkService: CommunityNetworkService by lazy {
        ApplicationController.instance.communityNetworkService
    }


    override fun onClick(v: View?) {
        when (v!!) {
            //요청하기 버튼
            requestBtn_community_request_act -> {

                if (et_enter_youtuber_name_request_act.text.length == 0 || et_enter_youtube_channel_request_act.text.length == 0) {
                    requestfailDialog.show()
                } else {

                    postCommunityRequest()

                }
            }

            //뒤로가기
            btn_back_request_community_act -> {
                finish()
            }

            //키보드 다운
            rl_comm_request_act -> {
                downKeyboard(rl_comm_request_act)
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community_request)

        init()
        //configureBeforeSearch()
        getRequestCnt()
    }

    private fun init() {
        requestBtn_community_request_act.setOnClickListener(this)
        btn_back_request_community_act.setOnClickListener(this)
        rl_comm_request_act.setOnClickListener(this)

    }

    private val completeConfirmListener = View.OnClickListener {
        requestDialog!!.dismiss()
        finish()
    }

    private val completefailConfirmListener = View.OnClickListener {
        requestfailDialog!!.dismiss()

        //##작성하지 않은 editText focus주기?
    }

    //키보드 다운
    private fun downKeyboard(view: View) {
        val imm: InputMethodManager =
            applicationContext!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    //통신 editText값 보내기
    private fun postCommunityRequest() {

        val boardsname: String = et_enter_youtuber_name_request_act.text.toString()
        val youtubeLink: String = et_enter_youtube_channel_request_act.text.toString()

        //Json 형식의 객체 만들기
        var jsonObject = JSONObject()
        jsonObject.put("name", boardsname)
        jsonObject.put("link", youtubeLink)

        //Gson 라이브러리의 Json Parser을 통해 객체를 Json으로!
        val gsonObject = JsonParser().parse(jsonObject.toString()) as JsonObject

        val getCommunitySmallNewPosts: Call<PostCommunityFavoriteLikeResponse> =
            communityNetworkService.postBoardsRequest(
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkeCI6MTIsImdyYWRlIjoiQURNSU4iLCJuYW1lIjoi66qF64uk7JewIiwiaWF0IjoxNTYyNDIzOTUyLCJleHAiOjE1NjM2MzM1NTIsImlzcyI6InlhbmcifQ.DbGROLSRyAm_NN1qcQ5sLmjxKpUACyMsFQRiDd2z3Lw"
                ,gsonObject)

        getCommunitySmallNewPosts.enqueue(object : Callback<PostCommunityFavoriteLikeResponse> {

            override fun onFailure(call: Call<PostCommunityFavoriteLikeResponse>, t: Throwable) {
                Log.e("request et보내기 fail", t.toString())
            }

            override fun onResponse(
                call: Call<PostCommunityFavoriteLikeResponse>,
                response: Response<PostCommunityFavoriteLikeResponse>
            ) {
                if (response.isSuccessful) {
                    response?.takeIf { it.isSuccessful }
                        ?.body()
                        ?.let {
                            if (it.success == true)
                            {
                                Log.v("TAGG", it.message)
                                requestDialog.show()
                            }

                        }

                }
            }
        })
    }

    fun configureBeforeSearch() : SpannableStringBuilder {

        //85자리에 통신해서 가져온 숫자를 넣어라! text로 바꿔서
        var creator_num = request_num.toString() + "명"
        Log.v("TAGG", creator_num.toString())

        var str = "현재 " + creator_num +"이 이 유튜버의" +'\n'+"개인 게시판을 요청했어요!"

        var ssb = SpannableStringBuilder(str)
        ssb.setSpan(ForegroundColorSpan(Color.parseColor("#ff57f7")),3, 3 + creator_num.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        ssb.setSpan(RelativeSizeSpan(1.1f) ,3, 3 + creator_num.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        return ssb

    }

    //통신 전체 보여주기
    private fun getRequestCnt() {
        val getrequestCnt : Call<GetBoardRequestNumResponse> = communityNetworkService.getBoeardsRequestIdx("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkeCI6MTIsImdyYWRlIjoiQURNSU4iLCJuYW1lIjoi66qF64uk7JewIiwiaWF0IjoxNTYyNDIzOTUyLCJleHAiOjE1NjM2MzM1NTIsImlzcyI6InlhbmcifQ.DbGROLSRyAm_NN1qcQ5sLmjxKpUACyMsFQRiDd2z3Lw",
            5)

        getrequestCnt.enqueue(object : Callback<GetBoardRequestNumResponse> {

            override fun onFailure(call: Call<GetBoardRequestNumResponse>, t: Throwable) {
                Log.e("최신글 전체 list fail", t.toString())
            }

            override fun onResponse(call: Call<GetBoardRequestNumResponse>, response: Response<GetBoardRequestNumResponse>) {

                if (response.isSuccessful) {
                    val temp : ArrayList<CommunityRequestCntData> = response.body()!!.data
                    request_num = temp[0].request_cnt

                }

            }

        })

    }

}
