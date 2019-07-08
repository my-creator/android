package com.crecrew.crecre.UI.Activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.crecrew.crecre.R
import com.crecrew.crecre.UI.Activity.Community.CommunityHotPostActivity
import kotlinx.android.synthetic.main.activity_creator_profile.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.startActivity

class CreatorProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creator_profile)

        val intent = intent
        val creator_name = intent.getStringExtra("creator_name")

        activity_creator_profile_btn_go_fanpage.setOnClickListener{
            startActivity<CommunityHotPostActivity>()
        }

        activity_creator_profile_btn_join_stat.setOnClickListener{
            startActivity<ProfileJoinStatActivity>()
        }
        // activity_creator_profile_tv_name.setText(creator_name)

    }

}