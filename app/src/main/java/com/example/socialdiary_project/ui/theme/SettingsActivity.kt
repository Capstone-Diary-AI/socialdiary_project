// SettingsActivity.kt

package com.example.socialdiary_project.ui.theme

import android.os.Build
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.socialdiary_project.R
import com.example.socialdiary_project.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding
    private var currentMode: Int = AppCompatDelegate.MODE_NIGHT_NO // 현재 모드 초기값

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 저장된 테마 설정 값 불러오기
        val preferences = getSharedPreferences("app_preferences", MODE_PRIVATE)
        currentMode = preferences.getInt("theme_mode", AppCompatDelegate.MODE_NIGHT_NO)
        AppCompatDelegate.setDefaultNightMode(currentMode)

        // ActivitySettingsBinding을 통해 레이아웃 설정
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Toolbar 설정
        setSupportActionBar(binding.settingsToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // 뒤로가기 버튼 클릭 리스너
        binding.settingsToolbar.setNavigationOnClickListener {
            finish()
        }

        // 라디오 버튼 선택 이벤트
        binding.radioMode.setOnCheckedChangeListener { _, checkedId ->
            val editor = preferences.edit()
            val selectedMode = when (checkedId) {
                R.id.rbLight -> AppCompatDelegate.MODE_NIGHT_NO
                R.id.rbDark -> AppCompatDelegate.MODE_NIGHT_YES
                R.id.rbDefault -> if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                } else {
                    AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY
                }
                else -> currentMode
            }

            if (currentMode != selectedMode) {
                currentMode = selectedMode
                AppCompatDelegate.setDefaultNightMode(selectedMode)
                editor.putInt("theme_mode", selectedMode)
                editor.apply()
                recreate() // 변경이 있을 때만 recreate() 호출
            }
        }

        // 글꼴 선택 스피너 설정
        ArrayAdapter.createFromResource(
            this,
            R.array.font_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerFont.adapter = adapter
        }

        // 글자 크기 선택 스피너 설정
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
