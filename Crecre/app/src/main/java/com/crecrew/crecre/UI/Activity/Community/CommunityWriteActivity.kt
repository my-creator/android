package com.crecrew.crecre.UI.Activity.Community

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.crecrew.crecre.R
import kotlinx.android.synthetic.main.activity_community_write.*

class CommunityWriteActivity : AppCompatActivity(), View.OnClickListener {

    var btn_anonymous = 0
    val My_READ_STORAGE_REQUEST_CODE = 88
    private val REQ_CODE_SELECT_IMAGE = 100

    override fun onClick(v: View?) {

        when (v!!) {
            btn_back_community_write -> {
                finish()
            }

            btn_anonymous_write_com_act -> {
                //익명버튼
                if (btn_anonymous == 0) {
                    btn_anonymous_write_com_act.isSelected = true
                    btn_anonymous = 1
                } else {
                    btn_anonymous_write_com_act.isSelected = false
                    btn_anonymous = 0
                }
            }
            btn_complete_community_write_act -> {
                //##통신붙이기
                finish()
            }
            //키보드 다운 함수
            rl_community_write_act -> {
                downKeyboard(rl_community_write_act)
            }
            //갤러리 접근
            btn_cam_write_com_act ->{
                //##이미지 어디에 집어넣어서 줘야할지? 통신
                requestReadExternalStoragePermission()
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community_write)

        init()

    }

    private fun init() {
        btn_back_community_write.setOnClickListener(this)
        btn_anonymous_write_com_act.setOnClickListener(this)
        btn_complete_community_write_act.setOnClickListener(this)
        rl_community_write_act.setOnClickListener(this)
        btn_cam_write_com_act.setOnClickListener(this)
    }

    //키보드 다운
    private fun downKeyboard(view : View) {
        val imm: InputMethodManager = applicationContext!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    //저장소 권한 확인
    private fun requestReadExternalStoragePermission() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                //사용자에게 권한을 왜 허용해야되는지에 메세지를 주기 위한 대한 로직을 추가하면 이 블락에서 하면됩니다!
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), My_READ_STORAGE_REQUEST_CODE)
            }
        } else {
            showAlbum()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == My_READ_STORAGE_REQUEST_CODE) {
            //결과에 대해 허용을 눌렀는지 체크하는 조건문이구요!
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //이곳은 외부저장소 접근을 허용했을 때에 대한 로직을 쓰시면됩니다. 우리는 앨범을 여는 메소드를 호출해주면되겠죠?
                showAlbum()
            } else {
                //이곳은 외부저장소 접근 거부를 했을때에 대한 로직을 넣어주시면 됩니다.
                finish()
            }
        }
    }

    fun showAlbum() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = android.provider.MediaStore.Images.Media.CONTENT_TYPE
        intent.data = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        startActivityForResult(intent, REQ_CODE_SELECT_IMAGE)
    }
}
