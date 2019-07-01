package com.crecrew.crecre.UI.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.View
import com.crecrew.crecre.R
import com.crecrew.crecre.UI.Fragment.CommunityFragment
import com.crecrew.crecre.UI.Fragment.VoteFragment
import com.crecrew.crecreUI.Fragment.MypageFragment
import com.crecrew.crecreUI.Fragment.RankFragment
import kotlinx.android.synthetic.main.activity_main_navi.*
import org.jetbrains.anko.textColor
import scom.crecrew.crecre.UI.Fragment.HomeFragment

class MainActivity : AppCompatActivity(), View.OnClickListener{

    override fun onClick(v:View?){

        when (v){
            activity_main_navi_home_container->{
                clearSelected()
                Log.e("click","click home icon")
                activity_main_navi_home_container.isSelected = true
                activity_main_navi_txt_home.setTextColor(ContextCompat.getColor(this, R.color.violet_pink))
                replaceFragment(HomeFragment())

            }
            activity_main_navi_rank_container->{
                clearSelected()
                activity_main_navi_rank_container.isSelected = true
                replaceFragment(RankFragment())
            }
            activity_main_navi_vote_container->{
                clearSelected()
                activity_main_navi_vote_container.isSelected = true
                replaceFragment(VoteFragment())
            }
            activity_main_navi_community_container->{
                clearSelected()
                activity_main_navi_community_container.isSelected = true
                replaceFragment(CommunityFragment())
            }
            activity_main_navi_mypage_container->{
                clearSelected()
                activity_main_navi_mypage_container.isSelected = true
                replaceFragment(MypageFragment())
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activity_main_navi_home_container.setOnClickListener(this)
        activity_main_navi_rank_container.setOnClickListener(this)
        activity_main_navi_vote_container.setOnClickListener(this)
        activity_main_navi_community_container.setOnClickListener(this)
        activity_main_navi_mypage_container.setOnClickListener(this)

        addFragment(HomeFragment())
        activity_main_navi_home_container.isSelected = true

    }

    fun clearSelected(){
        activity_main_navi_home_container.isSelected = false
        activity_main_navi_rank_container.isSelected = false
        activity_main_navi_vote_container.isSelected = false
        activity_main_navi_community_container.isSelected = false
        activity_main_navi_mypage_container.isSelected = false

    }

    fun addFragment(fragment : Fragment) : Unit{
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        transaction.add(R.id.activity_main_fl_container, fragment)
        transaction.commit()
    }

    fun replaceFragment(fragment:Fragment): Unit{
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        transaction.replace(R.id.activity_main_fl_container, fragment)
        transaction.commit()
    }
}
