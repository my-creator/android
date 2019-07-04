package com.crecrew.crecre.UI.Activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.crecrew.crecre.R
import kotlinx.android.synthetic.main.activity_vote_suggest.*

class VoteSuggestActivity:AppCompatActivity() {

    var item_list = ArrayList<String>()
    var stopClick: Int = 2;

    fun changePinkGray(v: View){
        v.setOnFocusChangeListener(){v,hasFocus->
            if (hasFocus) v.setBackgroundResource(R.drawable.vote_suggest_pink_back);
            else v.setBackgroundResource(R.drawable.vote_suggest_additem_gray);
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vote_suggest)

        vote_suggest_additem.setOnClickListener{
            stopClick += 1;
            if (stopClick <= 5)
            {
                if (stopClick == 3){setContentView(R.layout.activity_vote_suggest3)}
                else if (stopClick == 4){setContentView(R.layout.activity_vote_suggest4)}
                else if (stopClick == 5){setContentView(R.layout.activity_vote_suggest5)}
            }
            else
            {
                setContentView(R.layout.activity_vote_suggest5)
                vote_suggest_additem.setBackgroundResource(R.drawable.vote_suggest_additem_gray) //회색으로 바꿔
            }
        }
        changePinkGray(vote_suggest_typing_subj);

        /*vote_suggest_typing_subj.setOnFocusChangeListener() { it, hasFocus->
            if (hasFocus) it.setBackgroundResource(R.drawable.vote_suggest_pink_back)
        }*/


    }




}