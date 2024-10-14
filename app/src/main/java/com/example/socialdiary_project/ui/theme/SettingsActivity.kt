// SettingsActivity.kt
// 사용자 설정 화면. 다크 모드, 글꼴 선택, 글자 크기 선택 기능 제공

package com.example.socialdiary_project.ui.theme

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import android.widget.Spinner
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.Switch
import androidx.appcompat.app.AppCompatDelegate
import com.example.socialdiary_project.R

class SettingsActivity : AppCompatActivity() {
    private lateinit var darkModeSwitch: SwitchCompat
    private lateinit var fontSpinner: Spinner
    private lateinit var fontSizeSpinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        // 상단 네비게이션 바 설정
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.settings_toolbar)
        val darkModeSwitch = findViewById<Switch>(R.id.dark_mode_switch)
        setSupportActionBar(toolbar)

        // 뒤로가기 버튼 클릭 리스너
        toolbar.setNavigationOnClickListener {
            onBackPressed() // 뒤로가기 기능 수행
        }

        // 다크 모드 스위치
        // 현재 모드에 따라 스위치 상태 설정
        darkModeSwitch.isChecked = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES

        darkModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // 다크 모드 활성화
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                // 라이트 모드 활성화
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        // 글꼴 선택
        fontSpinner = findViewById(R.id.spinner_font)
        ArrayAdapter.createFromResource(
            this,
            R.array.font_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            fontSpinner.adapter = adapter
        }

        // 글자 크기 선택
        fontSizeSpinner = findViewById(R.id.spinner_font_size)
        ArrayAdapter.createFromResource(
            this,
            R.array.font_size_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            fontSizeSpinner.adapter = adapter
        }
    }
}
