// LoadingActivity.kt
package com.example.socialdiary_project

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class LoadingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)

        // 로딩 시간을 인텐트로 전달받아 설정 (기본값 2초)
        val loadingDuration = intent.getLongExtra("loadingDuration", 3000L)

        Handler(Looper.getMainLooper()).postDelayed({
            // 다음 화면으로 전환
            val nextActivity = intent.getStringExtra("nextActivity")
            if (nextActivity != null) {
                val targetIntent = Intent().apply {
                    setClassName(this@LoadingActivity, nextActivity)
                }
                startActivity(targetIntent)
            }
            finish()
        }, loadingDuration) // 로딩 시간 설정
    }
}
