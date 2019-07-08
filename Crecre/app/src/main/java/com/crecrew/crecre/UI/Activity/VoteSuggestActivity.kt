package com.crecrew.crecre.UI.Activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.crecrew.crecre.R
import com.crecrew.crecre.utils.CustomRequestCompleteDialog
import com.crecrew.crecre.utils.SearchAlarmDialog
import com.crecrew.crecre.utils.VoteSuggestCompleteDialog
import kotlinx.android.synthetic.main.activity_vote_suggest5.*
import kotlinx.android.synthetic.main.fragment_vote.*
import org.jetbrains.anko.startActivity

class VoteSuggestActivity :AppCompatActivity() {

    var stopClick: Int = 2;
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

        imageView_vote_suggest_check.setOnClickListener {
            if (vote_suggest_typing_subj.text.length==0 || vote_suggest_typing_item1.text.length == 0){
                requestfailDialog.show();
            } else requestDialog.show();
        }

        imageView_vote_suggest_back.setOnClickListener{
            finish()
        }

    }

    val requestDialog : VoteSuggestCompleteDialog by lazy {
        VoteSuggestCompleteDialog(this@VoteSuggestActivity, "제안 완료",
                "투표 제안이 완료되었습니다!", "투표는 심사 후 업로드 될 예정입니다.\n" +
                "제안해 주셔서 감사합니다.", completeConfirmListener,"확인")
    }
    val requestfailDialog : SearchAlarmDialog by lazy {
        SearchAlarmDialog(this@VoteSuggestActivity, "알림","아직 입력하지 않은 항목이 있습니다.\n" +
                "모든 항목을 입력해주세요.",
            completefailConfirmListener,"확인")
    }

    private val completeConfirmListener = View.OnClickListener {
        requestDialog!!.dismiss()
        finish()
    }
    private val completefailConfirmListener = View.OnClickListener {
        requestfailDialog!!.dismiss()
    }


}