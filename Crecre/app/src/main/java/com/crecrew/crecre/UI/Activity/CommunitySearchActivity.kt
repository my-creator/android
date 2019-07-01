package com.crecrew.crecre.UI.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.crecrew.crecre.R
import kotlinx.android.synthetic.main.activity_community_search.*

class CommunitySearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community_search)

        btn_back_community_search.setOnClickListener {
            finish()
        }
    }


}
