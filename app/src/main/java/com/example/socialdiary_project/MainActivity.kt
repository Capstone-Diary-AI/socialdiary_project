package com.example.socialdiary_project

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.socialdiary_project.ui.theme.LoginActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 초기 앱 실행 시 로그인 화면으로 이동 (혹은 메인 화면)
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish() // MainActivity 종료
    }
}
