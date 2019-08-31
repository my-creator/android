package com.crecrew.crecre.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.crecrew.crecre.base.BasePagerAdapter
import com.crecrew.crecre.db.SharedPreferenceController
import com.crecrew.crecre.R
import com.crecrew.crecre.ui.fragment.Community.CommunityFragment
import com.crecrew.crecre.ui.fragment.vote.VoteFragment
import com.crecrew.crecre.utils.ApplicationData
import com.crecrew.crecreUI.Fragment.MypageFragment
import com.crecrew.crecreUI.Fragment.RankFragment
import kotlinx.android.synthetic.main.activity_main.*
import scom.crecrew.crecre.UI.Fragment.HomeFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

/*
        var flag = intent.getIntExtra("flag", -1)

        if(flag == 1)
            Toast.makeText(this, "회원님 안녕하세요!!", Toast.LENGTH_LONG).show()
        else
            Toast.makeText(this, "둘러보기를 시작합니다", Toast.LENGTH_LONG).show()
*/


        //ApplicationData.loginState = false
        ApplicationData.auth =SharedPreferenceController.getUserToken(this)
        Log.v("login_token", ApplicationData.auth)

        if(ApplicationData.auth =="")
            Toast.makeText(this, "둘러보기를 시작합니다", Toast.LENGTH_LONG).show()
        else
            Toast.makeText(this, "회원님 안녕하세요!!", Toast.LENGTH_LONG).show()


        // ViewPager
        activity_main_vp_container.run {
            adapter = BasePagerAdapter(supportFragmentManager).apply {
                addFragment(HomeFragment())
                addFragment(RankFragment())
                addFragment(VoteFragment())
                addFragment(CommunityFragment())
                addFragment(MypageFragment())
            }
            offscreenPageLimit = 4
        }

        // TabLayout
        activity_main_tl_navi.run {
            val navigationLayout: View =
                LayoutInflater.from(this@MainActivity).inflate(R.layout.activity_main_navi, null, false)
            setupWithViewPager(activity_main_vp_container)
            getTabAt(0)!!.customView =
                navigationLayout.findViewById(R.id.activity_main_navi_home_container) as LinearLayout
            getTabAt(1)!!.customView =
                navigationLayout.findViewById(R.id.activity_main_navi_rank_container) as LinearLayout
            getTabAt(2)!!.customView =
                navigationLayout.findViewById(R.id.activity_main_navi_vote_container) as LinearLayout
            getTabAt(3)!!.customView =
                navigationLayout.findViewById(R.id.activity_main_navi_community_container) as LinearLayout
            getTabAt(4)!!.customView =
                navigationLayout.findViewById(R.id.activity_main_navi_mypage_container) as LinearLayout
        }
    }
}