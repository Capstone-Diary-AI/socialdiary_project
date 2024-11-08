// RegisterActivity.kt
// 회원가입 화면을 담당하는 액티비티로, 사용자가 아이디와 비밀번호를 입력하여
// 새로운 계정을 생성할 수 있는 기능을 제공

package com.example.socialdiary_project.ui.theme

import android.os.Bundle
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
    }
}
