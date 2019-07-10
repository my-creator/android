package com.crecrew.crecre.UI.Activity

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.crecrew.crecre.Data.ProfileHotVideoData
import android.widget.TextView
import com.crecrew.crecre.R
import com.crecrew.crecre.UI.Activity.Community.CommunityHotPostActivity
import com.crecrew.crecre.UI.Adapter.ProfileHotVideoRecyclerViewAdapter
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.RadarChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.data.RadarDataSet
import com.github.mikephil.charting.data.RadarEntry
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet
import kotlinx.android.synthetic.main.activity_creator_profile.*
import org.jetbrains.anko.startActivity
import java.util.ArrayList

class CreatorProfileActivity : FragmentActivity() {

    lateinit var profileHotVideoRecyclerViewAdapter: ProfileHotVideoRecyclerViewAdapter
    lateinit var total: TextView
    lateinit var review: TextView
    lateinit var mChart: RadarChart

    val testItem = arrayListOf<String>("진행능력", "소통력", "참신성", "편집력", "핫 지수")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creator_profile)
        configureRecyclerView()

        val intent = intent
        val creator_name = intent.getStringExtra("creator_name")

        activity_creator_profile_btn_go_fanpage.setOnClickListener{
            startActivity<CommunityHotPostActivity>()
        }

        activity_creator_profile_btn_join_stat.setOnClickListener{
            startActivity<ProfileJoinStatActivity>()
        }

        activity_creator_profile_btn_back.setOnClickListener{
            finish()
        }
        // activity_creator_profile_tv_name.setText(creator_name)
        activity_creator_profile_btn_rank_question.setOnClickListener{
            startActivity<ProfileRankQuestionActivity>()
        }

        activity_creator_profile_btn_class_question.setOnClickListener{
            startActivity<ProfileClassQuestionActivity>()
        }

        review = activity_creator_profile_review_number
        total = activity_creator_profile_total

        val fontReguler = total.typeface

        total.setTextColor(Color.rgb(51, 51, 51))

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
            setValueTypeface(fontReguler)
            setValueTextSize(8f)
            setDrawValues(false)
            setValueTextColor(Color.WHITE)
            isHighlightEnabled = false
        }

        mChart.data = data
        mChart.invalidate()

        total.text = "토탈${dataSetAvg(entries1)}점"
        review.text = "(192명 리뷰)"

        mChart.animateXY(1400, 1400, Easing.EaseInOutQuad)

        mChart.run {
            xAxis.run {
                typeface = fontReguler
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
                //typeface = mTfLight
                setLabelCount(6, true)
                axisMinimum = 0f
                axisMaximum = 5f
                setDrawLabels(false)
            }
        }
    }

    private fun configureRecyclerView(){
        var profileHotDataList: ArrayList<ProfileHotVideoData> = ArrayList()

        profileHotDataList.add(ProfileHotVideoData(2, "대박대박 정호의 누네띄네 100개 먹방 영상", "https://www.naver.com", 1200000, "https://i.ytimg.com/vi/SzJo9QfhZg8/maxresdefault.jpg","2019-08-07 04:51","정호네식당"))
        profileHotDataList.add(ProfileHotVideoData(4, "대박대박 시연의 좋은데이 100개 먹방 영상", "https://www.naver.com", 1000000,"https://i.ytimg.com/vi/SzJo9QfhZg8/maxresdefault.jpg","2019-10-07 04:51","시연네식당"))
        profileHotDataList.add(ProfileHotVideoData(6, "대박대박 홍삼의 고구마간식 100개 먹방 영상", "https://www.naver.com", 120000, "https://i.ytimg.com/vi/SzJo9QfhZg8/maxresdefault.jpg","2019-07-02 04:51","홍삼네식당"))

        profileHotVideoRecyclerViewAdapter = ProfileHotVideoRecyclerViewAdapter(this, profileHotDataList)
        activity_creator_profile_rv_hot_video.adapter = profileHotVideoRecyclerViewAdapter
        activity_creator_profile_rv_hot_video.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        activity_creator_profile_rv_hot_video.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        // new post
        var profileNewDataList: ArrayList<ProfileHotVideoData> = ArrayList()

        profileNewDataList.add(ProfileHotVideoData(2, "대박대박 정호의 누네띄네 100개 먹방 영상", "https://www.naver.com", 1200000, "https://i.ytimg.com/vi/SzJo9QfhZg8/maxresdefault.jpg","2019-07-07 04:51","정호네식당"))
        profileNewDataList.add(ProfileHotVideoData(4, "대박대박 시연의 좋은데이 100개 먹방 영상", "https://www.naver.com", 1000000,"https://i.ytimg.com/vi/SzJo9QfhZg8/maxresdefault.jpg","2019-07-03 04:51","시연네식당"))
        profileNewDataList.add(ProfileHotVideoData(6, "대박대박 홍삼의 고구마간식 100개 먹방 영상", "https://www.naver.com", 120000,"https://i.ytimg.com/vi/SzJo9QfhZg8/maxresdefault.jpg","2019-07-07 12:51","홍삼네식당"))

        profileHotVideoRecyclerViewAdapter = ProfileHotVideoRecyclerViewAdapter(this, profileNewDataList)
        activity_creator_profile_rv_new_video.adapter = profileHotVideoRecyclerViewAdapter
        activity_creator_profile_rv_new_video.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        activity_creator_profile_rv_new_video.addItemDecoration(DividerItemDecoration(this!!, DividerItemDecoration.VERTICAL))

    }

    private fun dataSetAvg(dataSet: ArrayList<RadarEntry>): Float {
        var total = 0f
        for (i in dataSet.indices)
            total += dataSet[i].value
        return total / dataSet.size
    }


}