// RegisterActivity.kt
// 회원가입 화면을 담당하는 액티비티로, 사용자가 닉네임, 아이디, 비밀번호를 입력하여
// 새로운 계정을 생성할 수 있는 기능을 제공

package com.example.socialdiary_project.ui.theme

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.socialdiary_project.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ViewBinding 설정
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Toolbar 설정
        setSupportActionBar(binding.registerToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // 뒤로가기 버튼 클릭 리스너
        binding.registerToolbar.setNavigationOnClickListener {
            finish()
        }

        // 회원가입 버튼 클릭 리스너
        binding.registerButton.setOnClickListener {
            val nickname = binding.nicknameInput.text.toString()
            val username = binding.usernameInput.text.toString()
            val password = binding.passwordInput.text.toString()

            if (nickname.isBlank() || username.isBlank() || password.isBlank()) {
                // 입력값 검증
                Toast.makeText(this, "모든 필드를 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else {
                // 회원가입 처리 로직 (예시)
                Toast.makeText(this, "회원가입 성공! 닉네임: $nickname", Toast.LENGTH_SHORT).show()
                finish() // 회원가입 성공 후 화면 종료
            }
        }
    }
}
