package com.crecrew.crecre.UI.Activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.crecrew.crecre.R
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity :AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        var source = findViewById(R.id.activity_splash_container) as ImageView
        Glide.with(this).load(R.drawable.splash).into(source)

        val handler = Handler()
        handler.postDelayed({
            startActivity(Intent(applicationContext, LoginActivity::class.java))
            finish()
        }, 2000)

        // splash 불러오는 동안 background에서 미리 작업하는 방법?
    }

}