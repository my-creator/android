package com.crecrew.crecre.UI.Activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.crecrew.crecre.R
import kotlinx.android.synthetic.main.activity_creator_profile.*

class CreatorProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creator_profile)

        val intent = intent
        val creator_name = intent.getStringExtra("creator_name")

        // activity_creator_profile_tv_name.setText(creator_name)

    }

}