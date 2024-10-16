// SettingsActivity.kt
// 사용자 설정 화면. 다크 모드, 글꼴 선택, 글자 크기 선택 기능 제공

package com.example.socialdiary_project.ui.theme

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import android.widget.Spinner
import android.widget.ArrayAdapter
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
        setSupportActionBar(toolbar)

        // 뒤로가기 버튼 클릭 리스너
        toolbar.setNavigationOnClickListener {
            onBackPressed() // 뒤로가기 기능 수행
        }

        // 다크 모드 스위치 (기능은 비활성화)
        darkModeSwitch = findViewById(R.id.switch_dark_mode)
        darkModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            // 다크 모드 기능을 수행하지 않도록 비워둠
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
