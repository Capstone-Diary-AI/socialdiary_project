// FindPasswordActivity.kt
// 사용자가 비밀번호를 찾을 수 있도록 지원하는 액티비티로,
// 사용자가 입력한 아이디와 이메일을 통해 비밀번호 찾기 기능을 제공

package com.example.socialdiary_project.ui.theme

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.socialdiary_project.databinding.ActivityFindPasswordBinding

class FindPasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFindPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ViewBinding 설정
        binding = ActivityFindPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Toolbar 설정
        setSupportActionBar(binding.findPasswordToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // 뒤로가기 버튼 클릭 리스너
        binding.findPasswordToolbar.setNavigationOnClickListener {
            finish()
        }
    }
}
