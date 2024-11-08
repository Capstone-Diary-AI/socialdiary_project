// SplashActivity.kt
package com.example.socialdiary_project

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // 로고와 텍스트에 애니메이션 적용
        val logo: ImageView = findViewById(R.id.logo)
        val splashText: TextView = findViewById(R.id.splashText)

        // 페이드 인 애니메이션
        val fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        logo.startAnimation(fadeIn)
        splashText.startAnimation(fadeIn)

        // 몇 초 뒤 메인 화면으로 전환
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 4000L)
    }
}
