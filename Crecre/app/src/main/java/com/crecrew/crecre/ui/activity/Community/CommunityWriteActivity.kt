package com.crecrew.crecre.ui.activity.Community

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.bumptech.glide.Glide
import com.crecrew.crecre.db.SharedPreferenceController
import com.crecrew.crecre.data.CommunityWriteImageData
import com.crecrew.crecre.network.ApplicationController
import com.crecrew.crecre.network.CommunityNetworkService
import com.crecrew.crecre.network.post.PostCommunityFavoriteLikeResponse
import com.crecrew.crecre.R
import com.crecrew.crecre.utils.ApplicationData
import com.crecrew.crecre.utils.SearchAlarmDialog
import kotlinx.android.synthetic.main.activity_community_write.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileNotFoundException
import java.io.InputStream
import java.lang.Boolean.TRUE


class CommunityWriteActivity : AppCompatActivity(), View.OnClickListener {

    var btn_anonymous = 0

    val My_READ_STORAGE_REQUEST_CODE = 88
    private val REQ_CODE_SELECT_IMAGE = 100

    lateinit var selectedImageUri : Uri

    private var imgs : MultipartBody.Part? = null

    var title_main = ""
    var content = ""

    val communityNetworkService: CommunityNetworkService by lazy {
        ApplicationController.instance.communityNetworkService
    }

    var dataList: ArrayList<CommunityWriteImageData> = ArrayList()

    val titleInputDialog: SearchAlarmDialog by lazy {
        SearchAlarmDialog(
            this@CommunityWriteActivity, "알림", "제목을 입력해주세요.",
            TitleConfirmListener, "확인"
        )
    }

    val contextInputDialog: SearchAlarmDialog by lazy {
        SearchAlarmDialog(
            this@CommunityWriteActivity, "알림", "내용을 입력해주세요.",
            ContextConfirmListener, "확인"
        )
    }


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
            //작성완료 체크버튼 눌렀을 때
            btn_complete_community_write_act -> {

                if (et_title_commu_write_act.text.length == 0)
                    titleInputDialog.show()
                else if (et_context_commu_write_act.text.length == 0)
                    contextInputDialog.show()
                else
                {
                    title_main = et_title_commu_write_act.text.toString()
                    content = et_context_commu_write_act.text.toString()
                    postCommunityWriteRequest()
                }

            }

            //키보드 다운 함수
            rl_community_write_act -> {
                downKeyboard(rl_community_write_act)
            }

            //갤러리 접근
            btn_cam_write_com_act -> {
                //##이미지 어디에 집어넣어서 줘야할지? 통신

                requestReadExternalStoragePermission()

                /*     var intent : YPhotoPickerIntent = YPhotoPickerIntent(this)

                     intent.setMaxSelectCount(10)
                     intent.setShowCamera(false)
                     intent.setShowGif(true)
                     intent.setSelectCheckBox(true)
                     intent.setMaxGrideItemCount(3)
                     startActivityForResult(intent, My_READ_STORAGE_REQUEST_CODE)*/
            }

            //et로 focus가도록
            btn_et_focus_commu_write_act -> {

                if (et_context_commu_write_act.requestFocus() == TRUE)
                    downKeyboard(btn_et_focus_commu_write_act)
                else {
                    et_context_commu_write_act.requestFocus()
                    //키보드 업
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community_write)

        if(ApplicationData.loginState)
            ApplicationData.auth = SharedPreferenceController.getUserToken(this)
        Log.v("login_token", ApplicationData.auth)

        init()


    }

    private fun init() {
        btn_back_community_write.setOnClickListener(this)
        btn_anonymous_write_com_act.setOnClickListener(this)
        btn_complete_community_write_act.setOnClickListener(this)
        rl_community_write_act.setOnClickListener(this)
        btn_cam_write_com_act.setOnClickListener(this)
        btn_et_focus_commu_write_act.setOnClickListener(this)

    }

    //통신 글 작성
    private fun postCommunityWriteRequest() {

        //intent로 boardIdx가져오기
        //val boardIdx = RequestBody.create(MediaType.parse("text/plain"), boardIdx)
        val title = RequestBody.create(MediaType.parse("text/plain"), title_main)
        val contents = RequestBody.create(MediaType.parse("text/plain"),content)
        var boardIdx = intent.getIntExtra("boardIdx", -1)



        val getCommunitySmallNewPosts: Call<PostCommunityFavoriteLikeResponse> =
            communityNetworkService.postPostContentsWrite(
                ApplicationData.auth,boardIdx, btn_anonymous, title,contents, imgs)

        Log.v("TAGG", btn_anonymous.toString())

        getCommunitySmallNewPosts.enqueue(object : Callback<PostCommunityFavoriteLikeResponse> {

            override fun onFailure(call: Call<PostCommunityFavoriteLikeResponse>, t: Throwable) {
                Log.e("request et보내기 fail", t.toString())
            }

            override fun onResponse(
                call: Call<PostCommunityFavoriteLikeResponse>,
                response: Response<PostCommunityFavoriteLikeResponse>
            ) {
                if (response.isSuccessful) {
                    response?.takeIf { it.isSuccessful }
                        ?.body()
                        ?.let {
                            if (it.success == true)
                            {
                                finish()
                                Log.v("TAGG", it.message)
                            }

                        }

                }
            }
        })
    }

