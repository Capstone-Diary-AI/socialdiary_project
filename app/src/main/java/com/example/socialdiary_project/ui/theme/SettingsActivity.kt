// SettingsActivity.kt
// 사용자 설정 화면. 다크 모드, 글꼴 선택, 글자 크기 선택 기능 제공

package com.example.socialdiary_project.ui.theme

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import android.widget.ArrayAdapter
import com.example.socialdiary_project.R
import com.example.socialdiary_project.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 상단 네비게이션 바 설정
        setSupportActionBar(binding.settingsToolbar)

        // 뒤로가기 버튼 클릭 리스너
        binding.settingsToolbar.setNavigationOnClickListener {
            onBackPressed() // 뒤로가기 기능 수행
        }

        // 다크 모드 스위치 (기능은 비활성화)
        binding.switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            // 다크 모드 기능을 수행하지 않도록 비워둠
        }

        // 글꼴 선택
        ArrayAdapter.createFromResource(
            this,
            R.array.font_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerFont.adapter = adapter
        }

        // 글자 크기 선택
        ArrayAdapter.createFromResource(
            this,
            R.array.font_size_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerFontSize.adapter = adapter
        }
    }
}
