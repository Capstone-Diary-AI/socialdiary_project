// LoginActivity.kt
// 사용자가 앱에 로그인할 수 있는 기능을 제공하는 액티비티로,
// 로그인 정보를 입력받아 메인 화면으로 이동하거나, 회원가입, 아이디 찾기 등 다른 화면으로 이동

package com.example.socialdiary_project.ui.theme

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.socialdiary_project.LoadingActivity
import com.example.socialdiary_project.MainActivity
import com.example.socialdiary_project.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    // View Binding을 통해 XML 레이아웃과 연결
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ActivityLoginBinding을 통해 레이아웃 설정
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 메인 화면으로 이동하는 버튼
        binding.buttonMain.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // 로그인 버튼 클릭 시, 사용자 입력을 검증하여 로딩 화면을 거쳐 메인 화면으로 이동
        binding.loginButton.setOnClickListener {
            val username = binding.usernameInput.text.toString()
            val password = binding.passwordInput.text.toString()

            // 입력값이 비어있지 않을 경우에만 로딩 화면을 통해 메인 화면으로 이동
            if (username.isNotEmpty() && password.isNotEmpty()) {
                val intent = Intent(this, LoadingActivity::class.java)
                intent.putExtra("nextActivity", "com.example.socialdiary_project.MainActivity")
                intent.putExtra("loadingDuration", 3000L)
                startActivity(intent)
            }
        }

// 회원가입 버튼 클릭 시 로딩 화면을 거쳐 회원가입 페이지로 이동
        binding.signupText.setOnClickListener {
            val intent = Intent(this, LoadingActivity::class.java)
            intent.putExtra("nextActivity", "com.example.socialdiary_project.ui.theme.RegisterActivity")
            intent.putExtra("loadingDuration", 1000L)
            startActivity(intent)
        }

// 아이디 찾기 버튼 클릭 시 로딩 화면을 거쳐 아이디 찾기 페이지로 이동
        binding.findIdText.setOnClickListener {
            val intent = Intent(this, LoadingActivity::class.java)
            intent.putExtra("nextActivity", "com.example.socialdiary_project.ui.theme.FindIdActivity")
            intent.putExtra("loadingDuration", 1000L)
            startActivity(intent)
        }

// 비밀번호 찾기 버튼 클릭 시 로딩 화면을 거쳐 비밀번호 찾기 페이지로 이동
        binding.findPasswordText.setOnClickListener {
            val intent = Intent(this, LoadingActivity::class.java)
            intent.putExtra("nextActivity", "com.example.socialdiary_project.ui.theme.FindPasswordActivity")
            intent.putExtra("loadingDuration", 1000L)
            startActivity(intent)
        }
    }
}
