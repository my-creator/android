package com.crecrew.crecre.UI.Activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.crecrew.crecre.R
import kotlinx.android.synthetic.main.activity_vote_suggest5.*
var stopClick: Int = 2;
class VoteSuggestActivity:AppCompatActivity() {

    var item_list = ArrayList<String>()


    fun changePinkGray(v: View){
        v.setOnFocusChangeListener(){v,hasFocus->
            if (hasFocus) v.setBackgroundResource(R.drawable.vote_suggest_pink_back);
            else v.setBackgroundResource(R.drawable.vote_suggest_gray_back);
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vote_suggest5)
        vote_suggest_typing_item5.setVisibility(View.GONE);
        vote_suggest_typing_item4.setVisibility(View.GONE);
        vote_suggest_typing_item3.setVisibility(View.GONE);

        vote_suggest_additem.setOnClickListener{
            stopClick = stopClick + 1;

                if (stopClick == 3){
                    vote_suggest_typing_item3.setVisibility(View.VISIBLE);
                }
                else if (stopClick == 4){
                    vote_suggest_typing_item4.setVisibility(View.VISIBLE);
                }
                else if (stopClick == 5){
                    vote_suggest_typing_item5.setVisibility(View.VISIBLE);
                    vote_suggest_additem.setBackgroundResource(R.drawable.vote_suggest_additem_gray)
                }

        }
        changePinkGray(vote_suggest_typing_subj);
        changePinkGray(vote_suggest_typing_item1);
        changePinkGray(vote_suggest_typing_item2);
        changePinkGray(vote_suggest_typing_item3);
        changePinkGray(vote_suggest_typing_item4);
        changePinkGray(vote_suggest_typing_item5);

    }




}