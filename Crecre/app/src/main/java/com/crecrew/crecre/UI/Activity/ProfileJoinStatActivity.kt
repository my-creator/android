package com.crecrew.crecre.UI.Activity

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.view.View
import android.widget.ProgressBar
import com.crecrew.crecre.R
import com.crecrew.crecre.UI.Activity.Community.CommunityHotPostActivity
import kotlinx.android.synthetic.main.activity_creator_profile.*

import kotlinx.android.synthetic.main.activity_profile_join_stat.*
import org.jetbrains.anko.startActivity

class ProfileJoinStatActivity : AppCompatActivity() {

    val progress = ArrayList<ProgressBar>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_join_stat)

//        progress.addAll(
//            arrayOf(
//                activity_profile_join_stat_progressbar_1,
//                activity_profile_join_stat_progressbar_2,
//                activity_profile_join_stat_progressbar_3,
//                activity_profile_join_stat_progressbar_4,
//                activity_profile_join_stat_progressbar_5
//            )
//        )
        activity_profile_join_stat_btn_back.setOnClickListener {
            finish()
        }
        activity_profile_join_stat_btn_1_0.setOnClickListener {
            activity_profile_join_stat_progressbar_1.progress = 0
        }
        activity_profile_join_stat_btn_1_1.setOnClickListener {
            activity_profile_join_stat_progressbar_1.progress = 1
        }
        activity_profile_join_stat_btn_1_2.setOnClickListener {
            activity_profile_join_stat_progressbar_1.progress = 2
        }
        activity_profile_join_stat_btn_1_3.setOnClickListener {
            activity_profile_join_stat_progressbar_1.progress = 3
        }
        activity_profile_join_stat_btn_1_4.setOnClickListener {
            activity_profile_join_stat_progressbar_1.progress = 4
        }
        activity_profile_join_stat_btn_1_5.setOnClickListener {
            activity_profile_join_stat_progressbar_1.progress = 5
        }
        activity_profile_join_stat_btn_2_0.setOnClickListener {
            activity_profile_join_stat_progressbar_2.progress = 0
        }
        activity_profile_join_stat_btn_2_1.setOnClickListener {
            activity_profile_join_stat_progressbar_2.progress = 1
        }
        activity_profile_join_stat_btn_2_2.setOnClickListener {
            activity_profile_join_stat_progressbar_2.progress = 2
        }
        activity_profile_join_stat_btn_2_3.setOnClickListener {
            activity_profile_join_stat_progressbar_2.progress = 3
        }
        activity_profile_join_stat_btn_2_4.setOnClickListener {
            activity_profile_join_stat_progressbar_2.progress = 4
        }
        activity_profile_join_stat_btn_2_5.setOnClickListener {
            activity_profile_join_stat_progressbar_2.progress = 5
        }
        activity_profile_join_stat_btn_3_0.setOnClickListener {
            activity_profile_join_stat_progressbar_3.progress = 0
        }
        activity_profile_join_stat_btn_3_1.setOnClickListener {
            activity_profile_join_stat_progressbar_3.progress = 1
        }
        activity_profile_join_stat_btn_3_2.setOnClickListener {
            activity_profile_join_stat_progressbar_3.progress = 2
        }
        activity_profile_join_stat_btn_3_3.setOnClickListener {
            activity_profile_join_stat_progressbar_3.progress = 3
        }
        activity_profile_join_stat_btn_3_4.setOnClickListener {
            activity_profile_join_stat_progressbar_3.progress = 4
        }
        activity_profile_join_stat_btn_3_5.setOnClickListener {
            activity_profile_join_stat_progressbar_3.progress = 5
        }
        activity_profile_join_stat_btn_4_0.setOnClickListener {
            activity_profile_join_stat_progressbar_4.progress = 0
        }
        activity_profile_join_stat_btn_4_1.setOnClickListener {
            activity_profile_join_stat_progressbar_4.progress = 1
        }
        activity_profile_join_stat_btn_4_2.setOnClickListener {
            activity_profile_join_stat_progressbar_4.progress = 2
        }
        activity_profile_join_stat_btn_4_3.setOnClickListener {
            activity_profile_join_stat_progressbar_4.progress = 3
        }
        activity_profile_join_stat_btn_4_4.setOnClickListener {
            activity_profile_join_stat_progressbar_4.progress = 4
        }
        activity_profile_join_stat_btn_4_5.setOnClickListener {
            activity_profile_join_stat_progressbar_4.progress = 5
        }
        activity_profile_join_stat_btn_5_0.setOnClickListener {
            activity_profile_join_stat_progressbar_5.progress = 0
        }
        activity_profile_join_stat_btn_5_1.setOnClickListener {
            activity_profile_join_stat_progressbar_5.progress = 1
        }
        activity_profile_join_stat_btn_5_2.setOnClickListener {
            activity_profile_join_stat_progressbar_5.progress = 2
        }
        activity_profile_join_stat_btn_5_3.setOnClickListener {
            activity_profile_join_stat_progressbar_5.progress = 3
        }
        activity_profile_join_stat_btn_5_4.setOnClickListener {
            activity_profile_join_stat_progressbar_5.progress = 4
        }
        activity_profile_join_stat_btn_5_5.setOnClickListener {
            activity_profile_join_stat_progressbar_5.progress = 5
        }






    }

}
