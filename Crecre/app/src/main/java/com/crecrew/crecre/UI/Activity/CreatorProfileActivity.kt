package com.crecrew.crecre.UI.Activity

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.crecrew.crecre.Base.BasePagerAdapter
import com.crecrew.crecre.Data.*
import com.crecrew.crecre.Network.ApplicationController
import com.crecrew.crecre.Network.CreatorNetworkService
import com.crecrew.crecre.Network.Get.GetCreatorTodayHotRank
import com.crecrew.crecre.Network.Get.GetProfileHotVideoResponse
import com.crecrew.crecre.Network.Get.GetProfileResponse
import com.crecrew.crecre.Network.Get.GetProfileStatResponse
import com.crecrew.crecre.R
import com.crecrew.crecre.UI.Activity.Community.CommunityHotPostActivity
import com.crecrew.crecre.UI.Adapter.ProfileHotVideoRecyclerViewAdapter
import com.crecrew.crecre.UI.Fragment.HomeTodayRankFragment
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.RadarChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.data.RadarDataSet
import com.github.mikephil.charting.data.RadarEntry
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet
import kotlinx.android.synthetic.main.activity_creator_profile.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import org.jetbrains.anko.ctx
import org.jetbrains.anko.startActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreatorProfileActivity : FragmentActivity() {

    lateinit var profileHotVideoRecyclerViewAdapter: ProfileHotVideoRecyclerViewAdapter
    lateinit var total: TextView
    lateinit var review: TextView
    lateinit var mChart: RadarChart

    //"진행능력", "소통력", "참신성", "편집력", "핫 지수"
    // val testItem = arrayListOf<String>("진행능력", "소통력", "참신성", "편집력", "핫 지수")

    lateinit var creatorProfileData : CreatorProfileData
    lateinit var statData: StatData
    lateinit var mTypeface: Typeface

    val creatorNetworkService: CreatorNetworkService by lazy{
        ApplicationController.instance.creatorNetworkService
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creator_profile)


        var creator_idx = 5123
        mTypeface = activity_creator_profile_review_number.typeface


        getProfileResponse(creator_idx)
        getProfileStatResponse(creator_idx)


        activity_creator_profile_btn_go_fanpage.setOnClickListener {
            startActivity<CommunityHotPostActivity>()
        }

        activity_creator_profile_btn_join_stat.setOnClickListener {
            startActivity<ProfileJoinStatActivity>()
        }

        activity_creator_profile_btn_back.setOnClickListener {
            finish()
        }
        // activity_creator_profile_tv_name.setText(creator_name)
        activity_creator_profile_btn_rank_question.setOnClickListener {
            startActivity<ProfileRankQuestionActivity>()
        }

        activity_creator_profile_btn_class_question.setOnClickListener {
            startActivity<ProfileClassQuestionActivity>()
        }

    }

    fun makeGraph(testItem : ArrayList<String>){

        mChart = activity_creator_profile_rc_chart
        mChart.run {
            isRotationEnabled = false
            description.isEnabled = false
            webLineWidth = 1f
            webColor = Color.WHITE
            webLineWidthInner = 1f
            radarBackgroundColor = Color.rgb(240, 240, 240)
            webOuterColor = Color.rgb(170, 170, 170)
            webColorInner = Color.rgb(221, 221, 221)
            //webAlpha = 100
        }

        val cnt = 5

        val entries1 = ArrayList<RadarEntry>()

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (i in 0 until cnt) {
            val val1 = (i + 1).toFloat()
            entries1.add(RadarEntry(val1))
        }

        val set1 = RadarDataSet(entries1, "크리에이터 스텟")
        set1.color = Color.rgb(28, 28, 28)
        set1.fillDrawable = getDrawable(R.drawable.radar_bg)
        set1.setDrawFilled(true)
        set1.lineWidth = 1f

        val sets = ArrayList<IRadarDataSet>()
        sets.add(set1)

        val data = RadarData(sets)
        data.run {
            setValueTypeface(mTypeface)
            setValueTextSize(8f)
            setDrawValues(false)
            setValueTextColor(Color.WHITE)
            isHighlightEnabled = false
        }

        mChart.data = data
        mChart.invalidate()

        // total.text = "토탈${dataSetAvg(entries1)}점"
        // review.text = "(192명 리뷰)"

        mChart.animateXY(1400, 1400, Easing.EaseInOutQuad)

        mChart.run {
            xAxis.run {
                typeface = mTypeface
                textSize = 14f
                yOffset = 0f
                xOffset = 0f
                setMultiLineLabel(true)
                valueFormatter = object : IAxisValueFormatter {

                    private val mActivities = arrayOf(
                        "${testItem[0]}\n${entries1[0].value}점",
                        "${testItem[1]}\n${entries1[1].value}점",
                        "${testItem[2]}\n${entries1[2].value}점",
                        "${testItem[3]}\n${entries1[3].value}점",
                        "${testItem[4]}\n${entries1[4].value}점")

                    override fun getFormattedValue(value: Float, axis: AxisBase): String {
                        return mActivities[value.toInt() % mActivities.size]
                    }
                }
                textColor = R.color.chartXAxisTextColor
            }

            yAxis.run {
                typeface = mTypeface
                setLabelCount(6, true)
                axisMinimum = 0f
                axisMaximum = 5f
                setDrawLabels(false)
            }
        }
    }


    private fun getProfileResponse(creatorIdx: Int){
        val getProfileResponse = creatorNetworkService.getProfileResponse(creatorIdx)
        getProfileResponse.enqueue(object : Callback<GetProfileResponse> {
            override fun onFailure(call: Call<GetProfileResponse>, t: Throwable) {
                Log.e("creator search fail",t.toString())
            }
            override fun onResponse(call: Call<GetProfileResponse>, response: Response<GetProfileResponse>) {
                if(response.isSuccessful){
                    if(response.body()!!.status == 200){
                        creatorProfileData = response.body()!!.data
                        Log.v("TAGG", creatorProfileData.profile_url)
                        Glide.with(this@CreatorProfileActivity).load(creatorProfileData.profile_url).apply(RequestOptions().circleCrop()).into(profile_creator_main_img)
                        Glide.with(this@CreatorProfileActivity).load(creatorProfileData.profile_asset).into(profile_creator_all_class)
                        Glide.with(this@CreatorProfileActivity).load(creatorProfileData.follower_grade_img_url).into(profile_creator_rank)
                        profile_creator_channel.text = creatorProfileData.creator_name
                        creator_profile_category.text = creatorProfileData.category_name + " " + creatorProfileData.category_lank + "위"
                        profile_follower_cnt.text = creatorProfileData.follower_cnt.toString() + "명"
                        youtube_subscriber_cnt.text = String.format("%,d",creatorProfileData.youtube_subscriber_cnt) + "명"
                        youtube_view_cnt.text = String.format("%,d",creatorProfileData.youtube_view_cnt) + "명"
                        activity_creator_profile_description.text = creatorProfileData.contents
                        Glide.with(this@CreatorProfileActivity).load(creatorProfileData.follower_grade_img_url).into(activity_creator_profile_rank_img)
                        Glide.with(this@CreatorProfileActivity).load(creatorProfileData.view_grade_img_url).into(activity_creator_profile_class_img)
                        activity_creator_profile_rank_tier.text = creatorProfileData.follower_grade_name + " " + creatorProfileData.follower_grade_level
                        activity_creator_profile_rank_percent.text = creatorProfileData.follower_grade_percent.toString() + "%"
                        activity_creator_profile_front_rank1_exp.text = "(" + creatorProfileData.front_lank_exp.toString()
                        activity_creator_profile_back_rank1_exp.text = "/" + creatorProfileData.back_lank_exp.toString() + ")"
                        activity_creator_profile_class_tier.text = "클래스 " + creatorProfileData.view_grade_name
                        activity_creator_profile_class_percentage.text = creatorProfileData.view_grade_percent.toString() + "%"
                        activity_creator_profile_front_rank2_exp.text = "(" + creatorProfileData.front_lank2_exp.toString()
                        activity_creator_profile_back_rank2_exp.text = "/" + creatorProfileData.back_lank2_exp.toString() + ")"
                        circularProgressbar1.progress = creatorProfileData.follower_grade_percent
                        circularProgressbar2.progress = creatorProfileData.view_grade_percent
                    }
                }
            }
        })

        }


    private fun getProfileStatResponse(creatorIdx: Int){
        val getProfileStatResponse = creatorNetworkService.getProfileStatResponse(creatorIdx)
        getProfileStatResponse.enqueue(object : Callback<GetProfileStatResponse> {
            override fun onFailure(call : Call<GetProfileStatResponse>, t: Throwable) {
                Log.e("creator search fail", t.toString())
            }
            override fun onResponse(call: Call<GetProfileStatResponse>, response: Response<GetProfileStatResponse>){
                if(response.isSuccessful){
                    if(response.body()!!.status == 200){
                        // Log.e("dfdfdf", "dfdfdf")
                        var creatorStatData: ArrayList<StatData> = response.body()!!.data
                        // Log.e("success", "success")
                        activity_creator_profile_total.text = "토탈 " + String.format("%.2f", creatorStatData[5].avg_stat) + "점"
                        activity_creator_profile_review_number.text = "(" + creatorStatData[5].join_cnt_stat.toString() + "명 리뷰)"
                        val testItem = arrayListOf(creatorStatData[0].name, creatorStatData[1].name, creatorStatData[2].name, creatorStatData[3].name, creatorStatData[4].name)
                        makeGraph(testItem)
                    }
                }
            }
        })
    }
    private fun getProfileHotVideo(creatorIdx: Int){
        val getProfileHotVideResponse = creatorNetworkService.getProfileHotVideoResponse(creatorIdx)
        getProfileHotVideResponse.enqueue(object : Callback<GetProfileHotVideoResponse> {
            override fun onFailure(call: Call<GetProfileHotVideoResponse>, t: Throwable) {
                Log.e("creator search fail", t.toString())
            }
            override fun onResponse(call: Call<GetProfileHotVideoResponse>, response: Response<GetProfileHotVideoResponse>){
                if(response.isSuccessful){
                    if(response.body()!!.status == 200){
                        var HotVideoDatas : ArrayList<HotVideoData> = response.body()!!.data
                        configureRecyclerView(HotVideoDatas)
                    }
                }
            }
        })
    }

    private fun configureRecyclerView(profileHotDataList : ArrayList<HotVideoData>){


        // dfsljfsdajasdfjla;njajananagnanavnavnoananarvn
        profileHotVideoRecyclerViewAdapter = ProfileHotVideoRecyclerViewAdapter(this, profileHotDataList)
        activity_creator_profile_rv_hot_video.adapter = profileHotVideoRecyclerViewAdapter
        activity_creator_profile_rv_hot_video.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        activity_creator_profile_rv_hot_video.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        // new post
//        var profileNewDataList: ArrayList<ProfileHotVideoData> = ArrayList()
//
//        profileHotVideoRecyclerViewAdapter = ProfileHotVideoRecyclerViewAdapter(this, profileNewDataList)
//        activity_creator_profile_rv_new_video.adapter = profileHotVideoRecyclerViewAdapter
//        activity_creator_profile_rv_new_video.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//        activity_creator_profile_rv_new_video.addItemDecoration(DividerItemDecoration(this!!, DividerItemDecoration.VERTICAL))

    }

    private fun dataSetAvg(dataSet: ArrayList<RadarEntry>): Float {
        var total = 0f
        for (i in dataSet.indices)
            total += dataSet[i].value
        return total / dataSet.size
    }




}