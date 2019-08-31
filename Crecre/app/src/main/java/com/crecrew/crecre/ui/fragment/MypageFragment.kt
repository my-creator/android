package com.crecrew.crecreUI.Fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.crecrew.crecre.db.SharedPreferenceController
import com.crecrew.crecre.R
import com.crecrew.crecre.ui.activity.LoginActivity
import com.crecrew.crecre.utils.SearchAlarmDialog
import kotlinx.android.synthetic.main.fragment_mypage.view.*
import org.jetbrains.anko.support.v4.ctx



class MypageFragment: Fragment(){

    private lateinit var rootView: View

    val LogoutDialog: SearchAlarmDialog by lazy {
        SearchAlarmDialog(
            ctx, "알림", "로그아웃이 되었습니다.",
            completefailConfirmListener, "확인"
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        rootView = inflater.inflate(R.layout.fragment_mypage, container, false)

        //로그아웃 버튼
        rootView.btn_logout_mypage_frag.setOnClickListener {
            //삭제 Dialog띄우기
            LogoutDialog.show()

        }

        return rootView
    }

    private val completefailConfirmListener = View.OnClickListener {
        LogoutDialog!!.dismiss()
        //token 지우기
        SharedPreferenceController.setUserToken(ctx, "")


        //mainActivity를 삭제하고 LoginActivity로 가기
        //startActivity<LoginActivity>()

        val intent = Intent(context,LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        //##로그인 엑티비티로 들어가져 있는 상태에서 뒤로가기 눌렀을 경우에 mypage화면에 들어옴.
        activity?.finish()
    }
}


