// FindIdActivity.kt
// 사용자가 아이디를 찾을 수 있도록 지원하는 액티비티로,
// 사용자가 입력한 이메일을 통해 아이디를 찾는 기능을 제공

package com.example.socialdiary_project.ui.theme

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.socialdiary_project.databinding.ActivityFindIdBinding

class FindIdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFindIdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ViewBinding 설정
        binding = ActivityFindIdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Toolbar 설정
        setSupportActionBar(binding.findIdToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // 뒤로가기 버튼 클릭 리스너
        binding.findIdToolbar.setNavigationOnClickListener {
            finish()
        }
    }
}
