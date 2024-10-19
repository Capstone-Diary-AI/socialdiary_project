// 파일명: LoginActivity.kt
package com.example.socialdiary_project.ui.theme

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.socialdiary_project.R
import com.example.socialdiary_project.ui.theme.DiaryListActivity


class LoginActivity : AppCompatActivity() { // 클래스명 변경
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login) // 레이아웃 파일명 변경

        // 기존 로그인 버튼 설정
        val loginButton = findViewById<Button>(R.id.login_button)
        val usernameInput = findViewById<EditText>(R.id.username_input)
        val passwordInput = findViewById<EditText>(R.id.password_input)

        loginButton.setOnClickListener {
            val username = usernameInput.text.toString()
            val password = passwordInput.text.toString()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                val intent = Intent(this, DiaryListActivity::class.java)
                startActivity(intent)
            }
        }

        // '메인' 버튼 클릭 시 DiaryListActivity로 이동
        val mainButton = findViewById<Button>(R.id.button_main)
        mainButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
