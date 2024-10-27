package com.example.socialdiary_project.ui.theme

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.socialdiary_project.MainActivity
import com.example.socialdiary_project.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

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

        // '메인' 버튼 클릭 시 MainActivity로 이동
        val mainButton = findViewById<Button>(R.id.button_main)
        mainButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // 아이디 찾기, 비밀번호 찾기, 회원가입 텍스트뷰 설정
        val findIdText = findViewById<TextView>(R.id.find_id_text)
        val findPasswordText = findViewById<TextView>(R.id.find_password_text)
        val signupText = findViewById<TextView>(R.id.signup_text)

        // 아이디 찾기 클릭 이벤트
        findIdText.setOnClickListener {
            val intent = Intent(this, FindIdActivity::class.java)
            startActivity(intent)
        }

        // 비밀번호 찾기 클릭 이벤트
        findPasswordText.setOnClickListener {
            val intent = Intent(this, FindPasswordActivity::class.java)
            startActivity(intent)
        }

        // 회원가입 클릭 이벤트
        signupText.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