    //키보드 다운
    private fun downKeyboard(view: View) {
        val imm: InputMethodManager =
            applicationContext!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    //저장소 권한 확인
    private fun requestReadExternalStoragePermission() {

        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                )
            ) {
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                    My_READ_STORAGE_REQUEST_CODE
                )
            }
        } else {
            showAlbum()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == My_READ_STORAGE_REQUEST_CODE) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showAlbum()
            } else {
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQ_CODE_SELECT_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {

                    selectedImageUri = data.data!!
                    //Uri를 getRealPathFromURI라는 메소드를 통해 절대 경로를 알아내고, 인스턴스 변수 imageURI에 넣어줍니다!

                    Glide.with(this@CommunityWriteActivity)
                        .load(selectedImageUri)
                        .thumbnail(0.1f)
                        .into(img_insert_commu_write_act)

                    val options = BitmapFactory.Options()

                    var input: InputStream? = null // here, you need to get your context.
                    try {
                        input = contentResolver.openInputStream(selectedImageUri)
                    } catch (e: FileNotFoundException) {
                        e.printStackTrace()
                    }

                    val bitmap = BitmapFactory.decodeStream(input, null, options) // InputStream 으로부터 Bitmap 을 만들어 준다.
                    val baos = ByteArrayOutputStream()
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos)

                    val photoBody = RequestBody.create(MediaType.parse("image/jpg"), baos.toByteArray())
                    val photo = File(selectedImageUri.toString()) // 가져온 파일의 이름을 알아내려고 사용합니다

                    imgs = MultipartBody.Part.createFormData("imgs", photo.name, photoBody)

                }
            }
        }

        /*  //다중선택
          if (clipData != null) {
              //사진 10개 이상 이면 끝!
              if (clipData.itemCount + presentImabeBoxNum > 9)
                  toast("10장 이하만 선택가능합니다!")
              else {
                  for (i in 0 until clipData.itemCount) {

                      val preUri = clipData.getItemAt(i).uri.toString()
                      val uri = Uri.parse(preUri.split('%')[0])

                      //###RecyclerView의 자리로 넣어주면 된다,,,!!!
                      //communityWriteImageData의 dataList에 uri를 저장시켜라!
                      dataList[i].image = uri.toString()
                      presentImabeBoxNum++

                  }
                  var communityWriteImageRecyclerViewAdapter =
                      CommunityWriteImageRecyclerViewAdapter(this, dataList)
                  rv_insert_img_commu_write_act.adapter = communityWriteImageRecyclerViewAdapter
                  rv_insert_img_commu_write_act.layoutManager =
                      LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
              }
          }*/


        /* val clipData = data!!.clipData
         Log.v("ghgh", clipData.toString())

         //다중선택
         if (clipData != null) {
             //사진 10개 이상 이면 끝!
             if (clipData.itemCount + presentImabeBoxNum > 9)
                 toast("10장 이하만 선택가능합니다!")
             else {
                 for (i in 0 until clipData.itemCount) {

                     val preUri = clipData.getItemAt(i).uri.toString()
                     val uri = Uri.parse(preUri.split('%')[0])

                     //###RecyclerView의 자리로 넣어주면 된다,,,!!!
                     //communityWriteImageData의 dataList에 uri를 저장시켜라!
                     dataList[i].image = uri.toString()
                     presentImabeBoxNum++

                 }
                 var communityWriteImageRecyclerViewAdapter =
                     CommunityWriteImageRecyclerViewAdapter(this, dataList)
                 rv_insert_img_commu_write_act.adapter = communityWriteImageRecyclerViewAdapter
                 rv_insert_img_commu_write_act.layoutManager =
                     LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
             }
         }*/
    }


    private val TitleConfirmListener = View.OnClickListener {
        titleInputDialog!!.dismiss()
        //##title EditText에 포커스 주기
    }

    private val ContextConfirmListener = View.OnClickListener {
        contextInputDialog!!.dismiss()
        //##context EditText에 포커스 주기
    }


}
